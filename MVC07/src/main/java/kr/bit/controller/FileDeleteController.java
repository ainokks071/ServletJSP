package kr.bit.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class FileDeleteController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx = request.getContextPath();
		
		int num = Integer.parseInt(request.getParameter("num"));  
		String fileName = request.getParameter("filename");
		fileName = URLEncoder.encode(fileName, "UTF-8"); 
		fileName = fileName.replace("+", " ");
		
		String UPLOAD_DIR = "file_repo";
		String uploadPath = request.getServletContext().getRealPath(UPLOAD_DIR);
//		1. 서버의 업로드 폴더에서 실제 파일 삭제하기.
		File file = new File(uploadPath + "/" + fileName);
		if(file.exists()) {
			file.delete();
		}

//		2. 데이터베이스 테이블의 filename = null로 바꾸기.
		MemberDAO dao = new MemberDAO();
		int count = dao.memberFileDelete(num);

		
		String nextPage = null;
		
		if(count > 0) {
			nextPage = "redirect:"+ctx+"/memberContent.do?num=" + num;
		} else {
			throw new ServletException("not delete");
		}
		
		return nextPage;
	}

}
