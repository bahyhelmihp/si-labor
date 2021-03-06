package com.apap.silabor.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "jadwal_jaga")
public class JadwalJagaModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Column(name = "tanggal", nullable = false)
	private Date tanggal;

	@NotNull
	@Column(name = "waktu_mulai", nullable = false)
	private Time start;

	@NotNull
	@Column(name = "waktu_selesai", nullable = false)
	private Time end;

	@NotNull
	@Column(name = "staff", nullable = false)
	private String namaStaff;

	@OneToMany(mappedBy = "jadwalJaga", fetch = FetchType.LAZY)
	private List<PemeriksaanModel> listPemeriksaan = new ArrayList<PemeriksaanModel>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public Time getStart() {
		return start;
	}

	public void setStart(Time start) {
		this.start = start;
	}

	public Time getEnd() {
		return end;
	}

	public void setEnd(Time end) {
		this.end = end;
	}

	public String getNamaStaff() {
		return namaStaff;
	}

	public void setNamaStaff(String namaStaff) {
		this.namaStaff = namaStaff;
	}

//	public StaffModel getStaff() {
//		return staff;
//	}
//
//	public void setStaff(StaffModel staff) {
//		this.staff = staff;
//	}

}
