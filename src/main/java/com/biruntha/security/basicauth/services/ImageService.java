package com.biruntha.security.basicauth.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.biruntha.security.basicauth.models.Image;
import com.biruntha.security.basicauth.repository.ImageDao;
import com.biruntha.security.basicauth.repository.ImageRepositoryCustom;

@Service
public class ImageService {
	@Autowired
	private ImageDao imageDao;
	
	@Autowired
	private ImageRepositoryCustom imageRepositoryCustom;
	
	public ResponseEntity<Image> createImage(Image image) {
		try {
			Integer id = imageRepositoryCustom.getMaxImageId() + 1;
			Image imageNew = imageDao.save(new Image(id, image.getImage(), image.getImageurl(), image.getTitle() ));
		    return new ResponseEntity<>(imageNew, HttpStatus.CREATED);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	public ResponseEntity<List<Image>> listImages() {
		try {
		    List<Image> images = new ArrayList<Image>();
		    imageDao.findAll().forEach(images::add);
		    if (images.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		return new ResponseEntity<>(images, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<Image> getImageById(Integer id) {
		Optional<Image> image = imageDao.findById(id);
		if (image.isPresent()) {
			return new ResponseEntity<>(image.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Image> updateImage(Integer id, Image image) {
		Optional<Image> imageData = imageDao.findById(id);

		if (imageData.isPresent()) {
			Image imageOld = imageData.get();
			imageOld.setTitle(image.getTitle());
			imageOld.setImage(image.getImage());
			imageOld.setImageurl(image.getImageurl());
		    return new ResponseEntity<>(imageDao.save(imageOld), HttpStatus.OK);
		} else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<HttpStatus> deleteImage(int id) {
		Optional<Image> image = imageDao.findById(id);
		if (image.isPresent()) {
			imageDao.delete(image.get());
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Image> getImageByTitle(String title) {
		Optional<Image> image = imageDao.findByTitle(title);
		if (image.isPresent()) {
			return new ResponseEntity<>(image.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<Page<Image>> getImageBySearch(String searchText, int pageNo, int pageSize, String sortBy) {
		try {
		// List<BookModel> book = bookRepository.findByTitleContaining(title);

		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Image> imagePages = imageDao.searchImage(pageable, ".*" + searchText + ".*");
		// if (bookPages.isEmpty()) {
		// return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		// }
		return new ResponseEntity<>(imagePages, HttpStatus.OK);
		} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}
	
	public ResponseEntity<Map<String, Object>> getImage(int pageNo, int pageSize, String sortBy) {
	  try {System.out.println(pageNo);
	  System.out.println(pageSize);
	  System.out.println(sortBy);
	  Map<String, Object> response = new HashMap<>();
	    Sort sort = Sort.by(sortBy);
	    System.out.println(sort);
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		 System.out.println(pageable);
	    Page<Image> page = imageDao.findAll(pageable);
	    System.out.println(page);
	    response.put("data", page.getContent());
	    System.out.println(response);
	    response.put("Total no of pages", page.getTotalPages());
	    response.put("Total no of elements", page.getTotalElements());
	    response.put("Current page no", page.getNumber());
		    
		    return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
