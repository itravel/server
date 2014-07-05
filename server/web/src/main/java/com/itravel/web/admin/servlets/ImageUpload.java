package com.itravel.web.admin.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.common.io.ByteSink;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

/**
 * Servlet implementation class EventsSave
 */
@WebServlet("/admin/imageUpload")
public class ImageUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File _temp = File.createTempFile("1afdafdaf", ".jpg");
		System.out.println(_temp.toString());
		ByteSink bs = Files.asByteSink(_temp);
		byte[] _tempBytes = new byte[1024];

		// Create a new file upload handler
//		ServletFileUpload upload = new ServletFileUpload();
//		try {
//			upload.parseRequest(request);
//		} catch (FileUploadException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		InputStream is = request.getInputStream();
		while(is.read(_tempBytes)!=-1){
			bs.write(_tempBytes);
		}
		is.close();
		
		
	}

}
