package com.apap.silabor.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.silabor.model.JenisPemeriksaanModel;
import com.apap.silabor.repository.JenisPemeriksaanDb;

@Service
@Transactional
public class JenisPemeriksaanServiceImpl implements JenisPemeriksaanService {

	@Autowired 
	private JenisPemeriksaanDb jenisPemeriksaanDb;

	@Override
	public JenisPemeriksaanDb getJenisPemeriksaanDb() {
		// TODO Auto-generated method stub
		return jenisPemeriksaanDb;
	}

	@Override
	public JenisPemeriksaanModel getJenisPemeriksaanById(long id) {
		// TODO Auto-generated method stub
		return jenisPemeriksaanDb.findById(id).get();
	}
	


}
