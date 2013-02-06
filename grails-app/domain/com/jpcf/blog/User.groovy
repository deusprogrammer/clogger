package com.jpcf.blog

class User {
	String username
	String password
	String salt
	
	String firstName
	String lastName
	String emailAddress
	
	Group userGroup = Group.REGISTERED
	Avatar avatar
	
	static hasMany = [affiliations: Affiliation, posts: BlogPost, replies: BlogReply]
	
	def beforeInsert = {
		salt = new Date().getTime()
		def passAndSalt = salt + "--" + password
		password = passAndSalt.encodeAsMD5()
	}

    static constraints = {
		salt nullable: true
		username unique: true
		emailAddress nullable: true, email: true
		avatar nullable: true
    }
}
