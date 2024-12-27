package com.rajeshphysics.ServiceImpls;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.relation.RoleNotFoundException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajeshphysics.Dtos.RoleDto;
import com.rajeshphysics.Exceptions.ResourceAlreadyExistsException;
import com.rajeshphysics.Models.Role;
import com.rajeshphysics.Repositories.RoleRepository;
import com.rajeshphysics.Services.RoleService;



@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoleDto addRole(RoleDto roleDto) {
        // Check if the role already exists
        Optional<Role> existingRole = roleRepo.findByName(roleDto.getName());
        if (existingRole.isPresent()) {
            logger.warn("Attempt to add existing role: {}", roleDto.getName());
            throw new ResourceAlreadyExistsException("Role '" + roleDto.getName() + "' already exists.");
        }

        // Use ModelMapper to convert DTO to Entity
        Role newRole = new Role();
        newRole.setIsActive(0);
        newRole.setName(roleDto.getName().trim().toUpperCase());
        newRole.setDescription(roleDto.getDescription().trim());
        Role savedRole = roleRepo.save(newRole);
        logger.info("Role added successfully: {}", savedRole.getName());

        // Convert saved entity back to DTO and return it
        return modelMapper.map(savedRole, RoleDto.class);
    }
    
    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleRepo.findAll();
        return roles.stream()
                    .map(role -> modelMapper.map(role, RoleDto.class))
                    .collect(Collectors.toList());
    }
    
    @Override
    public RoleDto updateRole(Long roleId, RoleDto roleDto) throws RoleNotFoundException {
        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with ID: " + roleId));
        
        role.setName(roleDto.getName().trim().toUpperCase());
        role.setIsActive(roleDto.getIsActive());
        role.setDescription(roleDto.getDescription().trim());
        // Update other fields if necessary
        
        Role updatedRole = roleRepo.save(role);
        logger.info("Role updated successfully: {}", updatedRole.getName());

        return modelMapper.map(updatedRole, RoleDto.class);
    }
    
    @Override
    public void deleteRole(Long roleId) throws RoleNotFoundException {
        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with ID: " + roleId));
        roleRepo.delete(role);
        logger.info("Role deleted successfully: {}", role.getName());
    }
}
