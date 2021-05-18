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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.biruntha.security.basicauth.models.Event;
import com.biruntha.security.basicauth.repository.EventRepository;

@Service
public class EventService {
	@Autowired
	EventRepository eventRepository;

	public ResponseEntity<Event> createEvent (Event event) {
		try {
			Event ff=eventRepository.save(event);
		       return new ResponseEntity<>(ff, HttpStatus.CREATED);
		       } catch (Exception e) {
		    	   return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    	   }
		 }

	public ResponseEntity<List<Event>> getAllEvents() {
		try {
			System.out.println("Hello");
			List<Event> events= new ArrayList<>();
			eventRepository.findAll().forEach(events::add);
			System.out.println(events);
			if (events.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(events,HttpStatus.OK);
		}
		}catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Map<String, Object>> getAllEventsInPage(int pageNo, int pageSize, String sortBy) {
		try {
			Map<String, Object> response = new HashMap<>();
		    Sort sort = Sort.by(sortBy);
			Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		    Page<Event> page = eventRepository.findAll(pageable);
		    response.put("data", page.getContent());
		    response.put("Total no of pages", page.getTotalPages());
		    response.put("Total no of elements", page.getTotalElements());
		    response.put("Current page no", page.getNumber());

		    return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	public ResponseEntity<Event> getEventById(String id)
	 { Optional<Event> ff = eventRepository.findById(id);
	if (ff.isPresent())
	 { return new ResponseEntity<>(ff.get(), HttpStatus.OK);
	 }
	else
	 { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
	 }

	public ResponseEntity<Event>updateEvent
	(@PathVariable  String id,@RequestBody Event event){
		Optional<Event> p=eventRepository.findById(id);
		if(p.isPresent()) {
			Event _event=p.get();
			_event.setTitle(event.getTitle());
			_event.setImageUrl(event.getImageUrl());
			_event.setDescription(event.getDescription());
			_event.setImage(event.getImage());
			_event.setDate(event.getDate());

			Event updatedEvent=eventRepository.save(_event);
			return new ResponseEntity<>(updatedEvent,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	public ResponseEntity<HttpStatus> deleteEvent(@PathVariable String id){
		try {
			Optional<Event> p=eventRepository.findById(id);
			if(p.isPresent()) {
				eventRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// public ResponseEntity<Page<Event>> getEventBySearch(String searchText, int pageNo, int pageSize, String sortBy) {
	//     try {
	//     	Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
	//     	Page<Event> eventPages = eventRepository.searchEvent(".*" + searchText + ".*", pageable);
	//     if (eventPages.isEmpty()) {
	//              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	//     }
	//         return new ResponseEntity<>(eventPages, HttpStatus.OK);
	//     } catch (Exception e) {
	//         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	//     }
	// }


}
