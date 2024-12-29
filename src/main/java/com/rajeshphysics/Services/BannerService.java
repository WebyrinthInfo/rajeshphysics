package com.rajeshphysics.Services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rajeshphysics.Models.Banner;
import com.rajeshphysics.Payloads.PageableDataResponse;


public interface BannerService {
	public Banner saveBanner(MultipartFile file,String desc) throws Exception ;
	public PageableDataResponse<List<Banner>> getAllBanners(Integer pageNumber, Integer pageSize,String sortBy, String sortDir);
	public boolean deleteBanner(Long id);
	

}
