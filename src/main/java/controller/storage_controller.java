package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.File;
import model.bo.file_bo;

@WebServlet("/storage_controller")
public class storage_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		file_bo bo = new file_bo();
		HttpSession session = request.getSession();
		List<File> fileList = bo.getFileList((String)(session.getAttribute("email")));
		request.setAttribute("fileList", fileList);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/storage.jsp");
		rd.forward(request, response);
	}

}
