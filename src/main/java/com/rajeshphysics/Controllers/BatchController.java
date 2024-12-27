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

import com.rajeshphysics.Dtos.BatchDto;
import com.rajeshphysics.Exceptions.ResourceAlreadyExistsException;
import com.rajeshphysics.Models.Batch;
import com.rajeshphysics.Payloads.AppConstrants;
import com.rajeshphysics.Payloads.GenericResponse;
import com.rajeshphysics.Payloads.PageableDataResponse;
import com.rajeshphysics.Services.BatchService;


@RestController
@RequestMapping("/api/batch")
@CrossOrigin("*")
public class BatchController {
	public static final Logger logger = LoggerFactory.getLogger(BatchController.class);
	
	 @Autowired
	    private BatchService batchServe;
	    
	    // ---------------- Add Batch --------------
	    @PostMapping("/add")
	    public ResponseEntity<GenericResponse<BatchDto>> addBatch(@RequestBody BatchDto batchDto, @RequestParam(name = "courseId", required = true) Long courseId, GenericResponse<BatchDto> response ) {
	        logger.info("Entering addBatch with data: {} : {}", batchDto, LocalDateTime.now());
	        
	        try {
	        	BatchDto createdBatch = batchServe.addBatch(batchDto, courseId);
	            response.setData(createdBatch);
	            response.setMsg("Batch created successfully.");
	            response.setStatus("Success");
	            logger.info("Batch added successfully: {} : {}", createdBatch.getBatchCode(), LocalDateTime.now());
	            return new ResponseEntity<>(response, HttpStatus.CREATED);
	        } catch (ResourceAlreadyExistsException e) {
	            logger.warn("Attempted to add an existing batch: {} : {}", batchDto.getBatchCode(), LocalDateTime.now());
	            response.setData(null);
	            response.setStatus("CONFLICT");
	            response.setMsg("Batch '" + batchDto.getBatchCode() + "' already exists.");
	            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	        } catch (Exception e) {
	            logger.error("Error while adding batch: {}", e.getMessage(), e);
	            response.setData(null);
	            response.setStatus("FAILURE");
	            response.setMsg("An error occurred while creating the batch.");
	            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        } finally {
	            logger.info("Exiting addBath : {} : {}",batchDto.getBatchCode(), LocalDateTime.now());
	        }
	    }
	    
//	    ------------------get All batch info------------------------
	    @GetMapping("/get-all")
	    public ResponseEntity<GenericResponse<PageableDataResponse<List<Batch>>>> getPaidStudent(
	            @RequestParam(defaultValue = AppConstrants.PAGE_NUMBER, required = false) Integer pageNumber,
	            @RequestParam(defaultValue = AppConstrants.PAGE_SIZE, required = false) Integer pageSize,
	            @RequestParam(defaultValue = AppConstrants.SORT_BY_ID, required = false) String sortBy,
	            @RequestParam(defaultValue = AppConstrants.SORT_DIR_DESC, required = false) String sortDir,
	            @RequestParam(required = false) String search) {

	        GenericResponse<PageableDataResponse<List<Batch>>> response = new GenericResponse<>();
	        
	        logger.warn("Fetching Batch with pageNumber={}, pageSize={}, sortBy={}, sortDir={}, search={} : {}", pageNumber, pageSize, sortBy, sortDir, search, LocalDateTime.now());
	        
	        try {
	            PageableDataResponse<List<Batch>> batchPage = batchServe.getAllBatch(pageNumber, pageSize, sortBy, sortDir, search);
	            
	            if (batchPage != null) {
	                response.setData(batchPage);
	                response.setStatus("SUCCESS");
	                response.setMsg("Data fetched successfully!");
	                logger.info("Data fetched successfully! : {} ",LocalDateTime.now() );
	                return new ResponseEntity<GenericResponse<PageableDataResponse<List<Batch>>>>(response, HttpStatus.OK);
	            } else {
	                response.setData(null);
	                response.setStatus("SUCCESS");
	                response.setMsg("No data available!");
	                logger.warn("No data available! : {} ", LocalDateTime.now());
	                return new ResponseEntity<GenericResponse<PageableDataResponse<List<Batch>>>>(response, HttpStatus.OK);
	            }
	        } catch (Exception e) {
	            response.setData(null);
	            response.setStatus("INTERNAL_SERVER_ERROR");
	            response.setMsg("An error occurred while fetching data!");
	            logger.error("An error occurred while fetching data : {}" ,LocalDateTime.now(), e);
	            return new ResponseEntity<GenericResponse<PageableDataResponse<List<Batch>>>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
}
