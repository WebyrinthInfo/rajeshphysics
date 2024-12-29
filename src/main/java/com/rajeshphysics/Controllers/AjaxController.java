package com.rajeshphysics.Controllers;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajeshphysics.Exceptions.AuthenticationException;
import com.rajeshphysics.Models.User;
import com.rajeshphysics.Payloads.AppConstrants;
import com.rajeshphysics.Payloads.AuthRequest;
import com.rajeshphysics.Payloads.GenericResponse;
import com.rajeshphysics.Repositories.UserRepository;
import com.rajeshphysics.Services.UserService;
import com.rajeshphysics.Utils.JwtUtil;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/ajax")
@CrossOrigin("*")
public class AjaxController {

    private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);

    @Autowired
    private JwtUtil jwtutil;

    @Autowired
    private UserDetailsService uds;

    @Autowired
    private AuthenticationManager am;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userServe;

    @PostMapping("/registration")
    public ResponseEntity<GenericResponse<User>> createUserAndAssignRole(
            @RequestBody User user,
            @RequestParam(name = "roleId", defaultValue = AppConstrants.STUDENT_ROLE_ID, required = false) Long roleId,
            @RequestParam(name = "batchCode", required = true) String batchCode,
            GenericResponse<User> response) {

        logger.info("Registration request received with roleId: {} and batchCode: {} : {}", roleId, batchCode, LocalDateTime.now());
        User userInfo = null;
        if (user != null && roleId != null && batchCode != null) {
            userInfo = userServe.createUser(user, roleId, batchCode);
            if (userInfo != null) {
                response.setData(userInfo);
                response.setMsg("Registration Successfully");
                response.setStatus("SUCCESS");
                logger.info("User registration successful for user: {} : {}", user.getUsername(), LocalDateTime.now());
                return new ResponseEntity<GenericResponse<User>>(response, HttpStatus.OK);
            } else {
                response.setData(userInfo);
                response.setMsg("Something went Wrong!!");
                response.setStatus("INTERNAL_SERVER_ERROR");
                logger.error("User registration failed for user: {} : {}", user.getUsername(), LocalDateTime.now());
                return new ResponseEntity<GenericResponse<User>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            response.setData(userInfo);
            response.setMsg("Invalid Parameters");
            response.setStatus("BAD_REQUEST");
            logger.warn("Invalid registration parameters received : "+LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse<User>> loginUser(
            @RequestBody AuthRequest authRequest,
            GenericResponse<User> response,
            HttpSession session) throws Exception {

        logger.info("Login request received for username: {} : {}", authRequest.getUsername(), LocalDateTime.now());
        if (authRequest != null) {
            authenticate(authRequest.getUsername().trim(), authRequest.getPassword().trim());
            UserDetails ud = uds.loadUserByUsername(authRequest.getUsername().trim());
            User user = userRepo.findByMobile(ud.getUsername()).orElse(null);
            if (user == null) {
                response.setData(null);
                response.setStatus("FAILURE");
                response.setMsg("User not found");
                logger.error("User not found for username: {} : {}", authRequest.getUsername(), LocalDateTime.now());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            try {
                Boolean validateToken = jwtutil.validateToken(user.getJwtToken(), user);
                int isActive = user.getIsActive();

                if (validateToken == true && isActive == 1) {
                    session.setAttribute("userInfo", user);
                    response.setData(user);
                    response.setStatus("SUCCESS");
                    response.setMsg("Login Successfully !!");
                    logger.info("User logged in successfully: {} : {}", authRequest.getUsername(), LocalDateTime.now() );
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    response.setData(null);
                    response.setStatus("SUCCESS");
                    response.setMsg("User is InActive !");
                    logger.warn("User is inactive: {} : {}", authRequest.getUsername(), LocalDateTime.now());
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            } catch (Exception e) {
                logger.error("Token validation failed for user: {} : {}", authRequest.getUsername(), LocalDateTime.now());
                throw new AuthenticationException("Your Subscription has been Expired !");
            }
        } else {
            response.setData(null);
            response.setMsg("Invalid Parameters");
            response.setStatus("BAD_REQUEST");
            logger.warn("Invalid login parameters received : {}", LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(username, password);
        try {
            am.authenticate(upat);
            logger.info("Authentication successful for username: {} : {}", username, LocalDateTime.now());
        } catch (BadCredentialsException e) {
            logger.error("Invalid credentials for username: {} : {}", username, LocalDateTime.now());
            throw new AuthenticationException("Invalid username or password");
        } catch (DisabledException e) {
            logger.error("Disabled user attempted login: {} : {}", username, LocalDateTime.now());
            throw new AuthenticationException("Disabled username or password");
        }
    }

    @PutMapping("/updateToken")
    public ResponseEntity<GenericResponse<User>> updateUserToken(
            @RequestParam("username") String username,
            @RequestParam("days") Integer days,
            @RequestParam(name = "isPaid", defaultValue = AppConstrants.FREE_STUDENT) String isPaid,
            GenericResponse<User> response) {

        logger.info("Token update request received for username: {} for {} days : {}", username, days, LocalDateTime.now());
        User userInfo = null;
        if (username != null && days != null) {
            userInfo = userServe.updateUserToken(username,(long)days, isPaid);
            if (userInfo != null) {
                response.setData(userInfo);
                response.setMsg("Token update Successfully for " + days + " Days");
                response.setStatus("SUCCESS");
                logger.info("Token updated successfully for username: {} : {}", username, LocalDateTime.now());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setData(userInfo);
                response.setMsg("Something went Wrong!!");
                response.setStatus("INTERNAL_SERVER_ERROR");
                logger.error("Token update failed for username: {} : {}", username, LocalDateTime.now());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            response.setData(userInfo);
            response.setMsg("Invalid Parameters");
            response.setStatus("BAD_REQUEST");
            logger.warn("Invalid parameters for token update received for username: {} : {}", username, LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
