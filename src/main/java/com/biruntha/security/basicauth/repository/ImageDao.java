package com.biruntha.security.basicauth.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.biruntha.security.basicauth.models.Image;

@Repository
public interface ImageDao extends MongoRepository<Image, Integer> {
	
Optional<Image> findByTitle(String title);

	@Query("{'$or':[ {'title': {$regex : ?0, $options: 'i'}}, {'image': {$regex : ?0, $options: 'i'}}, {'imageUrl': {$regex : ?0, $options: 'i'}}]}")
	
	Page<Image> searchImage(Pageable pageable, String searchText); 

}
