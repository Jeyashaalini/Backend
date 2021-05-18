package com.biruntha.security.basicauth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biruntha.security.basicauth.models.PaymentRequest;
import com.biruntha.security.basicauth.services.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
    @CrossOrigin("*")
    @RestController
    @RequestMapping("/api/auth/payment")
    public class PaymentController {

        @Autowired
        PaymentService service;

        @GetMapping
        public ResponseEntity<List<PaymentRequest>> getAllCardDetails() {
            return service.getAllCardDetails();
        }

        @PostMapping
        public ResponseEntity<String> completePayment(@RequestBody PaymentRequest request) throws StripeException {
            String chargeId= service.charge(request);
            return chargeId!=null? new ResponseEntity<String>(chargeId,HttpStatus.OK):
                    new ResponseEntity<String>("Please check the credit card details entered",HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler
        public String handleError(StripeException ex) {
            return ex.getMessage();
        }
}
