package com.apap.silabor.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KamarPasienIsiResponse {
	private String status;

	private String message;

	private List<KamarPasienIsi> result;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<KamarPasienIsi> getResult() {
		return result;
	}

	public void setResult(List<KamarPasienIsi> result) {
		this.result = result;
	}



}
