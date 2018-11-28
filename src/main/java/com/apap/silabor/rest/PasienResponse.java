package com.apap.silabor.rest;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PasienResponse {
	private String status;

	private String message;

	private List<PasienTest> result;

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

	public List<PasienTest> getResult() {
		return result;
	}

	public void setResult(List<PasienTest> result) {
		this.result = result;
	}





	



	
	
}