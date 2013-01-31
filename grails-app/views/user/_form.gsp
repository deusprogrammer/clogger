<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} ">
	<label for="username">
		<g:message code="user.username.label" default="Username" />
		
	</label>
	<g:textField name="username" value="${userInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} ">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		
	</label>
	<g:passwordField name="password" value="${userInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'emailAddress', 'error')} ">
	<label for="emailAddress">
		<g:message code="user.emailAddress.label" default="Email Address" />
		
	</label>
	<g:textField name="emailAddress" value="${userInstance?.emailAddress}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'firstName', 'error')} ">
	<label for="firstName">
		<g:message code="user.firstName.label" default="First Name" />
		
	</label>
	<g:textField name="firstName" value="${userInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastName', 'error')} ">
	<label for="lastName">
		<g:message code="user.lastName.label" default="Last Name" />
		
	</label>
	<g:textField name="lastName" value="${userInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'avatar', 'error')}">
	<label for="userGroup">
		<g:message code="user.avatar.label" default="Avatar" />
	</label>
	<g:select name="avatar.id" from="${com.jpcf.blog.Avatar.list()*.name}" keys="${com.jpcf.blog.Avatar.list()*.id}" value="${userInstance?.avatar?.id}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'userGroup', 'error')} required">
	<label for="userGroup">
		<g:message code="user.userGroup.label" default="Group" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="userGroup" from="${com.jpcf.blog.Group?.values()}" keys="${com.jpcf.blog.Group.values()*.name()}" required="" value="${userInstance?.userGroup?.name()}"/>
</div>