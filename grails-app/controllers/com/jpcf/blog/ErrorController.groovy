package com.jpcf.blog

class ErrorController {

    def status403() { 
		render "<h1>FORBIDDEN</h1>"
	}
	
	def status404() { 
		render "<h1>FILE NOT FOUND</h1>"
	}
}
