package yju.wdb.codingwithscpark.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.Data;

@Data
public class PageRequestDTO {
	private int page;
	private int size;
	private String type;
	private String keyword;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public PageRequestDTO() {
		page = 1;
		size = 10;
	}
	
	public PageRequestDTO(int page, int size) {
		this.page = page;
		this.size = size;
	}
	
	public Pageable getPageable(Sort sort) {
		return PageRequest.of(page-1, size, sort);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
