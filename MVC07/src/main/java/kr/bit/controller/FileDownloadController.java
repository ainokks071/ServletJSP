package kr.bit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String fileName = request.getParameter("filename");
//		최종 업로드 폴더(업로드되는 파일들의 저장폴더)의 이름
		String UPLOAD_DIR = "file_repo";
// 		Users/kks/dev-study/ServletJSP/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MVC07/file_repo
		String uploadPath = request.getServletContext().getRealPath(UPLOAD_DIR);
//		업로드 폴더의 실제경로로 File객체 만들기
		File downloadFile = new File(uploadPath + "/" + fileName);
//		System.out.println(downloadFile.getPath()); : 파일 경로
//		System.out.println(downloadFile.getName()); : 파일 이름 
		
		fileName = URLEncoder.encode(fileName, "UTF-8");
		fileName = fileName.replace("+", " ");
		
		response.setContentLength((int)downloadFile.length());
		response.setContentType("application/x-msdownload;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+fileName+";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		
		FileInputStream in = new FileInputStream(downloadFile);
		OutputStream out = response.getOutputStream();
		byte[] buffer=new byte[1024];
		while(true) {
			int count=in.read(buffer);
			if(count==-1) {
				break;
			}
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
		
		return null;
	}

}
