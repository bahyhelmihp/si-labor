package com.apap.silabor.service;

import com.apap.silabor.model.KebutuhanReagenModel;
import com.apap.silabor.repository.KebutuhanReagenDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * KebutuhanReagenServiceImpl
 */
@Service
@Transactional
public class KebutuhanReagenServiceImpl implements KebutuhanReagenService{
	@Autowired
	private KebutuhanReagenDb kebutuhanDb;
	
	@Override
	public KebutuhanReagenModel addKebutuhan(KebutuhanReagenModel kebutuhan) {
		return kebutuhanDb.save(kebutuhan);
	}
}
