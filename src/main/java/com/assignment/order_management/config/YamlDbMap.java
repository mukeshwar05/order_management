package com.assignment.order_management.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.order_management.dto.DbPojo;

/**
 * @author mukeshwar.s
 */

@Component
public class YamlDbMap {

	@Autowired
	YamlDbConfig yamlDbConfig;

	public DbPojo getDataSourceHashMap(String tenentId) {
		DbPojo dbMap = new DbPojo();
		Map<String, Map<String, String>> dataSourceMap = yamlDbConfig.getDatasources();
		for (String tenentIds : dataSourceMap.keySet()) {
			if (tenentIds.equals(tenentId)) {
				Map<String, String> tenentMap = dataSourceMap.get(tenentId);
				dbMap.setDriverClass(tenentMap.get("driverClassName"));
				dbMap.setUrl(tenentMap.get("jdbcUrl"));
				dbMap.setUserName(tenentMap.get("username"));
				dbMap.setPassword(tenentMap.get("password"));
			}
		}
		return dbMap;
	}
}