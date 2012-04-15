<%@ page import="net.continuumsecurity.ropeytasks.User"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'user.label', default: 'User')}" />
<title>Login</title>
</head>
<body>
	<div class="content scaffold-edit" role="main">
		<h1>Login</h1>
		<br/>
		<br/>
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		
		<g:form method="post">
			
				<label for="username">Username</label>
				<g:textField name="username" value="${userInstance?.username}" />
			
		
				<label for="password">Password

				</label>
				<g:passwordField name="password" value="${userInstance?.password}" />
			</div>
			
			<fieldset class="buttons">
				<g:actionSubmit action="login"
					value="Login" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>
