package com.example.demo.controller;

import com.example.demo.dao.CityDao;
import com.example.demo.javabean.CityBean;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {

	private String ak = "lV1RAEFs5jYIzdnBpAzQSkcSY6WfG8Lw";
	private static List<String> query = new ArrayList<String>();

	static{

		query.add("斜视弱视训练");
		query.add("眼科医院");

		query.add("好视立");
		query.add("康目视光");
		query.add("仁同慧光");
		query.add("目光之城");
		query.add("优倍视•视光中心");
		query.add("达人视界视光中心 ");
		query.add("爱瞳世家");
		query.add("易之快客");
		query.add("易视康");
		query.add("嘉尔视光");
		query.add("倍视力");
		query.add("好视清");
		query.add("眼悦降度眼视光");
		query.add("易视顿");
		query.add("华锐视光");
		query.add("护目亭视光");
		query.add("明润视光");
		query.add("美丽岛视光");
		query.add("伊博视光");
		query.add("永康视光");
		query.add("杰视爱儿康健眼科视光");
		query.add("权科");
		query.add("喜瞳视界");
		query.add("目雨亮瞳光学");
		query.add("秒视明");
		query.add("科大视力");
		query.add("明眸视力养护");
		query.add("艾草芳华视力养护");
		query.add("中易益康");
		query.add("瞳康");
		query.add("凌视");
		query.add("慧安明");
		query.add("眼康世家");
		query.add("眼一生");
		query.add("永康");
		query.add("亮一生");
		query.add("亿视康");
		query.add("古法明眸");
		query.add("维视力");
		query.add("亿视宁");
	}

	@Autowired
	private CityDao cityDao;


	@RequestMapping("/getDb")
	public String getDB(List<String> cityList){

		List<CityBean>list=cityDao.selectAll((String[]) cityList.toArray());

		System.out.println("list : " + list.size());
		return null;
	}

/*	public static void main(String[] args){

//		SearchController cc = new SearchController();
//
//		List<JSONObject> dataList = cc.search("中原区");
		// http://api.map.baidu.com/place/v2/search?query=ATM机&tag=银行&region=北京&output=json&ak=您的ak
		String url = "https://restapi.amap.com/v3/config/district?keywords=北京&subdistrict=2&key=7be17ed637a62ed507c187adf7608f6e";
		List<JSONObject> dataList = new ArrayList<JSONObject>();

		String dataStr = loadJSON(url);

	}*/

	public List<JSONObject> search(String region, String query){

		System.out.println("parameter : " + region);
		// http://api.map.baidu.com/place/v2/search?query=ATM机&tag=银行&region=北京&output=json&ak=您的ak
		String url = "http://api.map.baidu.com/place/v2/search?query=" + query +  "&region=" + region + "&ak=" +
				ak + "&output=json&city_limit=true&page_size=20" ;
		List<JSONObject> dataList = new ArrayList<JSONObject>();

		String dataStr = loadJSON(url);

		if(dataStr == null || "".equals(dataStr)){
			return dataList;
		}

		JSONObject jsonData = JSONObject.fromObject(dataStr);
		Integer total =  (Integer) jsonData.get("total");
		List<JSONObject> resultList =  (List<JSONObject>) jsonData.get("results");

		System.out.println("    total: " + total);

		if(resultList == null || resultList.size() == 0){
			return dataList;
		}
		dataList.addAll(resultList);
		if(total != 0 && total > resultList.size()){
			for(int i=1; i * 20 < total + 20 ; i++ ){
				String urlPage = url + "&page_num=" + i;
				String str = loadJSON(urlPage);
				JSONObject json = JSONObject.fromObject(str);
				List<JSONObject> rr =  (List<JSONObject>) json.get("results");
				if(rr == null || rr.size() == 0){
					return dataList;
				}
				dataList.addAll(rr);
			}
		}
		return dataList;
	}

	@RequestMapping("/search")
	public String search(@RequestBody List<String> cityList) throws IOException {

		List<CityBean>list=cityDao.selectAll((String[]) cityList.toArray());
		String path = "D:\\cityName";

		for(CityBean city : list){

			System.out.println(city.getProvice_name() + "  " + city.getCity_name() + "   " + city.getCounty_name());

			File file = new File(path + "\\" + city.getProvice_name() + ".txt");
			if(!file.exists()){
				file.createNewFile();
			}
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));

			StringBuffer buff = new StringBuffer();
			buff.append(city.getProvice_name());
			buff.append("  ");
			buff.append(city.getCity_name());
			buff.append("  ");
			buff.append(city.getCounty_name());
			buff.append(System.getProperty("line.separator"));
			String cityName=city.getCounty_name();

			for (String qq : query) {
				buff.append("  " + qq);
				buff.append(System.getProperty("line.separator"));

				List<JSONObject> dataList = search(cityName, qq);
				if (dataList == null || dataList.size() == 0) {
					continue;
				}

				for (JSONObject ob : dataList) {
					buff.append("    名称：");
					buff.append(ob.get("name"));
					buff.append("    地址：");
					buff.append(ob.get("address"));
					buff.append("    电话：");
					buff.append(ob.get("telephone"));
					buff.append(System.getProperty("line.separator"));
				}
				writer.write(buff.toString());
				writer.flush();
				buff = new StringBuffer();
			}
			writer.close();

		}

		return "success";
	}


	public static String loadJSON(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL oracle = new URL(url);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {} catch (IOException e) {}
		return json.toString();
	}
}
