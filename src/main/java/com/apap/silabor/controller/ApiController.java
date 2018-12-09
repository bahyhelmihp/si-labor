package com.apap.silabor.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apap.silabor.model.PemeriksaanModel;
import com.apap.silabor.rest.BaseResponse;
import com.apap.silabor.service.PemeriksaanService;

@RestController
public class ApiController {
	
	@Autowired
	private PemeriksaanService pemeriksaanService;
	
	//FITUR 8
    @PostMapping(value = "/api/lab/pemeriksaan/permintaan")
    public BaseResponse<PemeriksaanModel> addLabResult(@RequestBody @Valid PemeriksaanModel pemeriksaan, BindingResult bindingResult) {
        BaseResponse<PemeriksaanModel> response = new BaseResponse<PemeriksaanModel>();
        pemeriksaanService.addPemeriksaan(pemeriksaan);
        response.setStatus(200);
        response.setMessage("success");
        response.setResult(pemeriksaan);
        return response;
    }
	
}