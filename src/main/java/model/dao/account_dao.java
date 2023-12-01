package model.dao;

import java.sql.ResultSet;
import java.sql.Statement;

public class account_dao
{
	public boolean isExistEmail(String email)
	{
		try
	    {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3307/LTM_Project";
	        java.sql.Connection conn = java.sql.DriverManager.getConnection(url, "root", "");
	        String sql = "select * from account where email='" + email + "'";
	        Statement statement = conn.createStatement();
	        ResultSet rs = statement.executeQuery(sql);
	        if(rs.next())
	        	return true;
	        return false;
	    }
	    catch(Exception e)
	    {
	        System.out.println("Kết nối thất bại");
	        System.out.println("Lỗi: " + e.getMessage());
	        return false;
	    }
	}
	public boolean isExistAccount(String email, String password)
	{
		try
	    {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3307/LTM_Project";
	        java.sql.Connection conn = java.sql.DriverManager.getConnection(url, "root", "");
	        String sql = "select * from account where email='" + email + "' and password ='" + password + "'";
	        Statement statement = conn.createStatement();
	        ResultSet rs = statement.executeQuery(sql);
	        if(rs.next())
	        	return true;
	        return false;
	    }
	    catch(Exception e)
	    {
	        System.out.println("Kết nối thất bại");
	        System.out.println("Lỗi: " + e.getMessage());
	        return false;
	    }
	}
	
	public boolean createAccount(String email, String password, String name)
	{
		try
	    {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3307/LTM_Project";
	        java.sql.Connection conn = java.sql.DriverManager.getConnection(url, "root", "");
	        String sql = "insert into account values('" + email + "', '" + password + "', '" + name + "')";
	        Statement statement = conn.createStatement();
	        int rowsInserted = statement.executeUpdate(sql);
            return rowsInserted > 0;

	    }
	    catch(Exception e)
	    {
	        System.out.println("Kết nối thất bại");
	        System.out.println("Lỗi: " + e.getMessage());
	        return false;
	    }
	}
}