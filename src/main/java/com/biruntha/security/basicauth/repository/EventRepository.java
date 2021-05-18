package com.biruntha.security.basicauth.repository;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.biruntha.security.basicauth.models.Event;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

	// @Query("{'$or':[ {'title': {$regex : ?0, $options: 'i'}},\"{'imageUrl': {$regex : ?0, $options: 'i'}} "
	// 		+"{'description': {$regex : ?0, $options: 'i'}}, \" , {'date': {$regex : ?0, $options: 'i'}}\" , {'image': {$regex : ?0, $options: 'i'}}]}")
	// Page<Event> searchEvent(String searchText, Pageable pageable);

}
