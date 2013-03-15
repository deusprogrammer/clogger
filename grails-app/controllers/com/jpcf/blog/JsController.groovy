package com.jpcf.blog

class JsController {
	def summary(def poster) {
		def posts = BlogPost.findAllByOwner(poster)
	}
}
