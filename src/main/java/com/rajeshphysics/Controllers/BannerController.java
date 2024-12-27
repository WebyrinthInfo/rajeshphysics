package com.rajeshphysics.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rajeshphysics.Models.Banner;
import com.rajeshphysics.Payloads.AppConstrants;
import com.rajeshphysics.Payloads.GenericResponse;
import com.rajeshphysics.Payloads.PageableDataResponse;
import com.rajeshphysics.Services.BannerService;


@RestController
@RequestMapping("/api/banner")
@CrossOrigin("*")
public class BannerController {
	@Autowired
	private BannerService bannerServe;
	
//	----------------------{ upload banner }----------------------------------------
	@PostMapping("/upload")
	public ResponseEntity<GenericResponse<Banner>> uploadBanner(GenericResponse<Banner> response , @RequestParam MultipartFile file, @RequestParam ("desc") String desc ) throws Exception{
		
		if(file.isEmpty()) {
			response.setData(null);
			response.setStatus("INVALID PARAMETER");
			response.setMsg("File must be Required");
			return new ResponseEntity<GenericResponse<Banner>>(response, HttpStatus.BAD_REQUEST);
		}else if((Math.round(file.getSize()/1024))>1024) {
			response.setData(null);
			response.setStatus("INVALID PARAMETER");
			response.setMsg("File is must be less than 11 MB Your File Size is :>> "+Math.round(file.getSize()/1024)+" MB");
			return new ResponseEntity<GenericResponse<Banner>>(response, HttpStatus.BAD_REQUEST);
//		}else if(!(file.getContentType().toString().equals("image/png") || file.getContentType().toString().equals("image/jpeg")) ) {
//			response.setData(null);
//			response.setStatus(0);
//			response.setMsg("Sorry! Your file type is "+file.getContentType()+". File must be { PNG } Only!!");
//			return new ResponseEntity<GenericResponse<Banner>>(response, HttpStatus.BAD_REQUEST);
//		}else {
		}else{Banner saveBanner = bannerServe.saveBanner(file, desc);
			if(saveBanner!=null) {
				response.setData(saveBanner);
				response.setStatus("SUCCESS");
				response.setMsg("Banner save successfully!");
				return new ResponseEntity<GenericResponse<Banner>>(response, HttpStatus.OK);
			}else {
				response.setData(null);
				response.setStatus("FAILURE");
				return new ResponseEntity<GenericResponse<Banner>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
//	-------------{ get All banner }-------------------------------------
	@GetMapping("/getAll")
	public ResponseEntity<GenericResponse<PageableDataResponse<List<Banner>>>> getAllBanners(
			@RequestParam(value = "pageNumber", defaultValue = AppConstrants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstrants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstrants.SORT_BY_ID, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstrants.SORT_DIR_DESC, required = false) String sortDir,
			GenericResponse<PageableDataResponse<List<Banner>>> response) {
		PageableDataResponse<List<Banner>> allBanners = bannerServe.getAllBanners(pageNumber, pageSize, sortBy, sortDir);
		if (allBanners != null) {
			response.setData(allBanners);
			response.setStatus("SUCCESS");
			response.setMsg("Fetch Successfully !!");
		} else {
			response.setData(allBanners);
			response.setStatus("SUCCESS");
			response.setMsg("Data not Available!");
		}
		return new ResponseEntity<GenericResponse<PageableDataResponse<List<Banner>>>>(response, HttpStatus.OK);
	}
	
	
//	---------------{ delete Banner }-----------------------------------
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<GenericResponse<Banner>> signleDeleteBanner(GenericResponse<Banner>response, @PathVariable ("id") long id){
		return null;
	}
	

}
