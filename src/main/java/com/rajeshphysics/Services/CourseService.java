package com.rajeshphysics.Services;

import java.util.List;

import com.rajeshphysics.Dtos.CourseDto;
import com.rajeshphysics.Models.Course;
import com.rajeshphysics.Payloads.PageableDataResponse;


public interface CourseService {
	public CourseDto addCourse(CourseDto courseDto);
	public PageableDataResponse<List<Course>> getAllCourse(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String search);

}
