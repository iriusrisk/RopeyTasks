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
		<h1>Recover password</h1>
		<br/> <br/>
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>

		<g:form method="post">
			<label for="username">Enter your email address</label>
			<g:textField name="email" />

			
			<recaptcha:recaptcha/>
			
			<fieldset class="buttons">
				<g:actionSubmit action="recover" value="Recover" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>
