package com.assignment.order_management.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author mukeshwar.s
 */

@Configuration
@ConfigurationProperties(prefix = "tenents")
@PropertySource(value = "classpath:db.yaml", factory = YamlPropertySourceFactory.class)
public class YamlDbConfig {

	private Map<String, Map<String, String>> datasources;

	public Map<String, Map<String, String>> getDatasources() {
		return datasources;
	}

	public void setDatasources(Map<String, Map<String, String>> datasources) {
		this.datasources = datasources;
	}

	@Override
	public String toString() {
		return "TenentConfig [datasources=" + datasources + "]";
	}

}
