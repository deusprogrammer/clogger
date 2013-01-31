package com.jpcf.blog

class SessionTagLib {
	def currentUser = { attrs, body ->
		if (session["user"]) {
			def user = User.get(session["user"])
			if (user) {
				out << "${user.username}"
			}
			else {
			}
		}
	}
}
