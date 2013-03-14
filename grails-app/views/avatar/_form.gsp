<%@ page import="com.jpcf.blog.Avatar" %>

<table>
	<tr>
		<td>Name</td>
		<td><g:textField name="name" value="${avatarInstance?.name}"/></td>
	</tr>
	<tr>
		<g:if test="${!avatarInstance}">
			<td>Choose File</td>
			<td><input type="file" id="fileUpload" name="avatarImage" /></td>
		</g:if>
		<g:else>
		<td>Image</td>
		<td><img src="${createLink(action: 'get', id: avatarInstance?.id)}" /></td>
		</g:else>
	</tr>
</table>

