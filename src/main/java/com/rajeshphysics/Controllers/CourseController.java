package com.rajeshphysics.Controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajeshphysics.Dtos.CourseDto;
import com.rajeshphysics.Exceptions.ResourceAlreadyExistsException;
import com.rajeshphysics.Models.Course;
import com.rajeshphysics.Payloads.AppConstrants;
import com.rajeshphysics.Payloads.GenericResponse;
import com.rajeshphysics.Payloads.PageableDataResponse;
import com.rajeshphysics.Services.CourseService;
import com.rajeshphysics.Utils.ReCaptchaUtil;

@RestController
@RequestMapping("/api/course")
@CrossOrigin("*")
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    
    @Autowired
    private CourseService courseServe;
    
   
    
    // ---------------- Add Course --------------
    @PostMapping("/add")
    public ResponseEntity<GenericResponse<CourseDto>> addCourse(@RequestBody CourseDto courseDto,GenericResponse<CourseDto> response) {
        logger.info("Entering addCourse with data: {} : {}", courseDto, LocalDateTime.now() );
        
        try {
            CourseDto createdCourse = courseServe.addCourse(courseDto);
            response.setData(createdCourse);
            response.setMsg("Course created successfully.");
            response.setStatus("Success");
            logger.info("Course added successfully: {} : {}", createdCourse.getName(), LocalDateTime.now());
            return new ResponseEntity<GenericResponse<CourseDto>>(response, HttpStatus.CREATED);
        } catch (ResourceAlreadyExistsException e) {
            logger.warn("Attempted to add an existing course: {} : {}", courseDto.getName(), LocalDateTime.now(), e);
            response.setData(null);
            response.setStatus("CONFLICT");
            response.setMsg("Course '" + courseDto.getName() + "' already exists.");
            return new ResponseEntity<GenericResponse<CourseDto>>(response, HttpStatus.CONFLICT);
        } catch (Exception e) {
            logger.error("Error while adding course: {} : {}", e.getMessage(), LocalDateTime.now(), e);
            response.setData(null);
            response.setStatus("FAILURE");
            response.setMsg("An error occurred while creating the course.");
            return new ResponseEntity<GenericResponse<CourseDto>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("Exiting addCourse : {}", LocalDateTime.now());
        }
    }
    
    // ---------------- Get All Courses --------------
    @GetMapping("/get-all")
    public ResponseEntity<GenericResponse<PageableDataResponse<List<Course>>>> getAllCourses(
            @RequestParam(defaultValue = AppConstrants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = AppConstrants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(defaultValue = AppConstrants.SORT_BY_ID, required = false) String sortBy,
            @RequestParam(defaultValue = AppConstrants.SORT_DIR_DESC, required = false) String sortDir,
            @RequestParam(required = false) String search) {

        logger.info("Entering getAllCourses with parameters - pageNumber: {}, pageSize: {}, sortBy: {}, sortDir: {}, search: {}, {}", 
                pageNumber, pageSize, sortBy, sortDir, search, LocalDateTime.now());
        
        GenericResponse<PageableDataResponse<List<Course>>> response = new GenericResponse<>();
        
        try {
            PageableDataResponse<List<Course>> coursePage = courseServe.getAllCourse(pageNumber, pageSize, sortBy, sortDir, search);
            
            if (coursePage != null) {
                response.setData(coursePage);
                response.setStatus("SUCCESS");
                response.setMsg("Data fetched successfully!");
                logger.info("Courses fetched successfully : {}", LocalDateTime.now());
                return new ResponseEntity<GenericResponse<PageableDataResponse<List<Course>>>>(response, HttpStatus.OK);
            } else {
                response.setData(null);
                response.setStatus("SUCCESS");
                response.setMsg("No data available!");
                logger.warn("No courses available");
                return new ResponseEntity<GenericResponse<PageableDataResponse<List<Course>>>>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("Error occurred while fetching courses: {} : {}", e.getMessage(), LocalDateTime.now(), e);
            response.setData(null);
            response.setStatus("INTERNAL_SERVER_ERROR");
            response.setMsg("An error occurred while fetching data!");
            return new ResponseEntity<GenericResponse<PageableDataResponse<List<Course>>>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("Exiting getAllCourses : {}", LocalDateTime.now());
        }
    }
}
