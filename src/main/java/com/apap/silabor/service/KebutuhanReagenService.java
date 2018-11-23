package com.apap.silabor.service;

import java.util.List;

import com.apap.silabor.model.KebutuhanReagenModel;

/**
 * KebutuhanReagenService
 */
public interface KebutuhanReagenService {
	KebutuhanReagenModel addReagen(KebutuhanReagenModel reagen);
	List<KebutuhanReagenModel> getListReagen();
	KebutuhanReagenModel getReagenById(long id);
	KebutuhanReagenModel updateReagen(KebutuhanReagenModel reagen);
}
