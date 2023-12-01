package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.File;
import model.bo.file_bo;
import java.io.OutputStream;

@WebServlet("/download_controller")
public class download_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("file_id");;
        file_bo bo = new file_bo();
        File file = bo.getFileData(id);
        byte[] fileData = file.getFileData();
        String nameFile = file.getNameFile();

        if (fileData != null) {
        	response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + nameFile + "\"");
            response.setContentLength(fileData.length);

            OutputStream outputStream = response.getOutputStream();
            outputStream.write(fileData);
            outputStream.flush();
            outputStream.close();
        } else {
            System.out.println("Không có dữ liệu file");
        }
    }

}
