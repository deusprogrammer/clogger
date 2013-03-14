package com.jpcf.blog

import grails.converters.JSON

class JsController {

    def summary(String id) {
        def user = id
        def limit = params.limit
        def blogs = []
        
        if (!limit) {
            limit = -1
        }
        
        if (user) {
            def owner = User.findByUsername(user)
            println "OWNER: " + owner
            if (owner) {
                if (limit < 0) {
                    blogs = BlogPost.findAllByOwner(owner)
                } else if (limit > 0) {
                    blogs = BlogPost.findAllByOwner(owner, [max: limit.toInteger()])
                }
                
                blogs.eachWithIndex { blog, index ->
                    if (index == 0) {
                        println "LATEST: " + blog.title + " " + blog.dateCreated + "\n" + blog.content
                    } else {
                        println "BRIEF: " + blog.title + " " + blog.dateCreated
                    }
                }  
            }
        }
        
        withFormat {
            json {render blogs as JSON}
        }
    }
}
