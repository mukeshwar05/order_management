package com.assignment.order_management.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author mukeshwar.s
 */


@Configuration
@ConfigurationProperties(prefix = "api")
@PropertySource(value = "classpath:api.yaml", factory = YamlPropertySourceFactory.class)
public class YamlApiConfig {

	private List<Map<String, Object>> order;

	public List<Map<String, Object>> getOrder() {
		return order;
	}

	public void setOrder(List<Map<String, Object>> order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "YamlApiConfig [order=" + order + "]";
	}

}
