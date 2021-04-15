package com.example.demo.dao;

import com.example.demo.javabean.CityBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("cityDao")
public class CityDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List selectAll(String[] args){
		String str = String.join(",",args);
		List<CityBean>list=new ArrayList<>();
		String sql="SELECT			" +
				"		p.id,				" +
				"		p.provice_id,		" +
				"		p.provice_name,	" +
				"		c.city_id,			" +
				"		c.city_name,		" +
				"		c2.county_id,			" +
				"		c2.county_name		" +
				"	FROM				" +
				"		j_position_provice p	" +
				"	LEFT JOIN j_position_city c " +
				"		ON p.provice_id = c.province_id	" +
				"	LEFT JOIN j_position_county c2 " +
				"		ON c2.city_id = c.city_id	" +
				"   where p.orderSort in ( " + str + " ) " +
				"   order by  " +
				"		p.orderSort";
		list=jdbcTemplate.query(sql,new BeanPropertyRowMapper<CityBean>(CityBean.class));
		return list;
	}

}
