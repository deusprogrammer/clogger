package com.jpcf.blog

enum Group {
	UNREGISTERED ("Unregistered", "r---"),
	REGISTERED   ("Registered",   "rw--"),
	POWERUSER    ("Power User",   "rwx-"),
	SUPERUSER    ("Super User",   "rwxa")

	String name
	String mask
	
	String toString() {
		return name
	}
	
	def Group(def name, def mask) {
		this.name = name
		this.mask = mask
	}
}
