package com.biruntha.security.basicauth.repository;
import com.biruntha.security.basicauth.models.PaymentRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends MongoRepository<PaymentRequest,String>{
}
