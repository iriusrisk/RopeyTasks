package net.continuumsecurity.ropeytasks

import org.springframework.dao.DataIntegrityViolationException

import com.sun.xml.internal.rngom.parse.xml.SchemaParser.ParamState;

class UserController {
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def recaptchaService

	def edit() {
		//def userInstance = User.get(session.user.id)
        def userInstance = User.get(params.id)  //Vulnerable
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'user.label', default: 'User'),
				params.id
			])
			redirect(controller: "task", action: "list")
			return
		}

		[userInstance: userInstance]
	}

	def update() {
		def userInstance = User.get(params.id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'user.label', default: 'User'),
				params.id
			])
			render(view: "edit", model: [userInstance: userInstance])
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if (userInstance.version > version) {
				userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'user.label', default: 'User')]
						as Object[],
						"Another user has updated this User while you were editing")
				render(view: "edit", model: [userInstance: userInstance])
				return
			}
		}

		userInstance.properties = params

		if (!userInstance.save(flush: true)) {
			render(view: "edit", model: [userInstance: userInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'user.label', default: 'User'),
			userInstance.id
		])
		redirect(controller: "task",action: "list")
	}
	
	def recover() {
		if (params.email == null) {
			render(view: 'recover')
			return
		}
		if (!recaptchaService.verifyAnswer(session, request.getRemoteAddr(), params)) {
			flash.message = "CAPTCHA failed"
			render(view: "recover")
			return
		} else {
			recaptchaService.cleanUp(session)
		}
		def users = User.executeQuery("from User u where u.email='${params.email}'")
		if (users != null && users.size() > 0) {
			flash.message = 'Email with login details sent!'
			redirect(action: 'login')
		} else {
			flash.message = 'No such user found'
		}
		
	}
	
	def login() {
		def user = null
   		if (params.username != null) {	
			def users = User.executeQuery("from User u where u.username='${params.username}'")
			if (users != null && users.size() > 0) {
				user = users[0]
				session['failedLogins'] = user.failedLogins
				if (session['failedLogins'] >= 3) {
					if (!recaptchaService.verifyAnswer(session, request.getRemoteAddr(), params)) {
						flash.message = "CAPTCHA failed"
						log.debug("CAPTCHA failed, returning user: "+user.username)
						render(view: "login",model: [user: user])
						return
					} else {
						recaptchaService.cleanUp(session)
						flash.message = ""
					}
				}
				
				if (user.password.equalsIgnoreCase(params.password)) {
					user.failedLogins = 0
					user.save()
					session['user'] = user
				} else {
					user.failedLogins = user.failedLogins + 1
					user.save()
					flash.message = "Incorrect password"
				}
			} else {
				log.debug "Username: "+params.username+" not found."
				flash.message = "Username: "+params.username+" not found."
			}	
		} 
		if (session.user != null) {
			flash.message = ''
			if (session.user.role == 0) {
				redirect(controller: "task", action: "list")
				return
			} else {
				redirect(controller: "admin", action: "list")
				return
			}	
		}
		[user: user]	
	}
	
	def logout() {
		session.user = null
		redirect(uri: "/")
	}
	

}
