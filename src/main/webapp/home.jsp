<%@page import="models.User"%>
<%@page import="java.util.List"%>
<%@page import="daos.TaskDao"%>
<%@page import="models.Task"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	User user=(User)session.getAttribute("user");
	List<Task> tasks=new TaskDao().listTasks(user.getUid());
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
        

<nav class="px-10 bg-white border-gray-200 dark:bg-gray-900 dark:border-gray-700 p-4 flex justify-between items-center">
    <!-- Company Name -->
    <span class="text-xl font-semibold text-gray-900 dark:text-white">Welcome <%=user.getName() %></span>

    <!-- Logout Button -->
    <a class="bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600" href="logout">Logout</a>
</nav>


        <div class="form p-10 text-white">
            <form action="saveData" method="post">
                <input class="block px-5 py-2 bg-zinc-800 w-full rounded-md" type="text" placeholder="Title goes here" name="title" required>
                <textarea class="block px-5 py-2 bg-zinc-800 resize-none w-full rounded-md mt-2" placeholder="Write your note here" name="task" required></textarea>
                <input type="hidden" value=<%=user.getUid() %> name="uid">
	<div class="relative max-w-sm">
		<input type="date" id="taskDate" name="taskDate" class="mt-2 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required>
	       <div class="form-group space-y-2">
  <label for="priority" class="block text-sm font-medium text-gray-700">Priority</label>
  <select id="priority" name="priority" required class="mt-2 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required>
    <option value="1" selected>Low</option>
    <option value="2">Medium</option>
    <option value="3">High</option>
  </select>
</div>       
	</div>
	<input class="mt-2 bg-blue-600 px-5 py-2 rounded-md" type="submit" value="Create Task">
	</form>
</div>
<div class="tasks p-10 flex gap-3 flex-wrap">
        <%if(tasks.size()>0){%>
            <%for(Task task:tasks){%>
                <div class="task w-72 px-3 py-4 rounded-md bg-zinc-800">
                    <h1 class="text-white text-3xl tracking-tighter"><%= task.getTitle()%></h1>
                    <div class="flex justify-between">
                        <p class="tracking-tighter <% 
        				if (task.getPriority() == 1) { out.print("text-green-500"); }
        				else if (task.getPriority() == 2) { out.print("text-orange-500"); }
        				else if (task.getPriority() == 3) { out.print("text-red-500"); }
    					%>">
                            <% 
                                if (task.getPriority()==1) {
                                    out.print("Low Priority");
                                } else if (task.getPriority()==2) {
                                    out.print("Medium Priority");
                                } else if (task.getPriority()==3) {
                                    out.print("High Priority");
                                }
                            %>
                        </p>
                        <p class="text-white tracking-tighter"><%= task.getDate() %></p>
                    </div>
                    <div class="flex w-full justify-between items-center mt-3">
                        <form action="task.jsp" method="post">
                            <button class="text-white tracking-tighter" type="submit" value="<%= task.getId()%>" name="tid">Read More...</button>
                        </form>
                    </div>
                    <form action="deleteData" method="post" onsubmit="return confirmDelete(event, this)">
                        <input class="mt-2 bg-red-600 px-5 py-2 rounded-md text-white" type="hidden" value="<%= task.getId()%>" name="id">
                        <button type="submit" class="mt-2 bg-red-600 px-5 py-2 rounded-md text-white">Delete Task</button>
                    </form>
                </div>
            <%}%>
        <%}else{%>
            <h3 class="text-zinc-600">No tasks yet...</h3>
        <%}%>
    </div>
    <script>
	    function confirmDelete(event, form) {
	        event.preventDefault();
	        if (confirm("Are you sure you want to delete this task?")) {
	            form.submit();
	        }
	    }
	    document.addEventListener("DOMContentLoaded", function() {
	        let today = new Date().toISOString().split('T')[0]; 
	        document.getElementById("taskDate").value = today;
	    });
    </script>
</body>
</html>