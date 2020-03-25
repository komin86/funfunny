package com.funfunny.common.fileUpload;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.funfunny.common.time.TimeUtil;

@Service
public class FileUploadService {

	
	@Autowired
	TimeUtil timeUtil;
	
	public static final String freeBoradTempUrlPath = "freeBorad/image/";
	public static final String freeBoradTempStoragePath = "D:/image/freeBoardTemp/";
	
	public String fileUp(MultipartFile file, String path) {
		
		String imageName = timeUtil.getLocalDateTime().format(DateTimeFormatter.BASIC_ISO_DATE)
				+ UUID.randomUUID().toString()
				+ "." 
				+ file.getOriginalFilename().split("\\.")[1];
		String result = "";

		System.out.println("BASIC_ISO_DATE :  " + timeUtil.getLocalDateTime().format(DateTimeFormatter.BASIC_ISO_DATE));

		File target = new File( path + imageName);
		try {
			FileCopyUtils.copy(file.getBytes(), target);
			if(path.equals(freeBoradTempStoragePath)) {
				result = freeBoradTempUrlPath;
			}
			result += imageName;
		} catch (IOException e) {
			result = "FSF";
		}
		
		return result;
	}

}
