package com.jpcf.blog

class BlogPreviewTagLib {
	def stringUtilService

	def contentPreview = { attrs, body ->
		def length = attrs["shortenTo"]
		out << stringUtilService.shorten(body().toString(), length as Integer) 
	}
}
