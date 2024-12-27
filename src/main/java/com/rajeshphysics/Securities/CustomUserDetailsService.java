package com.rajeshphysics.Securities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rajeshphysics.Exceptions.ResourceNotFoundException;
import com.rajeshphysics.Models.User;
import com.rajeshphysics.Repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Starting loadUserByUsername() with username: {}", username);

        try {
            User user = userRepository.findByMobile(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with mobile: " + username));
            logger.info("Successfully executed loadUserByUsername() for username: {}", username);
            return user;
        } catch (ResourceNotFoundException ex) {
            logger.error("Error in loadUserByUsername(): User not found", ex);
            throw new UsernameNotFoundException("Invalid username or password");
        } catch (Exception ex) {
            logger.error("Unexpected error in loadUserByUsername()", ex);
            throw new UsernameNotFoundException("An unexpected error occurred");
        }
    }
}
