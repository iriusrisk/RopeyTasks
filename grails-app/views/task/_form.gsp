<%@ page import="net.continuumsecurity.ropeytasks.Task" %>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="task.name.label" default="Name" />
	</label>
	<g:textField name="name" value="${taskInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'detail', 'error')} ">
	<label for="detail">
		<g:message code="task.detail.label" default="Detail" />
		
	</label>
	<g:textField name="detail" value="${taskInstance?.detail}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'dueDate', 'error')} required">
	<label for="dueDate">
		<g:message code="task.dueDate.label" default="Due Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dueDate" precision="day"  value="${taskInstance?.dueDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="task.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="status" required="" value="${fieldValue(bean: taskInstance, field: 'status')}"/>
</div>

