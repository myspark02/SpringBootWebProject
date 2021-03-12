package yju.wdb.codingwithscpark.dto;

import java.time.*;

public class BoardDTO {
	private Long bno;
	private String title;
	private String content;
	private String writerEmail;
	private String writerName;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	private int replyCount;
	
	public BoardDTO(Long bno, String title, String content, String writerEmail, String writerName,
			LocalDateTime regDate, LocalDateTime modDate, int replyCount) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writerEmail = writerEmail;
		this.writerName = writerName;
		this.regDate = regDate;
		this.modDate = modDate;
		this.replyCount = replyCount;
	}
	
	public BoardDTO(String title, String content, String writerEmail) {
		this.title = title;
		this.content = content;
		this.writerEmail = writerEmail;
	}
	
	public BoardDTO(Long bno, String title, String content) {
		this.bno = bno;
		this.title = title;
		this.content = content;
	}
	
	public BoardDTO() {
		super();
	}

	@Override
	public String toString() {
		return "BoardDTO [bno=" + bno + ", title=" + title + ", content=" + content + ", writerEmail=" + writerEmail
				+ ", writerName=" + writerName + ", regDate=" + regDate + ", modDate=" + modDate + ", replyCount="
				+ replyCount + "]";
	}

	public Long getBno() {
		return bno;
	}
	public void setBno(Long bno) {
		this.bno = bno;
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
	public String getWriterEmail() {
		return writerEmail;
	}
	public void setWriterEmail(String writerEmail) {
		this.writerEmail = writerEmail;
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
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
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

}
