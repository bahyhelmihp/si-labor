package com.apap.silabor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="jenispemeriksaan_labsupplies")
public class JenisPemeriksaanLabSuppliesModel implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch =FetchType.LAZY, optional = false)
	@JoinColumn(name ="id_jenis_pemeriksaan", referencedColumnName = "id")
	@JsonIgnore
	private JenisPemeriksaanModel jenisPemeriksaan;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_lab_supplies", referencedColumnName = "id")
	private SupplyModel labSupplies;
}

