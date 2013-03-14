<%@ page import="com.jpcf.blog.User" %>
<%@ page import="com.jpcf.blog.Group" %>
<g:if test="${session['user']}">
	<% def user = User.get(session['user']) %>
	<div class="nav" role="navigation">
		<ul>
			<li><g:link class="list" controller="blogPost" action="list">Home</g:link></li>
			<g:if test="${user.userGroup == Group.POWERUSER || user.userGroup == Group.SUPERUSER}">
				<li><g:link class="list" controller="blogPost" action="listByUser" id="${user.username}">My Blogs</g:link></li>
				<li><g:link class="create" controller="blogPost" action="create">New Blog</g:link></li>
				<li><g:link class="edit" controller="admin" action="list">Admin Menu</g:link></li>
			</g:if>
			<li><g:link class="edit" controller="user" action="show" id="${session['user']}">Account</g:link></li>
		</ul>
	</div>
</g:if>