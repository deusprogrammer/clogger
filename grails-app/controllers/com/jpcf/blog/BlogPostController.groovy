package com.jpcf.blog

import org.springframework.dao.DataIntegrityViolationException

class BlogPostController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [blogPostInstanceList: BlogPost.list(params), blogPostInstanceTotal: BlogPost.count()]
    }

    def create() {
        [blogPostInstance: new BlogPost(params)]
    }

    def save() {
		//Attach owner to post
		def owner = User.get(session["user"])
		params.owner = owner
		
        def blogPostInstance = new BlogPost(params)
        if (!blogPostInstance.save(flush: true)) {
            render(view: "create", model: [blogPostInstance: blogPostInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'blogPost.label', default: 'BlogPost'), blogPostInstance.id])
        redirect(action: "show", id: blogPostInstance.id)
    }

    def show(Long id) {
        def blogPostInstance = BlogPost.get(id)
        if (!blogPostInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'blogPost.label', default: 'BlogPost'), id])
            redirect(action: "list")
            return
        }

        [blogPostInstance: blogPostInstance]
    }

    def edit(Long id) {
        def blogPostInstance = BlogPost.get(id)
        if (!blogPostInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'blogPost.label', default: 'BlogPost'), id])
            redirect(action: "list")
            return
        }

        [blogPostInstance: blogPostInstance]
    }

    def update(Long id, Long version) {
        def blogPostInstance = BlogPost.get(id)
        if (!blogPostInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'blogPost.label', default: 'BlogPost'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (blogPostInstance.version > version) {
                blogPostInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'blogPost.label', default: 'BlogPost')] as Object[],
                          "Another user has updated this BlogPost while you were editing")
                render(view: "edit", model: [blogPostInstance: blogPostInstance])
                return
            }
        }

        blogPostInstance.properties = params

        if (!blogPostInstance.save(flush: true)) {
            render(view: "edit", model: [blogPostInstance: blogPostInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'blogPost.label', default: 'BlogPost'), blogPostInstance.id])
        redirect(action: "show", id: blogPostInstance.id)
    }

    def delete(Long id) {
        def blogPostInstance = BlogPost.get(id)
        if (!blogPostInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'blogPost.label', default: 'BlogPost'), id])
            redirect(action: "list")
            return
        }

        try {
            blogPostInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'blogPost.label', default: 'BlogPost'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'blogPost.label', default: 'BlogPost'), id])
            redirect(action: "show", id: id)
        }
    }
}
