package com.assignment.order_management.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.assignment.order_management.dto.ApiPojo;
import com.assignment.order_management.dto.DataPojo;
import com.assignment.order_management.dto.ResponsePojo;
import com.assignment.order_management.utils.ConnectionConfig;
import com.assignment.order_management.utils.ResponseMessage;

/**
 * @author mukeshwar.s
 */

@Component
public class OrderManagementDao {

	@Autowired
	ConnectionConfig connectionConfig;

	@Autowired
	ApiPojo apiMap;

	private int result;

	public synchronized ResponsePojo readDataBase(DataPojo dataPojo) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ResponsePojo responsePojo = new ResponsePojo();
		try {
			connection = connectionConfig.config();

			statement = connection.createStatement();
			resultSet = statement.executeQuery(apiMap.getQuery());
			JSONArray data = fetchData(dataPojo, resultSet);
			if (!data.isEmpty()) {
				responsePojo.setStatus(true);
				responsePojo.setStatusCode(HttpStatus.OK.value());
				responsePojo.setData(data);
				responsePojo.setMessage(ResponseMessage.dataFound);
			} else {
				responsePojo.setStatus(true);
				responsePojo.setStatusCode(HttpStatus.NOT_FOUND.value());
				responsePojo.setData(null);
				responsePojo.setMessage(ResponseMessage.dataNotFound);
			}
		} catch (Exception e) {
			responsePojo.setStatus(false);
			responsePojo.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			responsePojo.setMessage(e.getMessage());
			responsePojo.setData(null);

		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return responsePojo;
	}

	public ResponsePojo statement() {
		Connection connection = null;
		Statement statement = null;
		ResponsePojo responsePojo = new ResponsePojo();
		try {
			connection = connectionConfig.config();
			statement = connection.createStatement();
			result = statement.executeUpdate(apiMap.getQuery());
			if (result > 0) {
				responsePojo.setStatus(true);
				responsePojo.setStatusCode(HttpStatus.OK.value());
				responsePojo.setData(null);
				responsePojo.setMessage(ResponseMessage.dataManipulated);
			} else {
				responsePojo.setStatus(true);
				responsePojo.setStatusCode(HttpStatus.NOT_FOUND.value());
				responsePojo.setData(null);
				responsePojo.setMessage(ResponseMessage.dataNotFound);
			}
		} catch (Exception e) {
			responsePojo.setStatus(false);
			responsePojo.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			responsePojo.setMessage(e.getMessage());
			responsePojo.setData(null);
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return responsePojo;
	}

	public JSONArray fetchData(DataPojo dataPojo, ResultSet resultSet) throws Exception {
		JSONArray jsonArr = new JSONArray();

		ResultSetMetaData rsmd = resultSet.getMetaData();
		while (resultSet.next()) {
			JSONObject json = new JSONObject();
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				json.put(rsmd.getColumnLabel(i + 1), resultSet.getObject(rsmd.getColumnLabel(i + 1)));
			}
			jsonArr.add(json);
		}

		return jsonArr;
	}

}
