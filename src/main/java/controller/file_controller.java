package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.bo.file_bo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
@WebServlet("/file_controller")
@MultipartConfig
public class file_controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    file_bo bo = new file_bo();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Part filePart = request.getPart("fileData");
        String fileName = filePart.getSubmittedFileName();
        InputStream fileContent = filePart.getInputStream();
        String email = (String) session.getAttribute("email");
        bo.createNewFileRecord(email, fileName, fileContent);
        response.sendRedirect("/LTM_Project/storage_controller");
    }
}
