package yju.wdb.codingwithscpark.dto;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class PageResultDTO <DTO, Entity>{
	// DTO list
	private List<DTO> dtoList;
	
	private int totalPage;
	
	private int page; // current page
	private int size; // number of rows per page
	private int start; // start page
	private int end; // end page
	private boolean prev, next;
	
	private List<Integer> pageList;
	
	
	public PageResultDTO(Page<Entity> result, Function<Entity, DTO> fn) {
		dtoList = result.stream().map(fn).collect(Collectors.toList());
		totalPage = result.getTotalPages();
		makePageList(result.getPageable());
	}
	
	private void makePageList(Pageable pageable) {
		this.page = pageable.getPageNumber() + 1;
		this.size = pageable.getPageNumber();
		
		int tempEnd = (int)(Math.ceil(page/10.0)) * 10;
		
		start = tempEnd - 9;
		prev = start > 1;
		end = totalPage > tempEnd ? tempEnd : totalPage;
		next = totalPage > end;
		pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
	}
	
	public List<DTO> getDtoList() {
		return dtoList;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
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

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public List<Integer> getPageList() {
		return pageList;
	}

	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}
	 
}


