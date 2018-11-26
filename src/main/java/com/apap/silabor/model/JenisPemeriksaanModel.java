package com.apap.silabor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "jenis_pemeriksaan")
public class JenisPemeriksaanModel implements Serializable{
	// id, nama : varchar (255), id_supplies: INT
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@NotNull
	@Size(max=255)
	@Column(name= "nama", nullable = false)
	private String nama;
	
	@OneToMany(mappedBy = "jenisPemeriksaan", fetch = FetchType.LAZY)
	private List<PemeriksaanModel> listPemeriksaan = new ArrayList<PemeriksaanModel>();
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
	},
			mappedBy = "jenisPemeriksaanList")
	private List<SupplyModel> supplyList;
	
	public List<PemeriksaanModel> getListPemeriksaan() {
		return listPemeriksaan;
	}

	public void setListPemeriksaan(List<PemeriksaanModel> listPemeriksaan) {
		this.listPemeriksaan = listPemeriksaan;
	}

	public List<SupplyModel> getSupplyList() {
		return supplyList;
	}

	public void setSupplyList(List<SupplyModel> supplyList) {
		this.supplyList = supplyList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

//	public List<PemeriksaanModel> getListPemeriksaan() {
//		return listPemeriksaan;
//	}

//	public void setListPemeriksaan(List<PemeriksaanModel> listPemeriksaan) {
//		this.listPemeriksaan = listPemeriksaan;
//	}
	
	
	
	
}
