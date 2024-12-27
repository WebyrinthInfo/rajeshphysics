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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajeshphysics.Models.User;
import com.rajeshphysics.Payloads.AppConstrants;
import com.rajeshphysics.Payloads.GenericResponse;
import com.rajeshphysics.Payloads.PageableDataResponse;
import com.rajeshphysics.Services.UserService;

@RequestMapping("/api/user/")
@RestController
@CrossOrigin("*")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/get-all")
    public ResponseEntity<GenericResponse<PageableDataResponse<List<User>>>> getPaidStudent(
            @RequestParam(defaultValue = AppConstrants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = AppConstrants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(defaultValue = AppConstrants.SORT_BY_ID, required = false) String sortBy,
            @RequestParam(defaultValue = AppConstrants.SORT_DIR_DESC, required = false) String sortDir,
            @RequestParam(required = false) String search) {

        GenericResponse<PageableDataResponse<List<User>>> response = new GenericResponse<>();
        
        logger.warn("Fetching users with pageNumber={}, pageSize={}, sortBy={}, sortDir={}, search={} : {}", pageNumber, pageSize, sortBy, sortDir, search, LocalDateTime.now());
        
        try {
            PageableDataResponse<List<User>> userPage = userService.getAllUser(pageNumber, pageSize, sortBy, sortDir, search);
            
            if (userPage != null) {
                response.setData(userPage);
                response.setStatus("SUCCESS");
                response.setMsg("Data fetched successfully!");
                logger.info("Data fetched successfully! : {} ",LocalDateTime.now() );
                return new ResponseEntity<GenericResponse<PageableDataResponse<List<User>>>>(response, HttpStatus.OK);
            } else {
                response.setData(null);
                response.setStatus("SUCCESS");
                response.setMsg("No data available!");
                logger.warn("No data available! : {} ", LocalDateTime.now());
                return new ResponseEntity<GenericResponse<PageableDataResponse<List<User>>>>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            response.setData(null);
            response.setStatus("INTERNAL_SERVER_ERROR");
            response.setMsg("An error occurred while fetching data!");
            logger.error("An error occurred while fetching data : {}" ,LocalDateTime.now(), e);
            return new ResponseEntity<GenericResponse<PageableDataResponse<List<User>>>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/get-userinfo")
    public ResponseEntity<GenericResponse<User>> updateUserToken(
            @RequestParam("username") String username,
            GenericResponse<User> response) {

        logger.info("Fetching users with username: {} : {} ", username,  LocalDateTime.now());
        User userInfo = null;
        if (username != null ) {
            userInfo = userService.getUserInfo(username);
            if (userInfo != null) {
                response.setData(userInfo);
                response.setMsg("Data Fetch Successfully");
                response.setStatus("SUCCESS");
                logger.info("Data successfully for username: {} : {}", username, LocalDateTime.now());
                return new ResponseEntity<GenericResponse<User>>(response, HttpStatus.OK);
            } else {
                response.setData(userInfo);
                response.setMsg("Something went Wrong!!");
                response.setStatus("INTERNAL_SERVER_ERROR");
                logger.error("Featch failed for username: {} : {}", username, LocalDateTime.now());
                return new ResponseEntity<GenericResponse<User>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            response.setData(userInfo);
            response.setMsg("Invalid Parameters");
            response.setStatus("BAD_REQUEST");
            logger.warn("Invalid parameters for received for username: {} : {}", username, LocalDateTime.now());
            return new ResponseEntity<GenericResponse<User>>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
}
