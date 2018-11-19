package com.apap.silabor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.silabor.model.JenisPemeriksaanModel;

@Repository
public interface JenisPemeriksaanDb extends JpaRepository<JenisPemeriksaanModel, Long> {

}
