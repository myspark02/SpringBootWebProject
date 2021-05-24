package yju.wdb.codingwithscpark.dto;

import java.util.*;

public class MovieDTO {
	private Long mno;
	private String title;
	private List<MovieImageDTO> imageDTOList = new ArrayList<>();
	
	public MovieDTO() {
		
	}
	
	public MovieDTO(Long mno, String title) {
		this.mno = mno;
		this.title = title;
	}

	public Long getMno() {
		return mno;
	}

	public void setMno(Long mno) {
		this.mno = mno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<MovieImageDTO> getImageDTOList() {
		return imageDTOList;
	}

	public void setImageDTOList(List<MovieImageDTO> imageDTOList) {
		this.imageDTOList = imageDTOList;
	}
	
	
}
