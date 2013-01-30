package com.jpcf.blog

class StringUtilService {

    def shorten(String text, int shortenTo) {
		def words = text.tokenize(" +")
		
		println "WORDS:      " + words.size
		println "SHORTEN TO: " + shortenTo
		
		def condensed = ""
		
		for (def i = 0; i < shortenTo && i < words.size; i++) {
			condensed += words[i]
			if (i < shortenTo - 1) {
				condensed += " "
			}
		}
		
		condensed.replace(".", ". ")
		
		if (words.size > shortenTo) {
			condensed += "..."
		}
		
		return condensed
    }
}
