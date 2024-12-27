package com.rajeshphysics.ServiceImpls;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rajeshphysics.Exceptions.ForbbidonExceptions;
import com.rajeshphysics.Exceptions.ResourceAlreadyExistsException;
import com.rajeshphysics.Exceptions.ResourceNotFoundException;
//import com.rajeshphysics.Models.Course;
import com.rajeshphysics.Models.Role;
import com.rajeshphysics.Models.User;
import com.rajeshphysics.Payloads.PageableDataResponse;
//import com.rajeshphysics.Repositories.CourseRepository;
import com.rajeshphysics.Repositories.RoleRepository;
import com.rajeshphysics.Repositories.UserRepository;
import com.rajeshphysics.Services.UserService;
import com.rajeshphysics.Utils.JwtUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


@Service
public class UserServiceImpl implements UserService {
	public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class); 
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
//	@Autowired
//	private CourseRepository courseRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(User user, Long roleId, Long courseId) {
		LocalDate currentDate = LocalDate.now();
		LocalDate plusDays = currentDate.plusDays(3);
		DateTimeFormatter  dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formatedDate = dtf.format(plusDays);
		
		//create user and assign Role
			Optional<User> fbe = userRepo.findByMobile(user.getMobile());
			  boolean empty = fbe.isEmpty();
			if(empty == false) {
				throw new ResourceAlreadyExistsException("User is already exist : "+fbe.get().getMobile());
			}else {
				
//				---------------------get Role info ----------------------
				Role role2 = roleRepo.findById(roleId).orElseThrow(()-> new ResourceNotFoundException("Role is not Found  : "+roleId));
				List<Role>  roles = new ArrayList<>(); 
				roles.add(role2);
				
//				--------------------get Course info------------------------
//				Course courseInfo = courseRepo.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("Course is not Found  : "+courseId));
//				List<Course>  courses = new ArrayList<>(); 
//				courses.add(courseInfo);
				
				
//				----------------------set user and access token -----------
			
				user.setMobile(user.getMobile().trim());
				user.setRoles(roles);
				user.setStatus("ACTIVE");
				user.setIsActive(1);
				user.setAccountExpireAt(formatedDate);
//				String activationToken = null;
//				if(role2.getName()=="STUDENT") {
//					activationToken = commonUtils.generateActivationToken();
//				}
//				user.set(activationToken);
				user.setPassword(passwordEncoder.encode(user.getPassword().trim()));
				String generateToken = jwtUtil.generateToken(user);
				logger.info("Generate Token Successfully");
				user.setJwtToken(generateToken);
				User save = userRepo.save(user);
//				if(role2.getName()=="STUDENT") {
//					commonUtils.sendActivationEmail(user.getEmail(), activationToken);
//				}
				
				return save;
		}
	}
	
	@Override
	public User updateUserToken(String username, Long days, String isPaid) {
		User user = userRepo.findByMobile(username).orElseThrow(()-> new ResourceNotFoundException("username is not found : "+ username));
		LocalDate currentDate = LocalDate.now();
		LocalDate plusDays = currentDate.plusDays(days);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String expitedDate = dtf.format(plusDays);
		user.setId(user.getId());
		user.setAccountExpireAt(expitedDate);
		user.setJwtToken(jwtUtil.generateTokenDays(user, days));
		user.setIsPaid(isPaid.trim().toUpperCase());
		User save = userRepo.save(user);
		return save;
	}
	
	@Override
	public PageableDataResponse<List<User>> getAllUser(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String search) {
		try {
			if(search==null || search == "") {
				Sort sorting = null;
				if(sortDir.equalsIgnoreCase("desc")) {
					sorting = Sort.by(sortBy).descending();
				}else {
					sorting = Sort.by(sortBy).ascending();
				}
					
				PageRequest page = PageRequest.of(pageNumber, pageSize, sorting);
				Page<User> pageUser = userRepo.findAll(page);
				List<User> content = pageUser.getContent();
				PageableDataResponse<List<User>> pr = new PageableDataResponse<>();
				pr.setContent(content);
				pr.setPageNumber(pageUser.getNumber());
				pr.setPageSize(pageUser.getSize());
				pr.setTotalElements(pageUser.getTotalElements());
				pr.setTotalPages(pageUser.getTotalPages());
				pr.setLastPage(pageUser.isLast());
				return pr;
				
			}else {
				Sort sorting = null;
				if(sortDir.equalsIgnoreCase("desc")) {
					sorting = Sort.by(sortBy).descending();
				}else {
					sorting = Sort.by(sortBy).ascending();
				}
					
				PageRequest page = PageRequest.of(pageNumber, pageSize, sorting);
				Page<User> pageUser = userRepo.findByKeyword(search, page);
				List<User> content = pageUser.getContent();
				PageableDataResponse<List<User>> pr = new PageableDataResponse<>();
				pr.setContent(content);
				pr.setPageNumber(pageUser.getNumber());
				pr.setPageSize(pageUser.getSize());
				pr.setTotalElements(pageUser.getTotalElements());
				pr.setTotalPages(pageUser.getTotalPages());
				pr.setLastPage(pageUser.isLast());
				return pr;
			}
		} catch (Exception e) {
//			e.printStackTrace();
			throw new  ForbbidonExceptions("Something Went Wrong !!");
		}
		
	}
	
	
	@Override
	public User getUserInfo(String username) {
		User user = userRepo.findByMobile(username).orElseThrow(()-> new ResourceNotFoundException("username is not found : "+ username));
		return user;	
	}

}
