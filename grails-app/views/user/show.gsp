
<%@ page import="com.jpcf.blog.User" %>

<%
	def user = null
	def group = com.jpcf.blog.Group.UNREGISTERED
	if (session["user"]) {
		user = User.get(session["user"])
	}
	
	if (user) {
		group = user.userGroup
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-user" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list user">
			
				<li class="fieldcontain">
					<img width="100px" height="100px" src="${createLink(controller: 'avatar', action: 'get', id: userInstance?.avatar?.id)}" />
				</li>
			
				<g:if test="${userInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="user.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${userInstance}" field="username"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${userInstance?.userGroup}">
				<li class="fieldcontain">
					<span id="group-label" class="property-label"><g:message code="user.userGroup.label" default="Group" /></span>
					
						<span class="property-value" aria-labelledby="userGroup-label"><g:fieldValue bean="${userInstance}" field="userGroup"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="user.firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${userInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${userInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="user.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${userInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${userInstance?.id}" />
					<g:link class="edit" action="changePassword" id="${userInstance?.id}">Change Password</g:link>
					<g:link class="edit" action="edit" id="${userInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:if test="${group == com.jpcf.blog.Group.SUPERUSER}">
						<g:if test="${userInstance?.userGroup == com.jpcf.blog.Group.REGISTERED}">
							<g:link class="edit" action="promoteTo" id="${userInstance?.id}" params="${[level: 'poweruser']}">Enable Blogging</g:link>
						</g:if>
						<g:elseif test="${userInstance?.userGroup == com.jpcf.blog.Group.POWERUSER}">
							<g:link class="edit" action="promoteTo" id="${userInstance?.id}" params="${[level: 'superuser']}">Promote to Admin</g:link>
						</g:elseif>
					</g:if>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
