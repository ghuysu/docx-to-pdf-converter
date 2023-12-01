package model.bean;

import java.sql.Date;

public class File
{
	private int id;
	private String email;
	private String nameFile;
	private byte[] fileData;
	private Date date;
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setNameFile(String nameFile)
	{
		this.nameFile = nameFile;
	}
	
	public String getNameFile()
	{
		return nameFile;
	}
	
	public void setFileData(byte[] fileData)
	{
		this.fileData = fileData;
	}
	
	public byte[] getFileData()
	{
		return fileData;
	}
	
	public void setDate(Date date)
	{
		this.date = date;
	}
	
	public Date getDate()
	{
		return date;
	}
}