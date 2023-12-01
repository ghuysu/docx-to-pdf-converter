package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.bean.File;

public class file_dao {

    public void addNewFileRecord(File file) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3307/LTM_Project";
            conn = java.sql.DriverManager.getConnection(url, "root", "");

            String sql = "INSERT INTO file (email, file_name, file_data, date) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, file.getEmail());
            pstmt.setString(2, file.getNameFile());
            pstmt.setBytes(3, file.getFileData());
            pstmt.setDate(4, file.getDate());
            pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Kết nối thất bại");
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
    
    public List<File> getFileList(String email)
    {
    	try
	    {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3307/LTM_Project";
	        java.sql.Connection conn = java.sql.DriverManager.getConnection(url, "root", "");
	        String sql = "select file_id, file_name, date from file where email='" + email + "'";
	        Statement statement = conn.createStatement();
	        ResultSet rs = statement.executeQuery(sql);
	        List<File> fileList = new ArrayList<>();
	        while (rs.next()) {
                File file = new File();
                file.setId(rs.getInt("file_id"));
                file.setNameFile(rs.getString("file_name"));
                file.setDate(rs.getDate("date"));
                fileList.add(file);
            }
	        return fileList;
	    }
	    catch(Exception e)
	    {
	        System.out.println("Kết nối thất bại");
	        System.out.println("Lỗi: " + e.getMessage());
	        return null;
	    }
    }
    
    public File getFileData(String id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3307/LTM_Project";
            Connection conn = DriverManager.getConnection(url, "root", "");
            String sql = "SELECT file_name, file_data FROM file WHERE file_id=?";
            
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, id);
            
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
            	File file = new File();
            	file.setNameFile(rs.getNString("file_name"));
                file.setFileData(rs.getBytes("file_data"));
                return file;
            }
            return null;
        } catch (Exception e) {
            System.out.println("Kết nối thất bại");
            System.out.println("Lỗi: " + e.getMessage());
            return null;
        }
    }
    
    public void deleteFile(String id)
    {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3307/LTM_Project";
            Connection conn = DriverManager.getConnection(url, "root", "");
            String sql = "DELETE FROM file WHERE file_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, id);

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Kết nối thất bại");
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

}
