package com.rajeshphysics.Controllers;

import java.time.LocalDateTime;
import java.util.List;

import javax.management.relation.RoleNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajeshphysics.Dtos.RoleDto;
import com.rajeshphysics.Exceptions.ResourceAlreadyExistsException;
import com.rajeshphysics.Payloads.GenericResponse;
import com.rajeshphysics.Services.RoleService;

@RestController
@RequestMapping("/api/role")
@CrossOrigin("*")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    
    @Autowired
    private RoleService roleServe;
    
    // ---------------- Add Role --------------
    @PostMapping("/add")
    public ResponseEntity<GenericResponse<RoleDto>> addRole(@RequestBody RoleDto roleDto, GenericResponse<RoleDto> response) {
        logger.info("Entering addRole with data: {} : {}", roleDto, LocalDateTime.now());
        
        try {
            RoleDto createdRole = roleServe.addRole(roleDto);
            response.setData(createdRole);
            response.setMsg("Role created successfully.");
            response.setStatus("Success");
            logger.info("Role added successfully: {} : {}", createdRole.getName(), LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ResourceAlreadyExistsException e) {
            logger.warn("Attempted to add an existing role: {} : {}", roleDto.getName(), LocalDateTime.now(), e);
            response.setData(null);
            response.setStatus("CONFLICT");
            response.setMsg("Role '" + roleDto.getName() + "' already exists.");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } catch (Exception e) {
            logger.error("Error while adding role: {} : {}", e.getMessage(),LocalDateTime.now(), e);
            response.setData(null);
            response.setStatus("FAILURE");
            response.setMsg("An error occurred while creating the role.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("Exiting addRole : {}", LocalDateTime.now());
        }
    }
    
    // ---------------- Get All Roles --------------
    @GetMapping("/get-all")
    public ResponseEntity<GenericResponse<List<RoleDto>>> getAllRoles() {
        logger.info("Entering getAllRoles");
        
        GenericResponse<List<RoleDto>> response = new GenericResponse<>();
        
        try {
            List<RoleDto> roles = roleServe.getAllRoles();
            response.setData(roles);
            response.setMsg("Roles fetched successfully.");
            response.setStatus("Success");
            logger.info("Fetched all roles successfully. : {}", LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while fetching roles: {} : {}", e.getMessage(),LocalDateTime.now(), e);
            response.setData(null);
            response.setStatus("FAILURE");
            response.setMsg("An error occurred while fetching the roles.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("Exiting getAllRoles : {} ",LocalDateTime.now());
        }
    }
    
    // ---------------- Update Role --------------
    @PutMapping("/update")
    public ResponseEntity<GenericResponse<RoleDto>> updateRole(@RequestParam Long roleId, @RequestBody RoleDto roleDto) {
        logger.info("Entering updateRole with roleId: {}, data: {}", roleId, roleDto);
        
        GenericResponse<RoleDto> response = new GenericResponse<>();
        
        try {
            RoleDto updatedRole = roleServe.updateRole(roleId, roleDto);
            response.setData(updatedRole);
            response.setMsg("Role updated successfully.");
            response.setStatus("Success");
            logger.info("Role updated successfully: {}", updatedRole.getName());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RoleNotFoundException e) {
            logger.warn("Role not found: {}", roleId, e);
            response.setData(null);
            response.setStatus("NOT_FOUND");
            response.setMsg(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error while updating role: {}", e.getMessage(), e);
            response.setData(null);
            response.setStatus("FAILURE");
            response.setMsg("An error occurred while updating the role.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("Exiting updateRole");
        }
    }
    
    // ---------------- Delete Role --------------
    @DeleteMapping("/delete")
    public ResponseEntity<GenericResponse<Void>> deleteRole(@RequestParam Long roleId) {
        logger.info("Entering deleteRole with roleId: {}", roleId);
        
        GenericResponse<Void> response = new GenericResponse<>();
        
        try {
            roleServe.deleteRole(roleId);
            response.setMsg("Role deleted successfully.");
            response.setStatus("Success");
            logger.info("Role deleted successfully: {}", roleId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RoleNotFoundException e) {
            logger.warn("Role not found: {}", roleId, e);
            response.setStatus("NOT_FOUND");
            response.setMsg(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error while deleting role: {}", e.getMessage(), e);
            response.setStatus("FAILURE");
            response.setMsg("An error occurred while deleting the role.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("Exiting deleteRole");
        }
    }
}
