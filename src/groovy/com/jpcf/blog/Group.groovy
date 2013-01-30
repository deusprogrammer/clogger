package com.jpcf.blog

enum Group {
	UNREGISTERED ("Unregistered", "r--"),
	REGISTERED   ("Registered",   "rw-"),
	POWERUSER    ("Power User",   "rwx")

	String name
	String mask
	
	def Group(def name, def mask) {
		this.name = name
		this.mask = mask
	}
}
