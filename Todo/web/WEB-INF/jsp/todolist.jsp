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
		<div id="header">
			<div id="menuBar">
				<div class="menuItem"></div>
				<div class="menuItem"></div>
			</div>
			<!-- logo etc -->
		</div>
		<h1>Your Todo Tasks</h1>
		<p>
		Welcome back, ${user}<!-- ${pageScope.request.userPrincipal['name']} -->
		
		<table border="1">
		<tr>
		<th>id</th><th>Due Date</th><th>Description</th><th>Priority</th><th>Done?</th><th>Action</th>
		</tr>
		
		<c:forEach var="todo" items="${todolist}">
			<tr>
			<td>${todo.id}</td>
			<td>${todo.dueDate}</td>
			<td>${todo.description}</td>
			<td align="center">${todo.priority}</td>
			<td align="center">${todo.done}</td>
			<td align="center"><a href="edit?id=${todo.id}">Edit</a></td>
			</tr>
		</c:forEach>
		</table>
		<br>
		<a href="view">Refresh List</a>
		<br>
		<a href="add">Add New Todo</a> ...nobody ever has enough to do!
	</div>
</body>
</html>	
		