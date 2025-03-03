<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="daos.TaskDao"%>
<%@page import="models.Task"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int id=Integer.parseInt(request.getParameter("tid"));
	Task task=new TaskDao().getTaskById(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>To do List</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
	
	<div class="main w-full min-h-screen bg-zinc-900">
        <h1 class="text-center text-white text-3xl">🗒 To Do List</h1>
        <div class="form p-10 text-white">
            <form action="updateData" method="post">
            	<input type="hidden" name="id" value=<%=task.getId() %>>
                <input class="block px-5 py-2 bg-zinc-800 w-full rounded-md" type="text" name="title" value=<%=task.getTitle()%>>
                <textarea id="task" class="block px-5 py-2 bg-zinc-800 resize-none w-full rounded-md mt-2" name="task"><%=task.getTask() %></textarea>
                
	<div class="relative max-w-sm">
		<%
			java.sql.Date sqlDate = task.getDate();  // task.getDate() returns java.sql.Date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String taskDate = sdf.format(sqlDate);  // Converts SQL Date to String in the format yyyy-MM-dd
		%>
		<input type="date" id="taskDate" name="taskDate" value="<%= taskDate %>" placeholder="<%= taskDate %>"
       class="mt-2 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
	       <div class="form-group space-y-2">
  <label for="priority" class="block text-sm font-medium text-gray-700">Priority</label>
  <select id="priority" name="priority" required class="mt-2 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
    <option value="1">Low</option>
    <option value="2">Medium</option>
    <option value="3">High</option>
  </select>
</div>       
	</div>
	<input class="mt-2 bg-blue-600 px-5 py-2 rounded-md" type="submit" value="Update Task">
	</form>
	<form action="deleteData" method="post" onsubmit="return confirmDelete(event, this)">
        <input class="mt-2 bg-red-600 px-5 py-2 rounded-md text-white" type="hidden" value="<%= task.getId()%>" name="id">
        <button class="mt-2 bg-red-600 px-5 py-2 rounded-md text-white">Delete Task</button>
	</form>
</div>
<script>
	const prioritySelect=document.getElementById('priority');
	const priorityValue=<%=task.getPriority()%>;
	if(prioritySelect){
		prioritySelect.value=priorityValue.toString();
	}
	function confirmDelete(event, form) {
        event.preventDefault();
        if (confirm("Are you sure you want to delete this task?")) {
            form.submit();
        }
    }
</script>
</body>
</html>