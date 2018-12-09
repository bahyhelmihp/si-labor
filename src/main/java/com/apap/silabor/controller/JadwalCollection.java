package com.apap.silabor.controller;

import java.util.ArrayList;
import java.util.List;

import com.apap.silabor.model.JadwalJagaModel;

public class JadwalCollection {

	List<JadwalJagaModel> jadwalColl;

	public JadwalCollection() {
		super();
		this.jadwalColl = new ArrayList<JadwalJagaModel>();
	}

	public List<JadwalJagaModel> getJadwalColl() {
		return jadwalColl;
	}

	public void setJadwalColl(List<JadwalJagaModel> jadwalColl) {
		this.jadwalColl = jadwalColl;
	}
	
	public void addJadwal(JadwalJagaModel jadwal) {
		this.jadwalColl.add(jadwal);
	}
	
	public void removeJadwal(JadwalJagaModel jadwal) {
		this.jadwalColl.remove(jadwal);
	}
	
}

