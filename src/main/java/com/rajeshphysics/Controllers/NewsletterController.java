package com.rajeshphysics.Controllers;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajeshphysics.Dtos.NewsletterDto;
import com.rajeshphysics.Dtos.RoleDto;
import com.rajeshphysics.Exceptions.ResourceAlreadyExistsException;
import com.rajeshphysics.Payloads.GenericResponse;
import com.rajeshphysics.Services.NewsletterService;
import com.rajeshphysics.Services.RoleService;

@RestController
@RequestMapping("/api/newsletter")
@CrossOrigin("*")
public class NewsletterController {
	private static final Logger logger = LoggerFactory.getLogger(NewsletterController.class);
	
	 @Autowired
	    private NewsletterService newsletterServe;
	    
	    // ---------------- Add NewsLetter --------------
	    @PostMapping("/add")
	    public ResponseEntity<GenericResponse<NewsletterDto>> addNewsletter(@RequestBody NewsletterDto newsletterDto, GenericResponse<NewsletterDto> response) {
	        logger.info("Entering add Newsletter with data: {} : {}", newsletterDto.getMobile(), LocalDateTime.now());
	        
	        try {
	            NewsletterDto newsletterInfo = newsletterServe.addNewsletter(newsletterDto);
	            response.setData(newsletterInfo);
	            response.setMsg("Newsletter created successfully.");
	            response.setStatus("Success");
	            logger.info("Newsletter added successfully: {} : {}", newsletterInfo.getMobile(), LocalDateTime.now());
	            return new ResponseEntity<GenericResponse<NewsletterDto>>(response, HttpStatus.CREATED);
	        } catch (ResourceAlreadyExistsException e) {
	            logger.warn("Attempted to add an existing Newsletter : {} : {}", newsletterDto.getMobile(), LocalDateTime.now(), e);
	            response.setData(null);
	            response.setStatus("CONFLICT");
	            response.setMsg("Newsletter '" + newsletterDto.getMobile() + "' already exists.");
	            return new ResponseEntity<GenericResponse<NewsletterDto>>(response, HttpStatus.CONFLICT);
	        } catch (Exception e) {
	            logger.error("Error while adding Newsletter : {} : {}", e.getMessage(),LocalDateTime.now(), e);
	            response.setData(null);
	            response.setStatus("FAILURE");
	            response.setMsg("An error occurred while creating the Newsletter.");
	            return new ResponseEntity<GenericResponse<NewsletterDto>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        } finally {
	            logger.info("Exiting addNewsletter : {}", LocalDateTime.now());
	        }
	    }
}
