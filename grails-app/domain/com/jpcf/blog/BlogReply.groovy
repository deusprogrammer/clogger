package com.jpcf.blog

class BlogReply {
	String content
	Date dateCreated
	Date lastUpdated
	
	static belongsTo = [post: BlogPost, owner: User]
    
    def beforeSave = {
        content = content.replaceAll(/<!--.*?-->/, '').replaceAll(/<.*?>/, '').replaceAll("\n", "<br/>")
    }
    
    def beforeInsert = {
        beforeSave()
    }
    
    def beforeUpdate = {
        beforeSave()
    }

    static constraints = {
		content maxSize: 4096
    }
}
