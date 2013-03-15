package com.jpcf.blog

class SessionService {
    def checkCredentials(def username, def password) {
        def user = User.findByUsername(username)
        
        if (user) {
            def passAndSalt = user.salt + "--" + password
            def hashedPass = passAndSalt.encodeAsMD5()
            
            if (hashedPass.equals(user.password)) {
                return true
            }
            else {
                return false
            }
        }
        else {
            println "Unable to find user by that name!"
            return false
        }
    }
	
	def checkOwnership(def userId, def params) {
        if (!params.id || !params.id.isNumber()) {
            return true
        }
        
		def objectId = params.id as Integer
		
		def object
		def controller = params.controller
		def owned = false
		
		userId = userId as Integer
		
		if (!objectId) {
			return false
		}
		
		switch (controller) {
			case "blogPost":
				object = BlogPost.get(objectId)
				break;
			case "blogReply":
				object = BlogReply.get(objectId)
				break;
			case "user":
				return userId == objectId
				break
			default: 
				return false
		}
		
		if (object) {
			return object.owner.id == userId
		} else {
			return false
		}
	}
}