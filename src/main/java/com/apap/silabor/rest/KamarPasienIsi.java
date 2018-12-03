package com.apap.silabor.rest;

import java.sql.Date;

public class KamarPasienIsi {
	private long id;

	private long id_pasien;

	private boolean active;
	
	private String status;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_pasien() {
		return id_pasien;
	}

	public void setId_pasien(long id_pasien) {
		this.id_pasien = id_pasien;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNomor_kamar() {
		return nomor_kamar;
	}

	public void setNomor_kamar(String nomor_kamar) {
		this.nomor_kamar = nomor_kamar;
	}

	private String nomor_kamar;

}
