package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bo.*;

@WebServlet("/login_controller")
public class login_controller extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		account_bo bo = new account_bo();
		int result = bo.isValidAccount(email, password);
		HttpSession session = request.getSession();
		if(result == -1)
		{
			request.setAttribute("error", "This email isn't registered");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
		else if(result == 0)
		{
			request.setAttribute("error", "Incorrect password");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
		else
		{
			session.removeAttribute("error");
			session.setAttribute("email", email);
			session.setAttribute("password", password);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/");
			rd.forward(request, response);
		}
	}

}
