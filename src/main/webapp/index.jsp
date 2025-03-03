<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String loginMsg="";
	String registerMsg="";
	String color="";
	Boolean showRegisterForm=false;
	Object login=session.getAttribute("msg");
	Object registered=session.getAttribute("registered");
	if(login!=null){
		loginMsg=(String) login;
		session.removeAttribute("msg");
	}
	if(registered!=null){
		if((Boolean) registered){
			registerMsg="Registration Successfull";
			color="green-500";
		}
		else{
			registerMsg="Registration Failed";
			color="red-500";
		}
		showRegisterForm=true;
		session.removeAttribute("registered");
	}
	
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login & Registration</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="flex items-center justify-center min-h-screen bg-zinc-900">
    <div class="w-full max-w-md p-8 bg-zinc-800 rounded-md shadow-md">
        <h2 class="text-white text-3xl text-center">Login</h2>
        <form action="login" method="post" class="mt-4">
            <input class="block w-full px-4 py-2 bg-zinc-700 text-white rounded-md focus:outline-none" type="email" placeholder="Email" name="email" required>
            <input class="block w-full px-4 py-2 bg-zinc-700 text-white rounded-md mt-2 focus:outline-none" type="password" placeholder="Password" name="password" required>
            <button class="mt-4 w-full bg-blue-600 px-4 py-2 rounded-md text-white">Login</button>
        </form>
        <p class="text-white text-center mt-4">Don't have an account? <a href="#" onclick="toggleForm()" class="text-blue-400">Register</a></p>
        <p class="text-red-500 text-center mt-4"><%= loginMsg%></p>
    </div>
    
    <div class="w-full max-w-md p-8 bg-zinc-800 rounded-md shadow-md hidden" id="registerForm">
        <h2 class="text-white text-3xl text-center">Register</h2>
        <form action="register" method="post" class="mt-4">
            <input class="block w-full px-4 py-2 bg-zinc-700 text-white rounded-md focus:outline-none" type="text" placeholder="Name" name="name" required>
            <input class="block w-full px-4 py-2 bg-zinc-700 text-white rounded-md mt-2 focus:outline-none" type="email" placeholder="Email" name="email" required>
            <input class="block w-full px-4 py-2 bg-zinc-700 text-white rounded-md mt-2 focus:outline-none" type="password" placeholder="Password" name="password" required>
            <button class="mt-4 w-full bg-green-600 px-4 py-2 rounded-md text-white">Register</button>
        </form>
        <p class="text-white text-center mt-4">Already have an account? <a href="#" onclick="toggleForm()" class="text-blue-400">Login</a></p>
        <p class="text-<%=color%> text-center mt-4"><%= registerMsg%></p>
    </div>
    
    <script>
        function toggleForm() {
            document.querySelector(".w-full.max-w-md").classList.toggle("hidden");
            document.getElementById("registerForm").classList.toggle("hidden");
        }
        <%if(showRegisterForm){%>
        	window.onload=function(){
        		toggleForm()
        	}
        <%}%>
    </script>
</body>
</html>
