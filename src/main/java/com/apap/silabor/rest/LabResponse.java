package com.apap.silabor.rest;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LabResponse {
	private String status;

	private String message;

	private LabResult labResult;

	public LabResponse(String status, String message, LabResult labResult) {
		super();
		this.status = status;
		this.message = message;
		this.labResult = labResult;
	}

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

	public LabResult getLabResult() {
		return labResult;
	}

	public void setLabResult(LabResult labResult) {
		this.labResult = labResult;
	}
	
}