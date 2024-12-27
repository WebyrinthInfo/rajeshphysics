package com.rajeshphysics.Dtos;

import java.util.ArrayList;
import java.util.List;

import com.rajeshphysics.Models.Batch;

public class CourseDto {
	
	private Integer id;

	private String name;
	
	private String imgPath;
	
	private String courseLanguage;
	
	private String courseTitle;
	
	private String courseDescription;
	
	private String coursePrice;
	
	private List<BatchDto> batches = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getCourseLanguage() {
		return courseLanguage;
	}

	public void setCourseLanguage(String courseLanguage) {
		this.courseLanguage = courseLanguage;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(String coursePrice) {
		this.coursePrice = coursePrice;
	}
	
	public List<BatchDto> getBatches() {
		return batches;
	}

	public void setBatches(List<BatchDto> batches) {
		this.batches = batches;
	}
	

	public CourseDto() {
		super();
	}
	
}
