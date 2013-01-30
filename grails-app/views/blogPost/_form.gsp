<%@ page import="com.jpcf.blog.BlogPost" %>

<div class="fieldcontain ${hasErrors(bean: blogPostInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="blogPost.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${blogPostInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: blogPostInstance, field: 'content', 'error')} ">
	<label for="content">
		<g:message code="blogPost.content.label" default="Content" />
		
	</label>
	<g:textArea name="content" cols="40" rows="5" maxlength="4096" value="${blogPostInstance?.content}"/>
</div>

</div>

