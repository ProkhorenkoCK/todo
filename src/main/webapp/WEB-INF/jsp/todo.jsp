<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TODO</title>
    <link type="text/css" rel="stylesheet" href="<c:url value='../css/style.css' />"/>
    <script src="//code.jquery.com/jquery-2.1.4.js"></script>
    <script src="<c:url value='../js/todo.js' />" ></script>
</head>
<body>
<input id="todo" type="text" placeholder="What needs to be done?"/>
<button id="addToDoButton">Add</button>
<table id="content">
</table>
</body>
</html>
