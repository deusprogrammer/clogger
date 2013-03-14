package com.jpcf.blog

class BlogPost {
	String title
	String content
	Date dateCreated
	Date lastUpdated
	
	static belongsTo = [owner: User]
	static hasMany   = [replies: BlogReply]

    static constraints = {
		content maxSize: 4096
    }
	
	static mapping = {
		replies sort: 'dateCreated'
		sort 'dateCreated': 'desc'
	}
}
