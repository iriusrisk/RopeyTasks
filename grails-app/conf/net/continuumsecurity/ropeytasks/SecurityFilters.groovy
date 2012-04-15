package net.continuumsecurity.ropeytasks

class SecurityFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
				if (!session.user && actionName != "login") {
					log.debug 'User not logged in, redirecting.'
					redirect(controller: "user", action: "login")
				}
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
		
		admin(controller:'admin') {
			before = {
				if (!session.user || !session.user.role == 1) {
					redirect(controller: "user", action: "login")
				}
			}
			after = { Map model ->

			}
			afterView = { Exception e ->

			}
		}
    }
}