<%@ page import="com.jpcf.blog.Avatar" %>

<table>
	<tr>
		<td>Name</td>
		<td><g:textField name="name" value="${avatarInstance?.name}"/></td>
	</tr>
	<tr>
		<g:if test="${avatarInstance.name}">
			<td>Image</td>
			<td><img src="${createLink(action: 'get', id: avatarInstance?.id)}" /></td>
		</g:if>
		<g:else>
			<td>Choose File</td>
			<td><input type="file" id="fileUpload" name="avatarImage" /></td>
		</g:else>
	</tr>
</table>

