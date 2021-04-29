package com.example.demo.service.impl;

import com.example.demo.dao.CityMapper;
import com.example.demo.javabean.CityBean;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	public CityMapper cityMapper;

	@Override
	public List<CityBean> searchAllById(String id) {
		return cityMapper.searchAllById(id);
	}

	@Override
	public List<CityBean> searchAllByArray(String[] id) {
		return cityMapper.searchAllByArray(id);
	}
}
