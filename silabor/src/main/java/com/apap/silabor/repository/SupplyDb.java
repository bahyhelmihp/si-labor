package com.apap.silabor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.silabor.model.SupplyModel;

@Repository
public interface SupplyDb extends JpaRepository<SupplyModel, Long> {

}
