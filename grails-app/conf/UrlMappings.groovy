class UrlMappings {

	static mappings = {
		"/$controller/$id?/$action?"{
			constraints {
				// apply constraints here
			}
		}
        
        "/$controller/$action?"{
            constraints {
                // apply constraints here
            }
        }
        
        "/user/$id/blogs" (controller: "blogPost", action: "listByUser")

		"/"(controller: "blogPost", action:"list")
		"500"(view:'/error')
	}
}
