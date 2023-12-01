<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
    if(session.getAttribute("email") == null) {
        response.sendRedirect("/LTM_Project/login.jsp");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <style>
    	html{
    		font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    	}
        /* CSS cho sidebar */
        .sidebar {
            height: 100%;
            width: 250px;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: #111;
            overflow-x: hidden;
            padding-top: 20px;
        }
        .sidebar a {
            padding: 6px 8px 6px 16px;
            text-decoration: none;
            font-size: 20px;
            color: #818181;
            display: block;
        }
        .sidebar a:hover {
            color: #f1f1f1;
        }
        .main {
            margin-left: 250px;
            padding: 20px;
        }
        /* CSS cho nút đăng xuất */
        .logout-btn {
            position: absolute;
            bottom: 20px;
        }
        .logout-btn a {
            text-decoration: none;
            color: #818181;
            font-size: 15px;
            margin-left: 60px;
        }
        .logout-btn a:hover {
            color: #f1f1f1;
        }
        .sidebar a.active {
            color: #fff;
            background-color: #4CAF50;
        }
        .content-area {
            background-color: #f4f4f4;
            padding: 20px;
        }
        .user-info-sidebar {
            color: #fff;
            margin-bottom: 30px;
            padding: 0 20px;
            font-size: 15px;
        }
        .main {
            margin-left: 250px;
            padding: 20px;
        }

        .content-area {
            background-color: #f4f4f4;
            padding: 20px;
        }

        .converter-container {
            background-color: #fff;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .converter-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group input[type="file"] {
            width: calc(100% - 22px);
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        .form-group input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 3px;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }

        .form-group input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <div class="user-info-sidebar">
            <p><%= session.getAttribute("email") %></p>
        </div>
        <a href="/LTM_Project/" class="active">File Converter</a>
        <a href="/LTM_Project/storage_controller">Storage</a>
        <div class="logout-btn">
            <a href="logout_controller">Log out</a>
        </div>
    </div>
    
    <div class="main">
        <div class="content-area">
			<div class="converter-container">
			    <h2>DOCX to PDF Converter</h2>
			    <form id="convertForm" action="file_controller" method="post" enctype="multipart/form-data">
			        <div class="form-group">
			            <input id="docxFileInput" type="file" name="fileData" accept=".docx" required>
			        </div>
			        <div class="form-group">
			            <input id="submitButton" type="submit" value="Convert to PDF">
			        </div>
			    </form>
			</div>
        </div>
    </div>
</body>
</html>
