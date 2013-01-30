package com.jpcf.blog

class User {
	String username
	String password
	String salt
	
	String firstName
	String lastName
	
	Group userGroup
	
	static hasMany = [affiliations: Affiliation, posts: BlogPost, replies: BlogReply]
	
	def beforeInsert = {
		salt = new Date().getTime()
		def passAndSalt = salt + "--" + password
		password = passAndSalt.encodeAsMD5()
	}

    static constraints = {
		salt nullable: true
		username unique: true
    }
}
