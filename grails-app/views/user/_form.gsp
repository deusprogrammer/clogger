<table class="minimal-table">
	<tr>
		<td>
			<label for="username">
				<g:message code="user.username.label" default="Username" />
				
			</label>
		</td>
		<td>
			<g:textField name="username" value="${userInstance?.username}"/>
		</td>
	</tr>
	
	<g:if test="${!editting}">
	<tr>
		<td>
			<label for="password">
				<g:message code="user.password.label" default="Password" />
				
			</label>
		</td>
		<td>
			<g:passwordField name="password" value="${userInstance?.password}"/>
		</td>
	</tr>
	</g:if>
	
	<tr>
		<td>
			<label for="emailAddress">
				<g:message code="user.emailAddress.label" default="Email Address" />
			</label>
		</td>
		<td>
			<g:textField name="emailAddress" value="${userInstance?.emailAddress}"/>
		</td>
	</tr>
	
	<tr>
		<td>
			<label for="firstName">
				<g:message code="user.firstName.label" default="First Name" />
				
			</label>
		</td>
		<td>
			<g:textField name="firstName" value="${userInstance?.firstName}"/>
		</td>
	</tr>
	
	<tr>
		<td>
			<label for="lastName">
				<g:message code="user.lastName.label" default="Last Name" />
			</label>
		</td>
		<td>
			<g:textField name="lastName" value="${userInstance?.lastName}"/>
		</td>
	</tr>
	
	<tr>
		<td class="avatar-td">
			<label for="avatar">
				<g:message code="user.avatar.label" default="Avatar" />
			</label>
		</td>
		<td class="avatar-td">
			<g:select data-baseUrl="${createLink(controller: 'avatar', action: 'get')}" name="avatar.id" id="avatar-select" from="${com.jpcf.blog.Avatar.list()*.name}" keys="${com.jpcf.blog.Avatar.list()*.id}" value="${userInstance?.avatar?.id}"/>
		</td>
		<td class="avatar-td">
			<% 
				def avatarId = userInstance?.avatar?.id
				if (!avatarId) {
					def avatarList = com.jpcf.blog.Avatar.list()
					if (avatarList.size > 0) {
						avatarId = avatarList[0].id
					}
				}
			 %>
			<img width="100px" height="100px" id="avatar-preview" src="${createLink(controller: 'avatar', action: 'get', id: avatarId)}" />
		</td>
	</tr>
</table>