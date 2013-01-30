
<%@ page import="com.jpcf.blog.BlogPost" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="blog">
		<g:set var="entityName" value="${message(code: 'blogPost.label', default: 'BlogPost')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-blogPost" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="list-blogPost" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
				<g:each in="${blogPostInstanceList}" var="post">
					<g:render template="postPreview" model="${[post: post]}" />
				</g:each>
			<div class="pagination">
				<g:paginate total="${blogPostInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
