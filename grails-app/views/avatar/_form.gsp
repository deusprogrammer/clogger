<%@ page import="com.jpcf.blog.Avatar" %>

<table>
	<tr>
		<td>Name</td>
		<td><g:textField name="name" value="${avatarInstance?.name}"/></td>
	</tr>
	<tr>
		<td>Choose File</td>
		<td><input type="file" id="fileUpload" name="avatarImage" /></td>
	</tr>
</table>

