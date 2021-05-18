package com.biruntha.security.basicauth.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public interface PendingDetailsRepositoryCustom {
    @Autowired
   public int getMaxPendingDetailID(); 
}
