package com.rajeshphysics.Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="SUBJECTS")
public class Subject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 458567242680803213L;
	
	@Column(name="ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="SUBJECT_NAME")
	private String name;
	
	@Column(name="IMAGE_URL")
	private String imgUrl;
	
	@Column(name="IS_ACTIVE")
	private int isActive = 1;
	
	@Column(name="SUBJECT_INTRODUCTION")
	private String subjectIntroduction;
	
	@Column(name="SUBJECT_DESCRIPTION")
	private String subjectDescription;
	
	@Column(name = "CREATED_AT")
	@CreationTimestamp
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	private LocalDateTime createdAt;

	@Column(name = "UPDATE_AT", nullable = false)
	@UpdateTimestamp
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	private LocalDateTime updateAt;
	
//	-----------------One Subject having many Batch-------------------
	@OneToMany(mappedBy = "subjects")
	private List<Batch> batches = new ArrayList<>();

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
	
	
	
	public List<Batch> getBatches() {
		return batches;
	}

	public void setBatches(List<Batch> batches) {
		this.batches = batches;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Subject() {
		super();
	}
	
	

}
