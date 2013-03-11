<div class="blog-div">
	<div class="blog-inner-div">
		<div class="blog-title-div"><g:link action="show" id="${post.id}">${post.title}</g:link></div>
		<div class="blog-header-div">Posted by ${post.owner.username} on ${post.dateCreated}.  Last updated: ${post.lastUpdated}</div>
		<div class="blog-content-container-div"><div class="blog-avatar-div"><img width="200px" width="200px" src="${createLink(controller: "avatar", action: "get", id: post.owner.avatar?.id)}" /></div><div class="blog-content-div"><g:contentPreview shortenTo="100">${post.content}</g:contentPreview></div></div>
	</div>
	<div class="blog-replies-div">
		<% 
			def replies = post.replies as List
			def omitted = replies.size
			if (replies.size > 2) {
				replies = replies[0..1]
			}
			omitted = omitted - replies.size
		%>
		<g:each in="${replies}" var="reply">
			<div class="blog-reply-div">
				<div class="blog-header-div">Posted by ${reply.owner.username} on ${reply.dateCreated}.  Last updated: ${reply.lastUpdated}</div>
				<div class="blog-content-container-div"><div class="blog-avatar-div"><img width="100px" height="100px" src="${createLink(controller: "avatar", action: "get", id: reply.owner.avatar?.id)}" /></div><div class="blog-content-div"><g:contentPreview shortenTo="100">${reply.content}</g:contentPreview></div></div>
			</div>
		</g:each>
		<g:if test="${omitted > 0}">
			<div class="blog-omitted-div">${omitted} replies omitted.</div>
		</g:if>
	</div>
</div>