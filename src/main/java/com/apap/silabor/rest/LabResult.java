package com.apap.silabor.rest;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LabResult {
	private long id;

	private String jenis;

	private String hasil;

	private Date tanggalPengajuan;

	private PasienTest pasien;

	public String getJenis() {
		return jenis;
	}

	public void setJenis(String jenis) {
		this.jenis = jenis;
	}

	public String getHasil() {
		return hasil;
	}

	public void setHasil(String hasil) {
		this.hasil = hasil;
	}

	public Date getTanggalPengajuan() {
		return tanggalPengajuan;
	}

	public void setTanggalPengajuan(Date tanggalPengajuan) {
		this.tanggalPengajuan = tanggalPengajuan;
	}

	public PasienTest getPasien() {
		return pasien;
	}

	public void setPasien(PasienTest pasien) {
		this.pasien = pasien;
	}

}
