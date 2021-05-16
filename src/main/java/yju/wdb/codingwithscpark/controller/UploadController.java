package yju.wdb.codingwithscpark.controller;

import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

import java.util.*;
import yju.wdb.codingwithscpark.dto.*;
@RestController

public class UploadController {
	private static final Logger log = LogManager.getLogger(UploadController.class);
	
	// application.properties의 변수 
	@Value("${yju.wdb.codingwithscpark.upload.path}") 
	private String uploadPath;
	
	@PostMapping("/removeFile")
	public ResponseEntity<Boolean> removeFile(String fileName) {
		String srcFileName = null;
		
		try {
			srcFileName = URLDecoder.decode(fileName, "UTF-8");
			File file = new File(uploadPath + File.separator + srcFileName);
			boolean result = file.delete();
			
			File thumbnail = new File(file.getParent(), "s_" + file.getName());
			
			result = thumbnail.delete();
			
			return new ResponseEntity<>(result, HttpStatus.OK);
			
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName) {
		ResponseEntity<byte[]> result = null;
		log.info("display invoked....");
		log.info("fileName:" + fileName);
		try {
			String srcFileName = URLDecoder.decode(fileName, "UTF-8");
			
			log.info("srcFileName : " + srcFileName);
			
			log.info("uploadPath:" + uploadPath);
			
			
			File file = new File(uploadPath + File.separator + srcFileName);
			
			log.info("file: " + file.getAbsolutePath());
			
			HttpHeaders header = new HttpHeaders();
			
			// MIME 타입 처리
			header.add("Content-Type",  Files.probeContentType(file.toPath()));
			
			// 파일 데이터 처리
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@PostMapping("/uploadAjax")
	public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles) {
		List<UploadResultDTO> resultDTOList = new ArrayList<>();
		
		
		for (MultipartFile uploadFile : uploadFiles) {
			
			//  이미지 파일만 업로드 가능
			if (uploadFile.getContentType().startsWith("image") == false) {
				log.warn("this file is not image type");
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			
			// 실제 파일 이름,  IE나 Edge는 전체 경로가 들어오므로 
			String originalName = uploadFile.getOriginalFilename();
			String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
			log.info("fileName: " + fileName);
			
			// 날짜 폴더 생성 
			String folderPath = makeFolder();
			
			//UUID
			String uuid = UUID.randomUUID().toString();
			
			// 저장할 파일 이름 중간에 "_"를 이용해서 구분
			String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
			log.info("saveName: " + saveName);
			Path savePath = Paths.get(saveName);
			
			try {
				uploadFile.transferTo(savePath);
				
				//섬네일 생성
				String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator +
							"s_" + uuid + "_" + fileName;
				File thumbnailFile = new File(thumbnailSaveName);
				
				Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100, 100);
				
				resultDTOList.add(new UploadResultDTO(fileName, uuid, folderPath));
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
	}
	
	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		String folderPath = str.replace("//", File.separator);
		
		// make folder ----
		File uploadPathFolder = new File(uploadPath, folderPath);
		
		if (uploadPathFolder.exists() == false) {
			uploadPathFolder.mkdirs();
		}
		
		return folderPath;
	}
}
