package com.example.demo.service;

import com.example.demo.javabean.CityBean;

import java.util.List;

public interface CityService {

	public List<CityBean> searchAllById(String id);

	public List<CityBean> searchAllByArray(String[] id);
}
