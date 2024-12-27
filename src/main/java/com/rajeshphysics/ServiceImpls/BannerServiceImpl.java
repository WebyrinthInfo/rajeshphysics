package com.rajeshphysics.ServiceImpls;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rajeshphysics.Exceptions.ResourceNotFoundException;
import com.rajeshphysics.Models.Banner;
import com.rajeshphysics.Payloads.PageableDataResponse;
import com.rajeshphysics.Repositories.BannerRepository;
import com.rajeshphysics.Services.BannerService;

@Service
public class BannerServiceImpl implements BannerService {
	private static final Logger logger = LoggerFactory.getLogger(BannerServiceImpl.class);
	
	@Autowired
	private BannerRepository bannerRepo;
	
	@Value("${app.banner-folder}")
	private String bannerFolder;

//	-----------------{ upload banner}----------------------
	@Override
	public Banner saveBanner(MultipartFile file,String desc) throws Exception {
//		String uriString = ServletUriComponentsBuilder.fromCurrentContextPath().path("/upload/banner").toUriString();
//		System.out.println("Path :>> "+ uriString);
		File fl = new File(bannerFolder);
		if(!fl.exists()) {
			boolean mkdir = fl.mkdirs();
			if(mkdir == true) {
				logger.info(bannerFolder+": is created Successully! : {} ",LocalDateTime.now());
			}else {
				logger.info(bannerFolder+": is Not created ! : {} ",LocalDateTime.now());
			}
			
		}else {
			logger.info(bannerFolder+": is Already created Successully! : {} ",LocalDateTime.now());
		}
			Banner info = null;
			
	        Random random = new Random();
	        int randomNumber = random.nextInt(100000);
	        String fileName = randomNumber+file.getOriginalFilename();
	        logger.info("file name :>> "+fileName);
	        long fileSize = Math.round(file.getSize()/1024);
	         
	        
	        boolean filesaver =  fileSaver(file, fileName);
	        
	        if(filesaver==true) {
	        	Banner banner = new Banner();
	        	banner.setFileName(fileName);
	        	banner.setIsActive(0);
	        	banner.setLink(ServletUriComponentsBuilder.fromCurrentContextPath().path(bannerFolder+File.separator).path(fileName).toUriString());
	        	
	        	banner.setOriginalName(file.getOriginalFilename());
	        	banner.setSize(fileSize+" MB");
	        	banner.setType(file.getContentType());
	        	banner.setDescription(desc);
	        	info = bannerRepo.save(banner);
	        	return info;
	        }else {
	        	return info;
	        }
	}
	
	

	private boolean fileSaver(MultipartFile file,String fileName) throws Exception {
		boolean f = false;
		try {
			InputStream is  = file.getInputStream();
	        byte data[] = new byte[is.available()];
	        is.read(data);
	        FileOutputStream fos = new FileOutputStream(bannerFolder+File.separator+fileName);
	        fos.write(data);
	        fos.flush();
	        fos.close();
	        f=true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Something went Wrong!!");
		}
		return f;
	}


//----------- { get all banners by default 7 and desc order }---------------------
	@Override
	public PageableDataResponse<List<Banner>> getAllBanners(Integer pageNumber, Integer pageSize, String sortBy,
			String sortDir) {
		Sort sorting = null;
		if(sortDir.equalsIgnoreCase("desc")) {
			sorting = Sort.by(sortBy).descending();
		}else {
			sorting = Sort.by(sortBy).ascending();
		}
			
		PageRequest page = PageRequest.of(pageNumber, pageSize, sorting);
		Page<Banner> pageBanner = bannerRepo.findAll(page);
		List<Banner> content = pageBanner.getContent();
		PageableDataResponse<List<Banner>> pr = new PageableDataResponse<>();
		pr.setContent(content);
		pr.setPageNumber(pageBanner.getNumber());
		pr.setPageSize(pageBanner.getSize());
		pr.setTotalElements(pageBanner.getTotalElements());
		pr.setTotalPages(pageBanner.getTotalPages());
		pr.setLastPage(pageBanner.isLast());
		return pr;
	}


//----------------------{ Delete banner by id }-------------------------------------
	@Override
	public boolean deleteBanner(Long id) {
		Banner banner = bannerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Banner is not found with : "+ id));
		if(banner!=null) {
			bannerRepo.deleteById(id);
		}
		return true;
	}
	

}
