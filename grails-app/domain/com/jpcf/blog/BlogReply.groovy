package com.jpcf.blog

class BlogReply {
	String content
	Date dateCreated
	Date lastUpdated
	
	static belongsTo = [post: BlogPost, owner: User]

    static constraints = {
		content maxSize: 4096
    }
}
