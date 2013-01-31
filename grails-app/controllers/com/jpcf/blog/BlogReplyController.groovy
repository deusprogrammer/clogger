package com.jpcf.blog

import org.springframework.dao.DataIntegrityViolationException

class BlogReplyController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def save() {
		//Attach owner to reply
		def owner = User.get(session["user"])
		params.owner = owner
		
        def blogReplyInstance = new BlogReply(params)
        if (!blogReplyInstance.save(flush: true)) {
            render(view: "create", model: [blogReplyInstance: blogReplyInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'blogReply.label', default: 'BlogReply'), blogReplyInstance.id])
        redirect(controller: "blogPost", action: "show", id: blogReplyInstance.post.id)
    }

    def edit(Long id) {
        def blogReplyInstance = BlogReply.get(id)
        if (!blogReplyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'blogReply.label', default: 'BlogReply'), id])
            redirect(action: "list")
            return
        }

        [blogReplyInstance: blogReplyInstance]
    }

    def update(Long id, Long version) {
        def blogReplyInstance = BlogReply.get(id)
        if (!blogReplyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'blogReply.label', default: 'BlogReply'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (blogReplyInstance.version > version) {
                blogReplyInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'blogReply.label', default: 'BlogReply')] as Object[],
                          "Another user has updated this BlogReply while you were editing")
                render(view: "edit", model: [blogReplyInstance: blogReplyInstance])
                return
            }
        }

        blogReplyInstance.properties = params

        if (!blogReplyInstance.save(flush: true)) {
            render(view: "edit", model: [blogReplyInstance: blogReplyInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'blogReply.label', default: 'BlogReply'), blogReplyInstance.id])
        redirect(action: "show", id: blogReplyInstance.id)
    }

    def delete(Long id) {
        def blogReplyInstance = BlogReply.get(id)
        if (!blogReplyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'blogReply.label', default: 'BlogReply'), id])
            redirect(action: "list")
            return
        }

        try {
            blogReplyInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'blogReply.label', default: 'BlogReply'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'blogReply.label', default: 'BlogReply'), id])
            redirect(action: "show", id: id)
        }
    }
}
