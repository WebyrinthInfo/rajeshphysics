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
import com.rajeshphysics.Dtos.ContactUsDto;
import com.rajeshphysics.Exceptions.ResourceAlreadyExistsException;
import com.rajeshphysics.Models.ContactUs;
import com.rajeshphysics.Payloads.AppConstrants;
import com.rajeshphysics.Payloads.GenericResponse;
import com.rajeshphysics.Payloads.PageableDataResponse;
import com.rajeshphysics.Services.ContactUsService;
import com.rajeshphysics.Utils.ReCaptchaUtil;

@RestController
@RequestMapping("/api/contact-us")
@CrossOrigin("*")
public class ContactUsController {
    private static final Logger logger = LoggerFactory.getLogger(ContactUsController.class);
    

    @Autowired
    private ContactUsService ContactUsServe;
    
    @Autowired
    private ReCaptchaUtil reCaptchaUtil;
    
    // ---------------- Add ContactUs --------------
    @PostMapping("/add")
    public ResponseEntity<GenericResponse<ContactUsDto>> addContactUs(@RequestBody ContactUsDto ContactUsDto, GenericResponse<ContactUsDto> response) {
        logger.info("Entering addContactUs with data: {} : {}", ContactUsDto, LocalDateTime.now() );
        
        try {
        	
            ContactUsDto createdContactUs = ContactUsServe.addContactUs(ContactUsDto);
            response.setData(createdContactUs);
            response.setMsg("ContactUs created successfully.");
            response.setStatus("Success");
            logger.info("ContactUs added successfully: {} : {}", createdContactUs.getName(), LocalDateTime.now());
            return new ResponseEntity<GenericResponse<ContactUsDto>>(response, HttpStatus.OK);
        } catch (ResourceAlreadyExistsException e) {
            logger.warn("Attempted to add an existing ContactUs: {} : {}", ContactUsDto.getName(), LocalDateTime.now(), e);
            response.setData(null);
            response.setStatus("CONFLICT");
            response.setMsg("ContactUs '" + ContactUsDto.getName() + "' already exists.");
            return new ResponseEntity<GenericResponse<ContactUsDto>>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while adding ContactUs: {} : {}", e.getMessage(), LocalDateTime.now(), e);
            response.setData(null);
            response.setStatus("FAILURE");
            response.setMsg("An error occurred while creating the ContactUs.");
            return new ResponseEntity<GenericResponse<ContactUsDto>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("Exiting addContactUs : {}", LocalDateTime.now());
        }
    }
    
    // ---------------- Get All ContactUs --------------
    @GetMapping("/get-all")
    public ResponseEntity<GenericResponse<PageableDataResponse<List<ContactUs>>>> getAllContactUss(
            @RequestParam(defaultValue = AppConstrants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = AppConstrants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(defaultValue = AppConstrants.SORT_BY_ID, required = false) String sortBy,
            @RequestParam(defaultValue = AppConstrants.SORT_DIR_DESC, required = false) String sortDir,
            @RequestParam(required = false) String search) {

        logger.info("Entering getAllContactUss with parameters - pageNumber: {}, pageSize: {}, sortBy: {}, sortDir: {}, search: {}, {}", 
                pageNumber, pageSize, sortBy, sortDir, search, LocalDateTime.now());
        
        GenericResponse<PageableDataResponse<List<ContactUs>>> response = new GenericResponse<>();
        
        try {
            PageableDataResponse<List<ContactUs>> contactUsPage = ContactUsServe.getAllContactUs(pageNumber, pageSize, sortBy, sortDir, search);
            
            if (contactUsPage != null) {
                response.setData(contactUsPage);
                response.setStatus("SUCCESS");
                response.setMsg("Data fetched successfully!");
                logger.info("ContactUs fetched successfully : {}", LocalDateTime.now());
                return new ResponseEntity<GenericResponse<PageableDataResponse<List<ContactUs>>>>(response, HttpStatus.OK);
            } else {
                response.setData(null);
                response.setStatus("SUCCESS");
                response.setMsg("No data available!");
                logger.warn("No ContactUss available");
                return new ResponseEntity<GenericResponse<PageableDataResponse<List<ContactUs>>>>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("Error occurred while fetching ContactUss: {} : {}", e.getMessage(), LocalDateTime.now(), e);
            response.setData(null);
            response.setStatus("INTERNAL_SERVER_ERROR");
            response.setMsg("An error occurred while fetching data!");
            return new ResponseEntity<GenericResponse<PageableDataResponse<List<ContactUs>>>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("Exiting getAllContactUss : {}", LocalDateTime.now());
        }
    }

}
