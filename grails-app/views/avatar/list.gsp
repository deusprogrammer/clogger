
<%@ page import="com.jpcf.blog.Avatar" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'avatar.label', default: 'Avatar')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-avatar" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="home" controller="blogPost" action="list">Home</g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-avatar" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="name" title="${message(code: 'avatar.name.label', default: 'Name')}" />
						<th>Image</th>
						<g:sortableColumn property="dateCreated" title="${message(code: 'avatar.dateCreated.label', default: 'Date Created')}" />
						<g:sortableColumn property="lastUpdated" title="${message(code: 'avatar.lastUpdated.label', default: 'Last Updated')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${avatarInstanceList}" status="i" var="avatarInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td class="avatar-td"><g:link action="edit" id="${avatarInstance.id}">${fieldValue(bean: avatarInstance, field: "name")}</g:link></td>
						
						<td class="avatar-td"><img width="100px" height="100px" src="${createLink(controller: 'avatar', action:'get', id: avatarInstance.id)}" /></td>
						
						<td class="avatar-td"><g:formatDate date="${avatarInstance.dateCreated}" /></td>
					
						<td class="avatar-td"><g:formatDate date="${avatarInstance.lastUpdated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${avatarInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
