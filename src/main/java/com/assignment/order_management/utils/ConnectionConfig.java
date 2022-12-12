package com.assignment.order_management.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.assignment.order_management.config.YamlDbMap;
import com.assignment.order_management.dto.DbPojo;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author mukeshwar.s
 */

@Component
public class ConnectionConfig {

	@Autowired
	Environment env;

	@Autowired
	YamlDbMap yamlDbMap;

	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;

	@Bean
	public void configDataSource() {
		DbPojo dbMap = yamlDbMap.getDataSourceHashMap(env.getProperty(Constants.TENANT_ID));

		config.setJdbcUrl(dbMap.getUrl());
		config.setDriverClassName(dbMap.getDriverClass());
		config.setUsername(dbMap.getUserName());
		config.setPassword(dbMap.getPassword());
		config.addDataSourceProperty("cachePrepStmts", Constants.CACHEPREPSTMTS);
		config.addDataSourceProperty("prepStmtCacheSize", Constants.PREPSTMTCACHESIZE);
		config.addDataSourceProperty("prepStmtCacheSqlLimit", Constants.PREPSTMTCACHESQLLIMIT);
		config.setMaximumPoolSize(Constants.MAXPOOLSIZE);
		ds = new HikariDataSource(config);
	}

	public static Connection config() throws SQLException {
		return ds.getConnection();
	}

	public ConnectionConfig() {
	}

}
