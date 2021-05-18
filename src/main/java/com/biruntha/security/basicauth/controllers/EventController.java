package com.biruntha.security.basicauth.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biruntha.security.basicauth.models.Event;
import com.biruntha.security.basicauth.services.EventService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/events")

public class EventController {

	@Autowired
    EventService eventService;

	@PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event){
		return eventService.createEvent(event);
	}


	@GetMapping
   public ResponseEntity<List<Event>> getAllEvents(){
		System.out.println("Hiiiii");
		return eventService.getAllEvents();
   }

	@GetMapping(params="id")
	public ResponseEntity<Event> getEventById(@RequestParam String id)
	{ return eventService.getEventById(id); }

	@GetMapping("/page")
	public ResponseEntity<Map<String, Object>> getEvent(@RequestParam(value="pageNo",defaultValue="0") int pageNo,
			@RequestParam(value="pageSize",defaultValue="2") int pageSize,@RequestParam(name = "sortBy", defaultValue = "id") String sortBy){
		return eventService.getAllEventsInPage(pageNo,pageSize,sortBy);
	}

}
