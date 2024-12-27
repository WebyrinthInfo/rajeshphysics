package com.rajeshphysics;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rajeshphysics.Models.Role;
import com.rajeshphysics.Models.User;
import com.rajeshphysics.Repositories.RoleRepository;
import com.rajeshphysics.Repositories.UserRepository;
import com.rajeshphysics.Utils.JwtUtil;

@SpringBootApplication
public class RajeshphysicsApplication extends SpringBootServletInitializer implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(RajeshphysicsApplication.class);
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private JwtUtil jwtUtil;

	public static void main(String[] args) {
		SpringApplication.run(RajeshphysicsApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(RajeshphysicsApplication.class);
	}

	@Override
	public void run(String... args) throws Exception {
//		------------------adding role and user for development and Maintenance-------------------------
		addRoleAndUser();
	}
	

	public void createStudentAndAdminRoleCreated() {
		logger.info("Checking Role is exit or Not");
		Optional<Role> info1 = roleRepo.findByName("ADMIN");

		if (!info1.isPresent()) {
			logger.info("adding role ADMIN & STUDENT .........");
			// ----------------------Adding ADMIN by Role ---------------
			Role role1 = new Role();
			role1.setId(2L);
			role1.setName("ADMIN");
			role1.setIsActive(1);
			role1.setDescription("this roles allow all apis to access.");
			roleRepo.save(role1);
			logger.info("Role Save SuccessFully with : ADMIN");
		} else {
			logger.warn("Roles is already added : ADMIN");
		}
		
		Optional<Role> info2 = roleRepo.findByName("STUDENT");
		if (!info2.isPresent()) {
			Role role2 = new Role();
			role2.setId(3L);
			role2.setName("STUDENT");
			role2.setIsActive(1);
			role2.setDescription("this roles allow specific apis to access.");
			roleRepo.save(role2);
			logger.info("Role Save SuccessFully with : STUDENT");

		} else {
			logger.warn("Roles is already added : STUDENT");
		}
	}

	public void addRoleAndUser() {
		createStudentAndAdminRoleCreated();
		LocalDate currentDate = LocalDate.now();
		LocalDate plusDays = currentDate.plusDays(3);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formatedDate = dtf.format(plusDays);

		logger.info("Checking user is exit or Not");
		Optional<User> info = userRepo.findByMobile("9939718838");
		if (!info.isPresent()) {
			logger.info("adding user .........");
			// ----------------------Adding WY by Role ---------------
			List<Role> roles = new ArrayList<>();
			Role role1 = new Role();
			role1.setId(1L);
			role1.setName("WY");
			role1.setIsActive(1);
			role1.setDescription("this roles allow all apis to access.");
			roles.add(role1);

//			----------------------------------Adding  user and wy Role By Default---------------------
			User userInfo = new User();
			userInfo.setId(1L);
			userInfo.setFullName("webyrinth (P) Ltd.");
			userInfo.setAddress("Santi nagar jehanabad");
			userInfo.setIsActive(1);
			userInfo.setStatus("ACTIVE");
			userInfo.setMobile("9939718838");
			userInfo.setAccountExpireAt(formatedDate);
			userInfo.setPassword(passwordEncoder.encode("India@123"));
			userInfo.setJwtToken(jwtUtil.generateToken(userInfo));
			userInfo.setRoles(roles);

			userRepo.save(userInfo);
			logger.info("User Save SuccessFully : {}", userInfo.getMobile());
		} else {
			logger.warn("User is already added : {}", info.get().getMobile());
		}

	}

}
