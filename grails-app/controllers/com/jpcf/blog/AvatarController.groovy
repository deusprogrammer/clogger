package com.jpcf.blog

import org.springframework.dao.DataIntegrityViolationException

class AvatarController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [avatarInstanceList: Avatar.list(params), avatarInstanceTotal: Avatar.count()]
    }

    def create() {
        [avatarInstance: new Avatar(params)]
    }
	
	def save() {
		println "IN save()"
		def avatar
		
		def f = request.getFile('avatarImage')
		if(!f.empty) {
			avatar = new Avatar(params)
			
			def filename = f.getOriginalFilename()
			def extension = filename[filename.lastIndexOf(".")..filename.size() - 1]
			
			avatar.save()
			
			def path = grailsApplication.config.avatars.location.toString() + "avatar" + avatar.id + extension

			avatar.filePath = path
			avatar.save()
			
			println "FILE: " + path
			
			new File(grailsApplication.config.avatars.location.toString()).mkdirs()
			f.transferTo(new File(path))
		}
		else {
			flash.message = 'File cannot be empty!'
			redirect(controller: "configuration", action: "index")
			return
		}
		
		redirect(controller: "avatar", action: "list")
	}

    def show(Long id) {
        def avatarInstance = Avatar.get(id)
        if (!avatarInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'avatar.label', default: 'Avatar'), id])
            redirect(action: "list")
            return
        }

        [avatarInstance: avatarInstance]
    }

    def edit(Long id) {
        def avatarInstance = Avatar.get(id)
        if (!avatarInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'avatar.label', default: 'Avatar'), id])
            redirect(action: "list")
            return
        }

        [avatarInstance: avatarInstance]
    }

    def update(Long id, Long version) {
        def avatarInstance = Avatar.get(id)
        if (!avatarInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'avatar.label', default: 'Avatar'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (avatarInstance.version > version) {
                avatarInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'avatar.label', default: 'Avatar')] as Object[],
                          "Another user has updated this Avatar while you were editing")
                render(view: "edit", model: [avatarInstance: avatarInstance])
                return
            }
        }

        avatarInstance.properties = params

        if (!avatarInstance.save(flush: true)) {
            render(view: "edit", model: [avatarInstance: avatarInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'avatar.label', default: 'Avatar'), avatarInstance.id])
        redirect(action: "show", id: avatarInstance.id)
    }

    def delete(Long id) {
        def avatarInstance = Avatar.get(id)
        if (!avatarInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'avatar.label', default: 'Avatar'), id])
            redirect(action: "list")
            return
        }

        try {
            avatarInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'avatar.label', default: 'Avatar'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'avatar.label', default: 'Avatar'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def get(Long id) {
		def avatar = Avatar.get(id)
		
		if (avatar) {
			def file = new File(avatar.filePath)
			response.setHeader("Content-Type", "binary/octet-stream")
			response.setHeader("Content-Disposition", "attachment; filename=${file.getName()}")
			response.setHeader("Content-Length", "${file.size()}")

			response.outputStream << file.newInputStream()
		}
	}
}
