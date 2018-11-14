package com.apap.silabor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.silabor.model.SupplyModel;
import com.apap.silabor.repository.SupplyDb;

@Service
@Transactional
public class SupplyServiceImpl implements SupplyService {
	@Autowired
	private SupplyDb supplyDb;

	@Override
	public SupplyModel addSupply(SupplyModel supply) {
		// TODO Auto-generated method stub
		return supplyDb.save(supply);
	}

	@Override
	public List<SupplyModel> getListSupply() {
		// TODO Auto-generated method stub
		return supplyDb.findAll();
	}

	@Override
	public SupplyModel getSupplyById(long id) {
		// TODO Auto-generated method stub
		return supplyDb.getOne(id);
	}

	@Override
	public SupplyModel updateSupply(SupplyModel newsupply) {
		// TODO Auto-generated method stub
		SupplyModel updateSupply = supplyDb.getOne(newsupply.getId());
		updateSupply.setJumlah(newsupply.getJumlah());
		return supplyDb.save(newsupply);
	}

	
	
	

}

