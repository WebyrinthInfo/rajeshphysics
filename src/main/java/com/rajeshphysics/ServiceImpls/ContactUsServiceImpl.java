package com.rajeshphysics.ServiceImpls;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rajeshphysics.Dtos.ContactUsDto;
import com.rajeshphysics.Exceptions.ForbbidonExceptions;
import com.rajeshphysics.Exceptions.ResourceAlreadyExistsException;
import com.rajeshphysics.Models.ContactUs;
import com.rajeshphysics.Payloads.PageableDataResponse;
import com.rajeshphysics.Repositories.ContactUsRepository;
import com.rajeshphysics.Services.ContactUsService;

@Service
public class ContactUsServiceImpl implements ContactUsService {
	 private static final Logger logger = LoggerFactory.getLogger(ContactUsServiceImpl.class);

	    @Autowired
	    private ContactUsRepository ContactUsRepo;

	    @Autowired
	    private ModelMapper modelMapper;
	    
	   

	    @Override
	    public ContactUsDto addContactUs(ContactUsDto ContactUsDto) {
	        logger.info("Adding a new ContactUs: {}", ContactUsDto.getName());
	        
	        ContactUs contactUs = modelMapper.map(ContactUsDto, ContactUs.class);
	        contactUs.setName(ContactUsDto.getName().trim());
	        contactUs.setMobile(ContactUsDto.getMobile().trim());
	        contactUs.setMessage(ContactUsDto.getMessage().trim());
	     
	        ContactUs savedContactUs = ContactUsRepo.save(contactUs);
	        logger.info("ContactUs added successfully: {}", savedContactUs.getName());

	        return modelMapper.map(savedContactUs, ContactUsDto.class);
	    }
	    



	    @Override
	    public PageableDataResponse<List<ContactUs>> getAllContactUs(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String search) {
	        try {
	            Sort sorting = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
	            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sorting);
	            Page<ContactUs> contactUsPage;

	            if (search == null || search.isEmpty()) {
	                contactUsPage = ContactUsRepo.findAll(pageRequest);
	            } else {
	                contactUsPage = ContactUsRepo.findByKeyword(search, pageRequest);
	            }

	            List<ContactUs> content = contactUsPage.getContent();
	            PageableDataResponse<List<ContactUs>> response = new PageableDataResponse<>();
	            response.setContent(content);
	            response.setPageNumber(contactUsPage.getNumber());
	            response.setPageSize(contactUsPage.getSize());
	            response.setTotalElements(contactUsPage.getTotalElements());
	            response.setTotalPages(contactUsPage.getTotalPages());
	            response.setLastPage(contactUsPage.isLast());
	            
	            logger.info("Fetched {} ContactUs (page {}/{})", content.size(), contactUsPage.getNumber() + 1, contactUsPage.getTotalPages());
	            return response;

	        } catch (Exception e) {
	            logger.error("Error occurred while fetching ContactUss: ", e);
	            throw new ForbbidonExceptions("Something went wrong while fetching ContactUs!");
	        }
	    }

}
