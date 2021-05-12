package com.biruntha.security.basicauth.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biruntha.security.basicauth.models.Upload;
import com.biruntha.security.basicauth.services.UploadService;

@CrossOrigin (origins="http://localhost:3000")
@RestController
@RequestMapping(value = "/upload")
public class UploadController {

	@Autowired
	private UploadService uploadService;
	
	@PostMapping
	public ResponseEntity<Upload> createUpload(@RequestBody Upload upload){
		System.out.println("hi");
		return uploadService.createUpload(upload);
	}
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<Upload> getUpload(@PathVariable String id){
		return uploadService.getUploadById(id);
	}
	
	@GetMapping (value = "/page")
	public ResponseEntity <Map<String, Object>> getAllUpload(
			@RequestParam(name = "pageNo",defaultValue = "0") int pageNo,
			@RequestParam(name = "pageSize",defaultValue = "5") int pageSize){
		return uploadService.getAllUpload(pageNo,pageSize);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Upload>deleteUpload(@PathVariable String id){
		return uploadService.deleteUpload(id);
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Upload> updateUpload(@PathVariable String id, @RequestBody Upload upload){
		return uploadService.updateUpload(id,upload);
	}
	
	@GetMapping(value = "/page/serachedPages")
	public ResponseEntity<Map<String,Object>> getSerchedUpload(
			@RequestParam(name = "serched",defaultValue = "null") String searched,
			@RequestParam(name = "pageNo",defaultValue = "0") int pageNo,
			@RequestParam(name = "pageSize",defaultValue = "5") int pageSize
			){
		return uploadService.searchedUpload(searched,pageNo,pageSize);
	}
	
	
	@GetMapping(value = "/page/filteredpage")
	public ResponseEntity<Map<String,Object>> getFilteredUpload(
			@RequestParam(name = "usage",defaultValue = "null") String medium,
			@RequestParam(name = "category",defaultValue = "null") String category,
			@RequestParam(name = "minPrice",defaultValue = "0") int minPrice,
			@RequestParam(name = "maxPrice",defaultValue = "10000") int maxPrice,
			@RequestParam(name = "pageNo",defaultValue = "0") int pageNo,
			@RequestParam(name = "pageSize",defaultValue = "5") int pageSize
			){
		return uploadService.getFilteredUpload(medium,category,minPrice,maxPrice,pageNo,pageSize);
	}
	
}
