package yju.wdb.codingwithscpark.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.*;

import lombok.Getter;

@MappedSuperclass
@EntityListeners(value= {AuditingEntityListener.class})
@Getter

abstract public class BaseEntity {
	@CreatedDate
	@Column(name = "regdate", updatable = false)
	private LocalDateTime regDate;
	
	@LastModifiedDate
	@Column(name="moddate")
	private LocalDateTime modDate;

	public LocalDateTime getRegDate() {
		return regDate;
	}


	public LocalDateTime getModDate() {
		return modDate;
	}

	
}
