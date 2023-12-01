package model.bo;
import model.dao.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.*;

public class account_bo extends HttpServlet {
	account_dao dao = new account_dao();
	
	public int isValidAccount(String email, String password)
	{
		if(!dao.isExistEmail(email))
			return -1;
		if(dao.isExistAccount(email, password) == true)
			return 1;
		else
			return 0;
	}
	
	public int createAccount(String email, String password, String name)
	{
		if(dao.isExistEmail(email))
			return -1;
		boolean result = dao.createAccount(email, password, name);
		if(result == true)
			return 1;
		else
			return 0;
	}
}