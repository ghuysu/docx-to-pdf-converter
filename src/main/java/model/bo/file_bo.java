package model.bo;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.pdf.PdfDocument;
import com.spire.*;

import model.bean.File;
import model.dao.*;

public class file_bo
{
	file_dao dao = new file_dao();
	public void createNewFileRecord(String email, String nameFile, InputStream fileData)
	{
		Date currentDate = new Date(System.currentTimeMillis());
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
        
        int index = nameFile.lastIndexOf('.');
        String file_name = nameFile.substring(0, index) + ".pdf";
        try
        {
        	Document document = new Document();
            document.loadFromStream(fileData, FileFormat.Docx);

            ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
            document.saveToStream(pdfStream, FileFormat.PDF);
        	
            File file = new File();
            file.setEmail(email);
            file.setNameFile(file_name);
            file.setDate(sqlDate);
            file.setFileData(pdfStream.toByteArray());
            dao.addNewFileRecord(file);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	
	public List<File> getFileList(String email)
	{
		return dao.getFileList(email);
	}
	
	public File getFileData(String id)
	{
		return dao.getFileData(id);
	}
	
	public void deleteFile(String id)
	{
		dao.deleteFile(id);
	}
}