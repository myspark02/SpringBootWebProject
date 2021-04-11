package yju.wdb.codingwithscpark.dto;

import java.time.LocalDateTime;

public class ReplyDTO {
	private Long rno;
	private String text;
	private String replyer;
	private Long bno;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	
	
	public ReplyDTO(Long rno, String text, String replyer, LocalDateTime regDate, LocalDateTime modDate) {
		this.regDate = regDate;
		this.modDate = modDate;
		this.rno = rno;
		this.text = text;
		this.replyer = replyer;
	}
	
	public Long getRno() {
		return rno;
	}
	public void setRno(Long rno) {
		this.rno = rno;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public Long getBno() {
		return bno;
	}
	public void setBno(Long bno) {
		this.bno = bno;
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
	@Override
	public String toString() {
		return "[rno : " + rno+", text:" + text+", replyer:" + replyer +", bno :" + bno +
					", regDate:" + regDate+", modDate:" + modDate + "]";
	}
}

