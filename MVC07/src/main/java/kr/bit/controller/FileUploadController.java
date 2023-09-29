package kr.bit.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

// 파일 업로드 시 필요한 API : Apache commons-fileupload, Apache commons-io (from mvnrepository)

// memberRegister.jsp(회원가입화면) : 클라이언트가 file첨부하고, 가입하면 FormData객체 넘어온다 !
// request객체에 담겨서 요청되겠지. -> 파일을 어떻게 추출할것인가.
public class FileUploadController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		한글 깨짐 방지.
//		**** utf-8로 했을 때, 한글이름으로 된 이미지 출력 안됐음. euc-kr로 바꾸니 되네???
//		**** but,, 서버 업로드 폴더에는 한글이 깨져서 저장된다... 
		request.setCharacterEncoding("utf-8");
//		최종 업로드 폴더(업로드되는 파일들의 저장폴더)의 이름
		String UPLOAD_DIR = "file_repo";
//	 	업로드 폴더의 실제 물리적 경로 이름 
// 		Users/kks/dev-study/ServletJSP/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MVC07/file_repo
		String uploadPath = request.getServletContext().getRealPath(UPLOAD_DIR);
//		업로드 폴더의 실제경로로 File객체 만들기
		File uploadFolder = new File(uploadPath);
//		그 경로에 폴더 존재하지 않으면 업로드 폴더 만들어라.
		if(!uploadFolder.exists()) {
			uploadFolder.mkdir();
		}
		
//		클라이언트 -> 서버로 파일을 업로드 시, 원칙적으로는 파일을 "임시 폴더에 저장 후" 실제 폴더로 옮긴다.  
//		아래의 파일업로드API(DiskFileItemFactory)를 이용하여 임시로 저장될 "임시 저장경로"를 설정해준다.
//		하지만, '임시폴더 = 실제폴더'로 지정해주는 것이 일반적이다. 
//		Disk(임시) FileItem(파일) Factory(모음)
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(uploadFolder);
		factory.setSizeThreshold(1024*1024); //1MB
		
		String fileName = "";

//		API를 이용해서 request객체에 담겨서 넘어온 파일들 쉽게 꺼내오는 방법
		ServletFileUpload upload = new ServletFileUpload(factory); //factory에 업로드 할 것.
		try {
//			파일정보들을 FileItem리스트로 반환해준다. 
			List<FileItem> fileItems = upload.parseRequest(request);
//			리스트에서 파일 하나씩 꺼내온다.
			for(int i = 0; i < fileItems.size(); i++) {
				FileItem fileItem = fileItems.get(i);
//				form태그로 전송된거냐..(parameter)
				if(fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName()+" : "+fileItem.getString("utf-8"));
//				파일이냐..	
				} else {
//					파일이 잘 넘어왔냐..
					if(fileItem.getSize()>0) {
						fileName = fileItem.getName();

//						getName() : 원래는 파일의 절대 이름(경로포함) 추출인데, 바로 되네? 
//						int index = fileItem.getName().lastIndexOf("/");
//						if(index==-1) {
//							index = fileItem.getName().lastIndexOf("||");
//						}
//						순수 파일 이름 추출하기.
//						fileName = fileItem.getName().substring(index+1);
//						System.out.println(fileName);
//						그 경로로 실제로 파일 객체 생성
						File uploadFile = new File(uploadPath + "/" + fileName);
						
//						이미 존재한다면, rename
						if(uploadFile.exists()) {
							fileName = System.currentTimeMillis() + "_" + fileName;
							uploadFile = new File(uploadPath + "/" + fileName);
						}
						
						try {
//							임시경로에서 실제경로에 파일 쓰기 !! (파일 업로드 완료)
							fileItem.write(uploadFile);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
//		ajax로 fileName을 넘겨준다.
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(fileName);
		return null;
	}

}
