package com.apap.silabor.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.silabor.model.JadwalJagaModel;
import com.apap.silabor.model.UserRoleModel;

@Repository
public interface JadwalJagaDb extends JpaRepository<JadwalJagaModel, Long> {
	
	JadwalJagaModel findByDate (Date tanggal);
	
}
