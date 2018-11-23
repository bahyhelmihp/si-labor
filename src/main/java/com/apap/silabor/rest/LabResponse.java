package com.apap.silabor.rest;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LabResponse {
	private String status;

	private String message;

	private LabResult result;

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

	public LabResult getResult() {
		return result;
	}

	public void setResult(LabResult result) {
		this.result = result;
	}

	
	
}