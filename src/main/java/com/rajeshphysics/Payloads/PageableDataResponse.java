package com.rajeshphysics.Payloads;

import java.util.List;

import com.rajeshphysics.Dtos.CourseDto;

public class PageableDataResponse<T> {
	private T content;
	private Integer pageNumber;
	private Integer pageSize;
	private Integer totalPages;
	private Long totalElements;
	private Boolean LastPage;
	public T getContent() {
		return content;
	}
	public void setContent(T content) {
		this.content = content;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public Long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
	public Boolean getLastPage() {
		return LastPage;
	}
	public void setLastPage(Boolean lastPage) {
		LastPage = lastPage;
	}
	
	
	
}
