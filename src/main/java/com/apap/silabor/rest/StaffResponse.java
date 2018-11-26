package com.apap.silabor.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StaffResponse {
	private String status;

	private String message;

	private List<StaffTest> result;

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

	public List<StaffTest> getResult() {
		return result;
	}

	public void setResult(List<StaffTest> result) {
		this.result = result;
	}
}
