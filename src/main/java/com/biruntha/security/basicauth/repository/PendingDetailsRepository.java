package com.biruntha.security.basicauth.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.biruntha.security.basicauth.models.PendingDetails;

@Repository
public interface PendingDetailsRepository extends MongoRepository<PendingDetails, Integer> {
    @Autowired
        @Query("{$or: [ { 'pending': { $regex: ?0 , $options: 'i' } }, { 'buyerName':{ $regex: ?0, $options: 'i' } },{ 'buyerAddress': { $regex: ?0 , $options: 'i' } },{ 'buyerPhoneNum': { $regex: ?0 , $options: 'i' } } ]}")
        List<PendingDetails> findBySearchContaining(String searched);

    }
