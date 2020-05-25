package kr.co.foreignlove.controller.attached;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileUploader implements FileUploadable{

	//여기는 받았을때 
	
	@SuppressWarnings("unchecked")
	public void upload(HttpServletRequest request, HttpServletResponse response ) {
		
		JSONObject obj = null;
		response.setContentType("application/json");
		PrintWriter out =null;
		String path = request.getServletContext().getRealPath("/uploadtemp");
		
		try{
			MultipartRequest m = new MultipartRequest(request,
					path,
					10 * 1024 * 1024,
					"utf-8",
					new DefaultFileRenamePolicy());
			File f = m.getFile("file");	
			System.out.println(f);
			
			if( f== null) {
				System.out.println("파일이업로드되지 않았습니다.");
			}
			else {
				
				obj= new JSONObject();
				obj.put("fileName",f.getName());
				obj.put("fileSize", f.length());
				out = response.getWriter();
				out.print(obj.toJSONString());
				
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
