package com.kh.fm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class FileUploadController
 */
@WebServlet("/upload")
public class FileUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// * 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		
		int maxSize = 10 * 1024 * 1024; 	// 10mbyte
		String savePath = request.getSession().getServletContext().getRealPath("/resources/upfiles/");
		
		MultipartRequest multiRequest = new MultipartRequest(request
															, savePath
															, maxSize
															, "UTF-8"
															, new MyFileRenamePolicy());
		// multipart/form-data 방식으로 요청된 경우 MultipartRequest 객체로 요청 데이터 추출!
		String title = multiRequest.getParameter("title");
		String file = "";
		
		if (multiRequest.getOriginalFileName("upfile") != null) {
			// 첨부파일이 있는 경우
			file = multiRequest.getOriginalFileName("upfile");
		}
		
		System.out.println("title: " + title + ", file: "+ file);
		
		response.sendRedirect(request.getContextPath());
		
	}

}
