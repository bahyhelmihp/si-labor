package com.apap.silabor.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private int pasien;
	
	@NotNull
	@Size(max=255)
	@Column(name= "hasil", nullable = false)
	private String hasil;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_jenis", referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private JenisPemeriksaanModel jenis_pemeriksaan;
	
	
}


