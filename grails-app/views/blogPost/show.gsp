
<%@ page import="com.jpcf.blog.BlogPost" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="blog">
		<g:set var="entityName" value="${message(code: 'blogPost.label', default: 'BlogPost')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<g:render template="post" model="${[post: blogPostInstance]}" />
	</body>
</html>
