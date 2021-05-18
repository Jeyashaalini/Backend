package com.biruntha.security.basicauth.controllers;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biruntha.security.basicauth.models.PendingDetails;
import com.biruntha.security.basicauth.services.PendingDetailsService;

@CrossOrigin (origins = {"http://localhost:3000"})
@RestController
@RequestMapping(value = "/pending")
public class PendingDetailsController {

        @Autowired
        PendingDetailsService pendingDetailsService;
        @PostMapping
        public ResponseEntity<PendingDetails> createPending (@RequestBody PendingDetails pendingDetails){
            return pendingDetailsService.createPending(pendingDetails);
        }
        @DeleteMapping(value = "/{id}") 
        public ResponseEntity<PendingDetails> deletePending(@PathVariable int id){
            return pendingDetailsService.deletePending(id);
        }

        @GetMapping (value = "/page")
        public ResponseEntity <Map<String, Object>> getAllPendings(
                @RequestParam(name = "pageNo",defaultValue = "0") int pageNo,
                @RequestParam(name = "pageSize",defaultValue = "5") int pageSize){
            return pendingDetailsService.getAllPendings(pageNo,pageSize);
        }

        @GetMapping(value = "/page/searchedPages")       
        public ResponseEntity<Map<String,Object>> getSearchedPendings(
                @RequestParam(name = "searched",defaultValue = "null") String searched,
                @RequestParam(name = "pageNo",defaultValue = "1") int pageNo,
                @RequestParam(name = "pageSize",defaultValue = "5") int pageSize
        ){
            return pendingDetailsService.searchedUpload(searched,pageNo,pageSize);
        }
    }
