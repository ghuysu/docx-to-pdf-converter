<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat"%>
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
    <title>Storage</title>
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
        
        .file-list {
            margin-top: 20px;
        }
        .file-item {
	        margin-bottom: 10px;
	        padding: 10px;
	        border: 1px solid #ccc;
	        border-radius: 5px;
	        position: relative;
	    }
	
	    .file-item .delete-btn {
	        position: absolute;
	        top: 10px;
	        right: 10px;
	        padding: 6px 12px;
	        background-color: #f44336;
	        color: white;
	        border: none;
	        border-radius: 3px;
	        cursor: pointer;
	        transition: background-color 0.3s ease;
	    }
	
	    .file-item .delete-btn:hover {
	        background-color: #d32f2f;
	    }
        
        .form-group input[type="submit"] {
        display: inline-block;
        padding: 10px 20px;
        text-align: center;
        text-decoration: none;
        background-color: #4CAF50; /* Màu xanh đẹp */
        color: white;
        border-radius: 5px;
        border: none;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    
    .form-group input[type="submit"]:hover {
        background-color: #45a049; /* Màu xanh đậm khi rê chuột vào */
    }
        
    </style>
</head>
<body>
    <div class="sidebar">
        <div class="user-info-sidebar">
            <p><%= session.getAttribute("email") %></p>
        </div>
        <a href="/LTM_Project/">File Converter</a>
        <a href="/LTM_Project/storage_controller" class="active">Storage</a>
        <div class="logout-btn">
            <a href="logout_controller">Log out</a>
        </div>
    </div>
    
        <div class="main">
        <div class="content-area">
            <h2>File List</h2>
            <div class="file-list">
                <% 
                    List<File> fileList = (List<File>)request.getAttribute("fileList");
                    if (fileList != null && !fileList.isEmpty()) {
                        for (File file : fileList) {
                        	java.sql.Date sqlDate = file.getDate(); // Lấy java.sql.Date từ đối tượng File
                        	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            String formattedDate = dateFormat.format(sqlDate);
                %>	
                            <div class="file-item">
                                <h3><%= file.getNameFile() %></h3>
                                <p>Date: <%= formattedDate %></p>
                                <form class="form-group" action="/LTM_Project/download_controller" method="post">
                                	<input name="file_id" value="<%= String.valueOf(file.getId()) %>" type="hidden">
                                	<input value="Download" type="submit">
                                </form>
                                <button class="delete-btn" onclick="deleteFile('<%= String.valueOf(file.getId()) %>')">Delete</button>
                            </div>
                <% 
                        }
                    } else {
                %>
                        <p>No files available.</p>
                <% 
                    }
                %>
            </div>
        </div>
    </div>
    <script>
        function deleteFile(fileId) {
            var confirmDelete = confirm("Are you sure you want to delete this file?");
            if (confirmDelete) {
                // Nếu người dùng xác nhận muốn xóa, chuyển hướng đến controller xóa file
                window.location.href = "/LTM_Project/delete_controller?file_id=" + fileId;
            }
        }
    </script>
</body>
</html>
