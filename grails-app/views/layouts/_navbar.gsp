<%@ page import="com.jpcf.blog.User" %>
<%@ page import="com.jpcf.blog.Group" %>
<g:if test="${session['user']}">
	<% def user = User.get(session['user']) %>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			<li><g:link class="list" controller="blogPost" action="list">List Blogs</g:link></li>
			<g:if test="${user.userGroup == Group.POWERUSER || user.userGroup == Group.SUPERUSER}">
				<li><g:link class="create" controller="blogPost" action="create">New Blog</g:link></li>
			</g:if>
			<li><g:link class="edit" controller="user" action="show" id="${session['user']}">Account</g:link></li>
		</ul>
	</div>
</g:if>