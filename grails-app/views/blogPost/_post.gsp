<div class="blog-div">
	<div class="blog-inner-div">
		<div class="blog-title-div">${post.title}</div>
		<div class="blog-header-div">Posted by ${post.owner.username} on ${post.dateCreated}.  Last updated: ${post.lastUpdated}</div>
		<div class="blog-content-container-div"><div class="blog-avatar-div"><img width="200px" height="200px" src="${createLink(controller: "avatar", action: "get", id: post.owner.avatar?.id)}" /></div><div class="blog-content-div">${post.content}</div></div>
	</div>
	<div class="blog-replies-div">
		<g:each in="${post.replies}" var="reply">
			<div class="blog-reply-div">
				<div class="blog-header-div">Posted by ${reply.owner.username} on ${reply.dateCreated}.  Last updated: ${reply.lastUpdated}</div>
				<div class="blog-content-container-div"><div class="blog-avatar-div"><img width="100px" height="100px" src="${createLink(controller: "avatar", action: "get", id: reply.owner.avatar?.id)}" /></div><div class="blog-content-div">${reply.content}</div></div>
			</div>
		</g:each>
		<div class="blog-reply-div">
			<h3>Reply</h3>
			<div class="blog-content-div">
				<g:form controller="blogReply" action="save">
					<g:render template="/blogReply/form" />
					<g:submitButton name="submit" value="Submit" />
				</g:form>
			</div>
		</div>
	</div>
</div>