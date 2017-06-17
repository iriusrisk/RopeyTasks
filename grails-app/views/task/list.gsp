
<%@ page import="net.continuumsecurity.ropeytasks.Task" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'task.label', default: 'Task')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-task" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/task/list')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" controller="task" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-task" class="content scaffold-list" role="main">
			<g:form name="search" method="GET" action="search">
				<table><tr><td><g:textField name="q"/></td><td><g:submitButton name="search-button" value="Search" /></td></tr></table>
			</g:form>
			<g:if test="${query?.length() > 0}">
				Results for: ${raw(query)}
			</g:if>
			
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="detail" title="${message(code: 'task.detail.label', default: 'Detail')}" />
					
						<g:sortableColumn property="dueDate" title="${message(code: 'task.dueDate.label', default: 'Due Date')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'task.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'task.status.label', default: 'Status')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${taskInstanceList}" status="i" var="taskInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${taskInstance.id}">${fieldValue(bean: taskInstance, field: "detail")}</g:link></td>
					
						<td><g:formatDate date="${taskInstance.dueDate}" /></td>
					
						<td>${fieldValue(bean: taskInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: taskInstance, field: "status")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			
		</div>
	</body>
</html>
