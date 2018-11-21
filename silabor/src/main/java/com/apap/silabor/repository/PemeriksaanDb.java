package com.apap.silabor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.silabor.model.PemeriksaanModel;

@Repository
public interface PemeriksaanDb extends JpaRepository<PemeriksaanModel, Long>{

}
