package com.apap.silabor.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.silabor.model.JadwalJagaModel;

@Repository
public interface JadwalJagaDb extends JpaRepository<JadwalJagaModel, Long> {

	List<JadwalJagaModel> findByTanggal(Date tanggal);

	JadwalJagaModel findById(long id);

}
