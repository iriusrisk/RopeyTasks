package net.continuumsecurity.ropeytasks

class SecurityFilters {

    def filters = {
		admin(controller:'admin', action:'list') {
            //Disable access control
			before = {
				if (session?.user?.role != 1) {
					redirect(controller: "user", action: "login")
                    return
				}
			}
			after = { Map model ->

			}
			afterView = { Exception e ->

			}
		}
		
        all(controller:'*', action:'*') {
            before = {
				if (!session.user && actionName != "login" && actionName != 'recover') {
					log.debug 'User not logged in, redirecting.'
					redirect(controller: "user", action: "login")
					return
				}
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
		

    }
}