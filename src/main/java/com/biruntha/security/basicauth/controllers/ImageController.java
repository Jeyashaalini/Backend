package com.biruntha.security.basicauth.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.biruntha.security.basicauth.models.Image;
import com.biruntha.security.basicauth.services.ImageService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/images")
public class ImageController {
		@Autowired
	    private ImageService imageService;
		
		@PostMapping
	    public ResponseEntity<Image> createImage(@RequestBody Image image){
			return imageService.createImage(image);
		}
		
		@GetMapping
	    public ResponseEntity<List<Image>> listImages(){
			return imageService.listImages();
	    }
		
		@GetMapping("/{id}")
		public ResponseEntity<Image> getImageById(@PathVariable Integer id){
		    return imageService.getImageById(id);
		}
		
		@GetMapping(params = "title")
		public ResponseEntity<Image> getImageByTitle(@RequestParam String Title){
		    return imageService.getImageByTitle(Title);
		}
		
		@PutMapping("/{id}")
	    public ResponseEntity<Image> updateImage(@PathVariable("id") Integer id, @RequestBody Image image) {
	        return imageService.updateImage(id, image);
	    }
		
		@DeleteMapping("/{id}")
		public ResponseEntity<HttpStatus> deleteImage(@PathVariable int id) {
		    return imageService.deleteImage(id);
		}
		
		@GetMapping("/search/{search}")
		public ResponseEntity<?> getImage(@PathVariable String search, @RequestParam(value="pageNo",defaultValue="0") int pageNo,
		@RequestParam(value="pageSize",defaultValue="0") int pageSize,@RequestParam(name = "sortBy", defaultValue = "id") String sortBy){
		return imageService.getImageBySearch(search,pageNo,pageSize,sortBy);
		} 
		
		@GetMapping("/page")
		public ResponseEntity<Map<String, Object>> getImage(@RequestParam(value="pageNo",defaultValue="0") int pageNo,
		@RequestParam(value="pageSize",defaultValue="0") int pageSize,@RequestParam(name = "sortBy", defaultValue = "id") String sortBy){
		return imageService.getImage(pageNo,pageSize,sortBy);
		}

	}
