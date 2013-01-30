import com.jpcf.blog.User
import com.jpcf.blog.Group

class SecurityFilters {
	def noRequirements = [blogPost: ["list", "show"], blogReply: [], user: ["create", "save", "login"]]
	def loginRequired = [blogPost: [], blogReply: ["create", "save"], user: ["show"]]
	def adminLoginRequired = [blogPost: ["create", "save"], blogReply: [], user: ["list"]]
	def ownershipRequired = [blogPost: ["edit", "update", "delete"], blogReply: ["edit", "update", "delete"], user: ["edit", "update", "delete"]]

    def filters = {
        all(controller:'*', action:'*') {
            before = {
				def availableActions = []
				def user = null
				
				if (controllerName == "error") {
					return true
				}
				
				availableActions += noRequirements[controllerName]
				
				//Logged in
				if (session["user"]) {
					availableActions += loginRequired[controllerName]
					user = User.get(session["user"])
				}
				
				//Logged in and admin
				if (user && user.userGroup == Group.POWERUSER) {
					availableActions += adminLoginRequired[controllerName]
				}
				
				//Logged in and owner of resource
				if (user) {
					availableActions += ownershipRequired[controllerName]
				}
				
				println "CONTROLLER REQUESTED: " + controllerName
				println "ACTION REQUESTED:     " + actionName
				println "AVAILABLE ACTIONS:    " + availableActions
				
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
