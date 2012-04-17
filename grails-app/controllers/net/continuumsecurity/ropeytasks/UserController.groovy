package net.continuumsecurity.ropeytasks

import org.springframework.dao.DataIntegrityViolationException

import com.sun.xml.internal.rngom.parse.xml.SchemaParser.ParamState;

class UserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def edit() {
		def userInstance = User.get(params.id)
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

	def login() {
		def user = null
		if (params.username != null) {
			def users = User.executeQuery("from User u where u.username='${params.username}'")
			if (users != null && users.size() > 0) {
				user = users[0]
				log.debug "Found user: ${user.username} with password: ${user.password} ID: ${user.id}"
				if (user.password.equalsIgnoreCase(params.password)) {
					session['user'] = user
					log.debug 'Successfully logged in user: '+user.username
				} else {
					log.debug "Incorrect password: ${params.password} for username: ${params.username}"
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
			} else {
				redirect(controller: "admin", action: "list")
			}	
		}	
	}
	
	def logout() {
		session.user = null
		redirect(uri: "/")
	}
	

}
