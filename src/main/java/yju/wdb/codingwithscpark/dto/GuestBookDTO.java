package yju.wdb.codingwithscpark.dto;

import java.time.LocalDateTime;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class GuestBookDTO {

	private Long gno;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime regDate, modDate;
	
	public GuestBookDTO(String title, String content, String writer) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	
	public GuestBookDTO() {
		
	}
	
	public Long getGno() {
		return gno;
	}
	public void setGno(Long gno) {
		this.gno = gno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public LocalDateTime getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	public LocalDateTime getModDate() {
		return modDate;
	}
	public void setModDate(LocalDateTime modDate) {
		this.modDate = modDate;
	}
}
