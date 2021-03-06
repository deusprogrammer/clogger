package com.jpcf.blog

import org.springframework.dao.DataIntegrityViolationException

class UserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def sessionService

    def index() {
        chain(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def create() {
        [userInstance: new User(params)]
    }

    def save() {
        def userInstance = new User(params)
        if (!userInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }
		
		session["user"] = userInstance.id

        flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def show(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def edit(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def update(Long id, Long version) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def delete(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def login() {
		if (sessionService.checkCredentials(params.username, params.password)) {
			session["user"] = User.findByUsername(params.username).id
		} else {
			session["user"] = null
		}
		
		redirect(url: request.getHeader("referer"))
		return
	}
	
	def logout() {
		session["user"] = null
		chain(controller: "blogPost", action: "list")
	}
	
	def changePassword(Long id) {
		def user = User.get(id)
		
		if (!user) {
			flash.message = "Unable to find user with that id"
			redirect(action: "list")
			return
		}
		
		[userInstance: user]
	}
	
	def promoteTo() {
		def id = params.id
		def level = params.level
		def user
		
		if (!id) {
			println "ID NOT SET"
			redirect(controller: "error", action: "status404")
			return
		}
		
		user = User.get(id)
		
		if (!user) {
			println "UNABLE TO FIND USER"
			redirect(controller: "error", action: "status404")
			return
		}
		
		switch(level) {
			case "superuser":
				user.userGroup = Group.SUPERUSER;
				break;
			case "poweruser":
				user.userGroup = Group.POWERUSER;
				break;
			default:
				println "UNABLE TO FIND LEVEL"
				redirect(controller: "error", action: "status404")
				return
		}
		
		user.save();
		flash.message = "User promoted successfully!"
		redirect(action: "show", id: user.id)
		return
	}
	
	def updatePassword() {
		def salt = new Date().getTime()
		def passAndSalt = salt + "--" + params.password
		def password = passAndSalt.encodeAsMD5()
		
		def user = User.get(params.id)
		
		if (user) {
			flash.message = "Successfully changed password!"
			user.password = password
			user.salt = salt
		}
		else {
			flash.message = "Password change failed!"
		}
		
		redirect(action: "show", id: user.id)
	}
}
