package com.jpcf.blog

<<<<<<< HEAD
class JsController {
	def summary(def poster) {
		def posts = BlogPost.findAllByOwner(poster)
	}
=======
import grails.converters.JSON

class JsController {

    def summary(String id) {
        def user = id
        def limit = params.limit
        def blogs = []
        def json = []
        def formatter = new java.text.SimpleDateFormat("MM/dd/yyyy hh:mm a")
        
        if (!limit) {
            limit = -1
        }
        
        if (user) {
            def owner = User.findByUsername(user)
            println "OWNER: " + owner
            if (owner) {
                if (limit < 0) {
                    blogs = BlogPost.findAllByOwner(owner).collect {[title: it.title, content: it.content, dateCreated: formatter.format(it.dateCreated), link: createLink(controller: "blogPost", action: "show", id: it.id, absolute: true)]}
                } else if (limit > 0) {
                    blogs = BlogPost.findAllByOwner(owner, [max: limit.toInteger()]).collect {[title: it.title, content: it.content, dateCreated: formatter.format(it.dateCreated), link: createLink(controller: "blogPost", action: "show", id: it.id, absolute: true)]}
                }

                /*                
                blogs.eachWithIndex { blog, index ->
                    def ret = [:]
                    ret["title"] = blog.title
                    ret["content"] = blog.content
                    ret["dateCreated"] = formatter.format(blog.dateCreated)
                    ret["link"] = createLink(controller: "blogPost", action: "show", id: blog.id, absolute: true)
                    json += ret
                } 
                */ 
            }
        }
        
        render blogs as JSON
    }
>>>>>>> 8559a199887c7f2f3e8050e69d956863c056b293
}
