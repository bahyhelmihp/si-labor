package com.apap.silabor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.silabor.model.PemeriksaanModel;
import com.apap.silabor.model.JenisPemeriksaanModel;

@Repository
public interface PemeriksaanDb extends JpaRepository<PemeriksaanModel, Long>{
	PemeriksaanModel findByIdPasienAndJenisPemeriksaan(long idPasien, JenisPemeriksaanModel jenisPemeriksaanModel);
}
