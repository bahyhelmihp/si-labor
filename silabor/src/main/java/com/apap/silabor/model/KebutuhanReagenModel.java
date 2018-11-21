package com.apap.silabor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

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

/*
 *  KebutuhanReaganModel
 */
@Entity
@Table(name = "kebutuhan_reagen")
public class KebutuhanReagenModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_reagen", referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private SupplyModel supply;
	
	@NotNull
	@Column(name = "tanggal_update", nullable = false)
	private Date tanggal_update;
	
	@NotNull
	@Column(name = "jumlah", nullable = false)
	private int jumlah;
	
	@NotNull
	@Column(name = "status", nullable = false)
	private int status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SupplyModel getSupply() {
		return supply;
	}

	public void setSupply(SupplyModel supply) {
		this.supply = supply;
	}

	public Date getTanggal_update() {
		return tanggal_update;
	}

	public void setTanggal_update(Date tanggal_update) {
		this.tanggal_update = tanggal_update;
	}

	public int getJumlah() {
		return jumlah;
	}

	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
