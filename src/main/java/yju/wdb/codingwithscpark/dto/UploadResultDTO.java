package yju.wdb.codingwithscpark.dto;

import java.io.*;
import java.net.URLEncoder;

public class UploadResultDTO {
	private String fileName;
	private String uuid;
	private String folderPath;
	
	
	public String getThumbnailURL() {
		try {
			return URLEncoder.encode(folderPath+ "/s_" + uuid + "_" + fileName, "UTF-8");
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getImageURL() {
		try {
			return URLEncoder.encode(folderPath + "/" +uuid + "_" + fileName, "UTF-8");
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "";	
	}
	
	
	public UploadResultDTO(String fileName, String uuid, String folderPath) {
		super();
		this.fileName = fileName;
		this.uuid = uuid;
		this.folderPath = folderPath;
	}

	

	public UploadResultDTO() {
		super();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFolderPath() {
		return folderPath;
	}

	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}
}
