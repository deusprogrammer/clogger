import com.jpcf.blog.*

class SecurityFilters {
	def noRequirements = [blogPost: ["list", "listByUser", "show"], blogReply: [], user: ["list", "show", "create", "save", "login", "logout"], avatar: ["get"], js: ["summary"]]
	def loginRequired = [blogPost: [], blogReply: ["create", "save"], user: ["show"], avatar: []]
	def adminLoginRequired = [blogPost: ["create", "save"], blogReply: [], user: [], avatar: ["index", "list", "create", "save", "edit", "update", "delete"], admin: ["list"]]
	def ownershipRequired = [blogPost: ["edit", "update", "delete"], blogReply: ["edit", "update", "delete"], user: ["edit", "update", "delete", "changePassword", "updatePassword"], avatar: []]
	
	def domainMappings = [blogPost: BlogPost.class, blogReply: BlogReply.class, user: User.class, avatar: Avatar.class]
	
	def sessionService

    def filters = {
        all(controller:'*', action:'*') {
            before = {
				def availableActions = []
				def user = null
				def loggedIn = false
				def owner = false
				def poweruser = false
				
				if (controllerName == "error") {
					return true
				}
				
				availableActions += noRequirements[controllerName]
				
				//Logged in
				if (session["user"]) {
					availableActions += loginRequired[controllerName]
					user = User.get(session["user"])
					loggedIn = true
				}
				
				//Logged in and superuser
				if (user && user.userGroup == Group.SUPERUSER) {
					return true
				} 
				//Logged in and poweruser
				else if (user && user.userGroup == Group.POWERUSER) {
					availableActions += adminLoginRequired[controllerName]
					poweruser = true
				}
				
				//Logged in and owner of resource
				if (user && sessionService.checkOwnership(user.id, params)) {
					availableActions += ownershipRequired[controllerName]
					owner = true
				}
				
				println "CONTROLLER REQUESTED:  ${controllerName}"
				println "ACTION REQUESTED:      ${actionName}"
				println "AVAILABLE ACTIONS:     ${availableActions}"
				println "LOGGED IN:             ${loggedIn}"
				println "POWERUSER:             ${poweruser}"
				println "OWNER:                 ${owner}"
				println "\n"
				
				if (actionName in availableActions) {
					return true
				}
				else {
					redirect(controller: "error", action: "status403")
				}
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
