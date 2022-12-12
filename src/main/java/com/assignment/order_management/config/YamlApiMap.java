package com.assignment.order_management.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.order_management.dto.ApiPojo;

/**
 * @author mukeshwar.s
 */

@Component
public class YamlApiMap {

	@Autowired
	YamlApiConfig yamlApiConfig;

	@Autowired
	ApiPojo apiMap;

	public boolean apiMapping(String var) {
		List<Map<String, Object>> datasources = yamlApiConfig.getOrder();
		int index = -1;
		for (int i = 0; i < datasources.size(); i++) {
			if (datasources.get(i).containsValue(var))
				index = i;
		}
		if (index != -1) {
			apiMap.setType((String) datasources.get(index).get("type"));
			apiMap.setQuery((String) datasources.get(index).get("query"));
			String[] elem = ((String) datasources.get(index).get("paramType")).split(",");
			List<String> obj2 = Arrays.asList(elem);
			ArrayList<String> strArr = new ArrayList<String>(obj2);
			apiMap.setArgs(strArr);
			return true;
		}
		return false;
	}
}
