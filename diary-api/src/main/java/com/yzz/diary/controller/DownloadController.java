package com.yzz.diary.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DownloadController {
	
	@RequestMapping(value = "/downloadapk")
	public void downLoadApk(HttpServletResponse response ){
		String fileName = "111.apk";
		response.setContentType("multipart/form-data"); 
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
		
		InputStream is = null;
		try {
			is = new FileInputStream( "./apk/"+fileName );
			OutputStream os = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while( (len = is.read(buffer)) > 0 ){
				os.write(buffer,0,len);
			}
			
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
