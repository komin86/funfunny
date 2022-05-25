package com.funfunny.common.fileUpload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.funfunny.common.time.TimeUtil;

import net.sf.json.JSONObject;

@Service
public class FileUploadService {
	
	@Autowired
	TimeUtil timeUtil;
	
	public JSONObject fileUp(MultipartFile file, String path, String url) {
		
		String imageName = timeUtil.getLocalDateTime().format(DateTimeFormatter.BASIC_ISO_DATE)
				+ UUID.randomUUID().toString()
				+ "." 
				+ file.getOriginalFilename().split("\\.")[1];
		
		System.out.println("BASIC_ISO_DATE :  " + timeUtil.getLocalDateTime().format(DateTimeFormatter.BASIC_ISO_DATE));

		JSONObject result = new JSONObject();
		
		try {
			File mkdir = new File( path );
			if(!mkdir.exists()) { mkdir.mkdir();}

			File target = new File( path + imageName);
			FileCopyUtils.copy(file.getBytes(), target);
			result.put("url", url);
			result.put("name", imageName);
			result.put("status", "SU");
			
		} catch (IOException e) {
			System.out.println(e);
			result.put("status", "FSF");
		}
		
		return result;
	}
	
	
	public String fileCopy(String tempPath, String realPath , String filename)  {
		
		File temp = new File(tempPath+filename);
		File real = new File(realPath);
		if(!real.exists()) { real.mkdir(); }
		
		File realTarget = new File(realPath+filename);
		
		FileInputStream stream;
		String result = "";
		
		try {
			//실사용 경로 확인
			if(!realTarget.exists()) {
				stream = new FileInputStream(temp);
				FileCopyUtils.copy(stream.readAllBytes(), realTarget);
				stream.close();
				result = "SU";
			}
			result = "EXIST";
		} catch (Exception e) {
			result = "ERR";
			e.printStackTrace();
		}
		
		return result;
	}

	
	
	public boolean fileDelete() {
		
		File file = new File("");
		boolean result = false;
		if(file.exists()) {
			result = file.delete();
		}
		
		return result;
	}
	
}
