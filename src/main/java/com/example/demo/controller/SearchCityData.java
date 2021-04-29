package com.example.demo.controller;

import com.example.demo.javabean.CityBean;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchCityData {

	@Autowired
	private CityService cityService;

	@RequestMapping("/getAllData")
	public List<CityBean> getAllData(){

		return cityService.searchAllById("1");
	}

	@RequestMapping("/getAllDataByArray")
	public List<CityBean> getAllDataByArray(){

		return cityService.searchAllByArray(new String[]{"1"});
	}
}
