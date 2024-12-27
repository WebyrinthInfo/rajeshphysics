package com.rajeshphysics.ServiceImpls;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rajeshphysics.Dtos.CourseDto;
import com.rajeshphysics.Exceptions.ForbbidonExceptions;
import com.rajeshphysics.Exceptions.ResourceAlreadyExistsException;
import com.rajeshphysics.Models.Batch;
import com.rajeshphysics.Models.Course;
import com.rajeshphysics.Payloads.PageableDataResponse;
import com.rajeshphysics.Repositories.CourseRepository;
import com.rajeshphysics.Services.CourseService;


@Service
public class CourseServiceImpl implements CourseService {
    
    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private ModelMapper modelMapper;
   

    @Override
    public CourseDto addCourse(CourseDto courseDto) {
        logger.info("Adding a new course: {}", courseDto.getName());
        
        Optional<Course> existingCourse = courseRepo.findByName(courseDto.getName().trim().toUpperCase());
        if (existingCourse.isPresent()) {
            logger.warn("Attempt to add existing course: {}", courseDto.getName());
            throw new ResourceAlreadyExistsException("Course '" + courseDto.getName() + "' already exists.");
        }
  

        Course course = modelMapper.map(courseDto, Course.class);
        course.setName(courseDto.getName().trim().toUpperCase());
        course.setCourseTitle(courseDto.getCourseTitle().trim());
        course.setCourseLanguage(courseDto.getCourseLanguage().toUpperCase().trim());
        course.setImgPath("default.png");
        course.setCourseDescription(courseDto.getCourseDescription().trim());
        course.setCoursePrice(courseDto.getCoursePrice().trim());

        Course savedCourse = courseRepo.save(course);
        logger.info("Course added successfully: {}", savedCourse.getName());

        return modelMapper.map(savedCourse, CourseDto.class);
    }
    


    @Override
	public PageableDataResponse<List<Course>> getAllCourse(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String search) {
		try {
			if(search==null || search == "") {
				Sort sorting = null;
				if(sortDir.equalsIgnoreCase("desc")) {
					sorting = Sort.by(sortBy).descending();
				}else {
					sorting = Sort.by(sortBy).ascending();
				}
					
				PageRequest page = PageRequest.of(pageNumber, pageSize, sorting);
				Page<Course> pageCourse = courseRepo.findAll(page);
				List<Course> content = pageCourse.getContent();
				PageableDataResponse<List<Course>> pr = new PageableDataResponse<>();
				pr.setContent(content);
				pr.setPageNumber(pageCourse.getNumber());
				pr.setPageSize(pageCourse.getSize());
				pr.setTotalElements(pageCourse.getTotalElements());
				pr.setTotalPages(pageCourse.getTotalPages());
				pr.setLastPage(pageCourse.isLast());
				return pr;
				
			}else {
				Sort sorting = null;
				if(sortDir.equalsIgnoreCase("desc")) {
					sorting = Sort.by(sortBy).descending();
				}else {
					sorting = Sort.by(sortBy).ascending();
				}
					
				PageRequest page = PageRequest.of(pageNumber, pageSize, sorting);
				Page<Course> pageCourse = courseRepo.findByKeyword(search, page);
				List<Course> content = pageCourse.getContent();
				PageableDataResponse<List<Course>> pr = new PageableDataResponse<>();
				pr.setContent(content);
				pr.setPageNumber(pageCourse.getNumber());
				pr.setPageSize(pageCourse.getSize());
				pr.setTotalElements(pageCourse.getTotalElements());
				pr.setTotalPages(pageCourse.getTotalPages());
				pr.setLastPage(pageCourse.isLast());
				return pr;
			}
		} catch (Exception e) {
//			e.printStackTrace();
			throw new  ForbbidonExceptions("Something Went Wrong !!");
		}
		
	}

}
