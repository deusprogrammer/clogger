package com.jpcf.blog

class Avatar {
	String name
	String filePath
	Date dateCreated
	Date lastUpdated

    static constraints = {
		filePath nullable: true
    }
}
