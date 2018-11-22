package com.apap.silabor.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pemeriksaan")
public class PemeriksaanModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@NotNull
	@Column(name = "tanggal_pengajuan", nullable = false)
	private Date tanggalPengajuan;
	
	@NotNull
	@Column(name = "tanggal_pemeriksaan", nullable = false)
	private Date tanggalPemeriksaan;
	
	@NotNull
	@Column(name = "status", nullable = false)
	private int status;
	
	@NotNull
	@Column(name = "id_pasien", nullable = false)
	private long idPasien;
	
	@NotNull
	@Size(max=255)
	@Column(name= "hasil", nullable = true)
	private String hasil;
	
	@OneToMany(mappedBy = "pemeriksaan", fetch = FetchType.LAZY)
	private List<SupplyModel> listSupply = new ArrayList<SupplyModel>();
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_jenis", referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private JenisPemeriksaanModel jenisPemeriksaan;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_jadwal", referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private JadwalJagaModel jadwalJaga;
	
	public long getIdPasien() {
		return idPasien;
	}

	public void setIdPasien(long idPasien) {
		this.idPasien = idPasien;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getTanggalPengajuan() {
		return tanggalPengajuan;
	}

	public void setTanggalPengajuan(Date tanggalPengajuan) {
		this.tanggalPengajuan = tanggalPengajuan;
	}

	public Date getTanggalPemeriksaan() {
		return tanggalPemeriksaan;
	}

	public void setTanggalPemeriksaan(Date tanggalPemeriksaan) {
		this.tanggalPemeriksaan = tanggalPemeriksaan;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getHasil() {
		return hasil;
	}

	public void setHasil(String hasil) {
		this.hasil = hasil;
	}

	public JenisPemeriksaanModel getJenisPemeriksaan() {
		return jenisPemeriksaan;
	}

	public void setJenisPemeriksaan(JenisPemeriksaanModel jenisPemeriksaan) {
		this.jenisPemeriksaan = jenisPemeriksaan;
	}
}


