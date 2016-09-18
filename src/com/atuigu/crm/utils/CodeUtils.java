package com.atuigu.crm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CodeUtils {

	public static String convertMapToStr(Map<String, Object> params,
			String prefix) {
		StringBuilder str  = new StringBuilder();
		Set<Entry<String,Object>> entrySet = params.entrySet();
		if(params!=null && params.size()>0){
			for (Entry<String, Object> entry : entrySet) {
				Object val = entry.getValue();
				if(val==null || val.toString().trim().equals("")){
					continue;
				}
				String key = entry.getKey();
				str.append("&")
					.append(prefix)
					.append(key)
					.append("=")
					.append(val);
				
			}
		}
		
		return str.toString();
	}

	public static Map<String, Object> convertToQueryMap(
			Map<String, Object> params) throws ParseException {
		Map<String,Object> newMap = new HashMap<>();
		Set<Entry<String,Object>> entrySet = params.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			Object val = entry.getValue();
			if(val == null || val.toString().trim().equals("")){
				continue;
			}
			String key = entry.getKey();
			String[] split = key.split("_");
			String name = key;
			if(split.length>1){
				name=split[1];
				if("D".equals(split[0])){
					val=new SimpleDateFormat("yyyy-MM-dd").parseObject(val.toString());
				}else if("LIKE".equals(split[0])){
					val="%"+val+"%";
				}
			}
			newMap.put(name, val);
		}

			
		return newMap;
	}

	

}
