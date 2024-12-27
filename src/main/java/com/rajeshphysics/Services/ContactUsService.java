package com.rajeshphysics.Services;

import java.util.List;

import com.rajeshphysics.Dtos.ContactUsDto;
import com.rajeshphysics.Models.ContactUs;
import com.rajeshphysics.Payloads.PageableDataResponse;

public interface ContactUsService {
	public ContactUsDto addContactUs(ContactUsDto ContactUsDto);
	public PageableDataResponse<List<ContactUs>> getAllContactUs(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String search); 

}
