package com.example.demo.javabean;

public class CityBean {
	private Long id;
	private String provice_id;
	private String provice_name;
	private String city_id;
	private String city_name;
	private String county_id;
	private String county_name;

	public String getCounty_id() {
		return county_id;
	}

	public void setCounty_id(String county_id) {
		this.county_id = county_id;
	}

	public String getCounty_name() {
		return county_name;
	}

	public void setCounty_name(String county_name) {
		this.county_name = county_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvice_id() {
		return provice_id;
	}

	public void setProvice_id(String provice_id) {
		this.provice_id = provice_id;
	}

	public String getProvice_name() {
		return provice_name;
	}

	public void setProvice_name(String provice_name) {
		this.provice_name = provice_name;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
}
