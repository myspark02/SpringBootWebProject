package yju.wdb.codingwithscpark.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MovieImageDTO {

	private String uuid;
	private String imageName;
	private String path;
	
	
	
	public MovieImageDTO(String uuid, String imageName, String path) {
		super();
		this.uuid = uuid;
		this.imageName = imageName;
		this.path = path;
	}
	
	public MovieImageDTO() {
		
	}

	public String getImageURL() {
		try {
			return URLEncoder.encode(path+"/"+uuid+"_"+imageName, "UTF-8");
			
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getThumbnailURL() {
		try {
			return URLEncoder.encode(path+"/s_"+uuid+"_"+imageName, "UTF-8");
			
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public String getUuid() {
		return uuid;
	}

	public String getImageName() {
		return imageName;
	}

	public String getPath() {
		return path;
	}
}
