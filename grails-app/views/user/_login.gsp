<div class="login-div">
	<g:if test="${session["user"]}">
		<p>Welcome back <g:currentUser />!  <g:link controller='user' action='logout'>Logout</g:link>?</p>
	</g:if>
	<g:else>
		<table class="login-table">
			<tr>
				<td width="1px">Login</td>
				<g:form controller="user" action="login">
					<td>
						<g:textField name="username" placeHolder="username"/>
						<g:passwordField name="password" placeHolder="password"/>
						<g:submitButton name="login" value="Login" />
						<g:link class="create" controller="user" action="create"><input type="button" value="Create User" /></g:link>
					</td>
				</g:form>
			</tr>
		</table>
	</g:else>
</div>