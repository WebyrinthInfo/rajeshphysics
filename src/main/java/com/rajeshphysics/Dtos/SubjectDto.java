package com.rajeshphysics.Dtos;

import java.time.LocalDateTime;




public class SubjectDto {
	private Long id;
	
	private String name;
	
	private String imgUrl;
	
	private int isActive;
	
	private String subjectIntroduction;
	
	private String subjectDescription;
	
	private LocalDateTime createdAt;

	private LocalDateTime updateAt;
	
	private BatchDto batch;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getSubjectIntroduction() {
		return subjectIntroduction;
	}

	public void setSubjectIntroduction(String subjectIntroduction) {
		this.subjectIntroduction = subjectIntroduction;
	}

	public String getSubjectDescription() {
		return subjectDescription;
	}

	public void setSubjectDescription(String subjectDescription) {
		this.subjectDescription = subjectDescription;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public BatchDto getBatch() {
		return batch;
	}

	public void setBatch(BatchDto batch) {
		this.batch = batch;
	}

	
	
}
