package com.apap.silabor.service;

import com.apap.silabor.model.KebutuhanReagenModel;
import com.apap.silabor.repository.KebutuhanReagenDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * KebutuhanReagenServiceImpl
 */
@Service
@Transactional
public class KebutuhanReagenServiceImpl implements KebutuhanReagenService{
	@Autowired
	private KebutuhanReagenDb reagenDb;
	
	@Override
	public KebutuhanReagenModel addReagen(KebutuhanReagenModel reagen) {
		return reagenDb.save(reagen);
	}
	
	@Override
	public List<KebutuhanReagenModel> getListReagen(){
		return reagenDb.findAll();
	}
}
