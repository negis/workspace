<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="todostyles.css" type="text/css" />
<title>Your Todo Tasks</title>
</head>
<body>
<div id="main">

	<h1>Add/Edit Todo</h1>
	
	
	</p>
	<form method="POST">
		<table border="0">
		<c:if test="${not empty todo.id}">
		<tr>
		<td align="right">Id:</td> <td><b>${todo.id}</b> <input type="hidden" name="id" value="${todo.id}"/> </td>
		</tr>
		</c:if>
		<tr>
		<td align="right">Description:</td> <td> <input width="30" name="description" value="${todo.description}"/></td>
		</tr>
		<tr>
		<td align="right">Date due:</td> <td> <input type="date" width="12" name="duedate" value="${todo.dueDate}"/></td>
		</tr>
		<tr>
		<td align="right">Priority:</td> <td> <input type="range"  min="1"  max="5" title="(1=high,5=low)" name="priority" value="${todo.priority}"/> </td>
		</tr>
		<tr>
		<td align="right">Done?</td> <td> <input type="checkbox" name="done" ${todo.done? "checked=true" : "" } /> </td>
		</tr>
		<tr>
		<td colspan="2" align="center"> <input type="submit" value="Submit"  /> </td>
		</tr>
		</table>
	</form>
		
</div>
</body>
</html>	
		