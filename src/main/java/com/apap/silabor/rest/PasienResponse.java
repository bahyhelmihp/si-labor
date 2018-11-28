package com.apap.silabor.rest;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PasienResponse {
	private String status;

	private String message;

	private Map<String,PasienTest> result;

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

	public Map<String, PasienTest> getResult() {
		return result;
	}

	public void setResult(Map<String, PasienTest> result) {
		this.result = result;
	}






	



	
	
}