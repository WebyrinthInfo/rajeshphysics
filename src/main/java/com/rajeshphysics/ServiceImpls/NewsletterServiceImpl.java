package com.rajeshphysics.ServiceImpls;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajeshphysics.Dtos.NewsletterDto;
import com.rajeshphysics.Exceptions.ResourceAlreadyExistsException;
import com.rajeshphysics.Models.Newsletter;
import com.rajeshphysics.Repositories.NewsletterRepository;
import com.rajeshphysics.Services.NewsletterService;
@Service
public class NewsletterServiceImpl implements NewsletterService {
	private static final Logger logger = LoggerFactory.getLogger(NewsletterServiceImpl.class);
	
	@Autowired
	private NewsletterRepository newsletterRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public NewsletterDto addNewsletter(NewsletterDto newsletterDto) {
		 logger.info("Adding a new Newsletter : {}", newsletterDto.getMobile());
	        
	        Optional<Newsletter> existingInfo = newsletterRepo.findByMobile(newsletterDto.getMobile().trim());
	        if (existingInfo.isPresent()) {
	            logger.warn("Attempt to add existing Newsletter: {}", newsletterDto.getMobile());
	            throw new ResourceAlreadyExistsException("Newsletter '" + newsletterDto.getMobile() + "' already exists.");
	        }

	        Newsletter newsletter = modelMapper.map(newsletterDto, Newsletter.class);
	        newsletter.setMobile(newsletterDto.getMobile().trim());

	        Newsletter savedNewsletter = newsletterRepo.save(newsletter);
	        logger.info("Newsletter added successfully: {}", savedNewsletter.getMobile());

	        return modelMapper.map(savedNewsletter, NewsletterDto.class);	
	}

}
