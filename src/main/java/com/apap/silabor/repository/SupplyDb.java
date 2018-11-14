package com.apap.silabor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apap.silabor.model.SupplyModel;

public interface SupplyDb extends JpaRepository<SupplyModel, Long> {

}
