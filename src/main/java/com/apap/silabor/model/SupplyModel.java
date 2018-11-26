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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "supply")
public class SupplyModel implements Serializable{
	// id, nama varchar 255, jenis v255, jumlah int, deskripsi v255,
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	//nip varchar255 notnull unique
	@NotNull
	@Size(max=255)
	@Column(name= "jenis", nullable = false)
	private String jenis;
	
	@NotNull
	@Size(max=255)
	@Column(name= "nama", nullable = false)
	private String nama;
	
	@NotNull
	@Size(max=255)
	@Column(name= "deskripsi", nullable = false)
	private String deskripsi;
	
	@NotNull
	@Column(name= "jumlah", nullable = false)
	private Integer jumlah;
	
	@OneToMany(mappedBy = "supply", fetch = FetchType.LAZY)
	private List<KebutuhanReagenModel> listKebutuhanReagen = new ArrayList<KebutuhanReagenModel>();
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "jenisPemeriksaan_supply",
            joinColumns = { @JoinColumn(name = "id_supply") },
            inverseJoinColumns = { @JoinColumn(name = "id_jenisPemeriksaan") })
    private List<JenisPemeriksaanModel> jenisPemeriksaanList;
	
	public List<JenisPemeriksaanModel> getJenisPemeriksaanList() {
		return jenisPemeriksaanList;
	}

	public void setJenisPemeriksaanList(List<JenisPemeriksaanModel> jenisPemeriksaanList) {
		this.jenisPemeriksaanList = jenisPemeriksaanList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJenis() {
		return jenis;
	}

	public void setJenis(String jenis) {
		this.jenis = jenis;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public Integer getJumlah() {
		return jumlah;
	}

	public void setJumlah(Integer jumlah) {
		this.jumlah = jumlah;
	}

	public List<KebutuhanReagenModel> getListKebutuhanReagen() {
		return listKebutuhanReagen;
	}

	public void setListKebutuhanReagen(List<KebutuhanReagenModel> listKebutuhanReagen) {
		this.listKebutuhanReagen = listKebutuhanReagen;
	}

	
	
}
