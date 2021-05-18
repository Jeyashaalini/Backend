package com.biruntha.security.basicauth.services;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.biruntha.security.basicauth.models.PendingDetails;
import com.biruntha.security.basicauth.repository.PendingDetailsRepository;
 import com.biruntha.security.basicauth.repository.PendingDetailsRepositoryCustom;

@Service
public class PendingDetailsService {
        @Autowired
        PendingDetailsRepository pendingDetailsRepository;
        PendingDetailsRepositoryCustom pendingDetailsRepositoryCustom;

        public ResponseEntity<PendingDetails> createPending(PendingDetails pendingDetails) {
            try {
               int id = pendingDetailsRepositoryCustom.getMaxPendingDetailID()+1;
                PendingDetails newPending = pendingDetailsRepository.save(new PendingDetails(id,pendingDetails.getUploadId(),pendingDetails.getBuyerName(),pendingDetails.getBuyerAddress(),pendingDetails.getBuyerPhoneNum()));

                return new ResponseEntity<>(newPending,HttpStatus.CREATED);
            }catch(Exception e) {
                return new ResponseEntity<>(null,HttpStatus.SERVICE_UNAVAILABLE);
            }
        }

        public ResponseEntity<PendingDetails> deletePending(int id) {
            try {
                Optional<PendingDetails> pendingData = pendingDetailsRepository.findById(id);
                if(pendingData.isPresent()) {
                    pendingDetailsRepository.deleteById(id);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }catch(Exception e) {
                return new ResponseEntity<>(null,HttpStatus.SERVICE_UNAVAILABLE);
            }
        }

        public ResponseEntity<Map<String, Object>> getAllPendings(int pageNo, int pageSize) {
            try {
                Map<String, Object> response = new HashMap<>();
                Sort sort = Sort.by("id");
                Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
                Page<PendingDetails> page = pendingDetailsRepository.findAll(pageable);
                response.put("data", page.getContent());
                response.put("Total_No_Of_Pages", page.getTotalPages());
                response.put("Total_No_Of_Elements", page.getTotalElements());
                response.put("Current page no", page.getNumber());

                return new ResponseEntity<>(response, HttpStatus.OK);
            }catch(Exception e) {
                return new ResponseEntity<>(null,HttpStatus.SERVICE_UNAVAILABLE);
            }
        }

        public ResponseEntity<Map<String, Object>> searchedUpload(String searched, int pageNo, int pageSize) {

            List<PendingDetails> searchedUpload = pendingDetailsRepository.findBySearchContaining(searched);
            Map<String, Object> response = new HashMap<>();
            PagedListHolder<PendingDetails> page = new PagedListHolder<PendingDetails>(searchedUpload);
            page.setPageSize(pageSize); // number of items per page
            page.setPage(pageNo);

            response.put("data", page.getPageList());
            response.put("Total_No_Of_Elements", page.getNrOfElements());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }
