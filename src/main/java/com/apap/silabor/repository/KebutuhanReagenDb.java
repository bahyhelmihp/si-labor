package com.apap.silabor.repository;

import com.apap.silabor.model.KebutuhanReagenModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * KebutuhanReagenDb
 */
@Repository
public interface KebutuhanReagenDb extends JpaRepository<KebutuhanReagenModel, Long>{
	

}
