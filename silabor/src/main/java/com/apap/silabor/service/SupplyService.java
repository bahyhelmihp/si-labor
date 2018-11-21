package com.apap.silabor.service;

import java.util.List;

import com.apap.silabor.model.SupplyModel;

public interface SupplyService {

	SupplyModel addSupply(SupplyModel supply);

	List<SupplyModel> getListSupply();

	SupplyModel getSupplyById(long id);

	SupplyModel updateSupply(SupplyModel supply);

}