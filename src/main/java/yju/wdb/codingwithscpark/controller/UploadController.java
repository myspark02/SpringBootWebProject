package yju.wdb.codingwithscpark.controller;

import org.apache.logging.log4j.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController

public class UploadController {
	private static final Logger log = LogManager.getLogger(UploadController.class);
	
	@PostMapping("/uploadAjax")
	public void uploadFile(MultipartFile[] uploadFiles) {
		
		for (MultipartFile uploadFile : uploadFiles) {
			// 실제 파일 이름,  IE나 Edge는 전체 경로가 들어오므로 
			String originalName = uploadFile.getOriginalFilename();
			String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
			log.info("fileName: " + fileName);
		}
	}
}
