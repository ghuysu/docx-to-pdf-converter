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

@WebServlet("/signup_controller")
public class signup_controller extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		
		account_bo bo = new account_bo();
		int result = bo.createAccount(email, password, name);
		HttpSession session = request.getSession();
		if(result == -1)
		{
			request.setAttribute("errorSignup", "This email is registered");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/signup.jsp");
			rd.forward(request, response);
		}
		else if(result == 0)
		{
			request.setAttribute("errorSignup", "Signup unsuccessfully");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/signup.jsp");
			rd.forward(request, response);
		}
		else
		{
			session.removeAttribute("errorSignup");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
	}

}