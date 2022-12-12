package com.assignment.order_management.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.assignment.order_management.config.YamlApiMap;
import com.assignment.order_management.dao.OrderManagementDao;
import com.assignment.order_management.dto.ApiPojo;
import com.assignment.order_management.dto.DataPojo;
import com.assignment.order_management.dto.ResponsePojo;
import com.assignment.order_management.utils.Constants;
import com.assignment.order_management.utils.ResponseMessage;

/**
 * @author mukeshwar.s
 */
@Component
public class OrderManagementServices {

	@Autowired
	OrderManagementDao dao;

	@Autowired
	YamlApiMap yamlApiMap;

	@Autowired
	MappingValidation mappingValidation;

	@Autowired
	ApiPojo apiMap;

	public ResponsePojo yamlService(DataPojo dataPojo) {
		ResponsePojo responsePojo = new ResponsePojo();

		boolean taskValidate = yamlApiMap.apiMapping(dataPojo.getName());
		if (taskValidate && mappingValidation.requestMappingValidation(dataPojo.getArgs(), dataPojo.getType())) {
			String type = apiMap.getType();
			switch (type) {
			case Constants.TYPE_QUERY: {
				mappingValidation.apiQueryMapping(apiMap.getQuery(), dataPojo.getArgs());
				return dao.readDataBase(dataPojo);
			}
			case Constants.TYPE_STATEMENT: {
				mappingValidation.apiQueryMapping(apiMap.getQuery(), dataPojo.getArgs());
				return dao.statement();
			}
			default: {
				responsePojo.setStatus(true);
				responsePojo.setStatusCode(HttpStatus.NOT_FOUND.value());
				responsePojo.setData(null);
				responsePojo.setMessage(ResponseMessage.noDataDefinition);
			}
			}
		} else {
			responsePojo.setStatus(true);
			responsePojo.setStatusCode(HttpStatus.OK.value());
			responsePojo.setData(null);
			responsePojo.setMessage(ResponseMessage.wrongInput);
			return responsePojo;
		}
		return responsePojo;

	}

	public int userCount(String userId) {
		int orderCount = -1;
		DataPojo fetchOrderCount = new DataPojo();

		fetchOrderCount.setName(Constants.READ_USER_COUNT_API);
		fetchOrderCount.setType(Constants.TYPE_QUERY);
		ArrayList<String> argsFetchOrderCount = new ArrayList<String>();
		argsFetchOrderCount.add(userId);

		fetchOrderCount.setArgs(argsFetchOrderCount);
		ResponsePojo orderCountData = yamlService(fetchOrderCount);
		if (orderCountData.getStatusCode() == 200 && orderCountData.getData() != null) {
			JSONArray respJsonArray = (JSONArray) orderCountData.getData();
			for (int j = 0; j < respJsonArray.size(); j++) {
				JSONObject respJsonObject = (JSONObject) respJsonArray.get(j);
				orderCount = Integer.parseInt(respJsonObject.get(Constants.COLUMN_USER_COUNT).toString());
			}
			return orderCount;
		} else {
			return -1;
		}
	}

	// To check the discount for the order placed by User
	public double checkDiscount(DataPojo dataPojo) {
		int orderCount = userCount(dataPojo.getArgs().get(0));
		if (orderCount != -1) {
			if (orderCount >= 20) {
				// Platinum
				return (Double.parseDouble(dataPojo.getArgs().get(1)) * 0.2);
			} else if (orderCount >= 10) {
				// Gold
				return (Double.parseDouble(dataPojo.getArgs().get(1)) * 0.1);
			} else {
				// Regular
				return 0;
			}
		} else {
			return -1;
		}
	}

	public List<Integer> getUserIdList() {
		List<Integer> userIdList = new ArrayList<Integer>();
		DataPojo fetchUserIdList = new DataPojo();

		fetchUserIdList.setName(Constants.READ_USER_ID_API);
		fetchUserIdList.setType(Constants.TYPE_QUERY);
		ArrayList<String> argsFetchUserIdList = new ArrayList<String>();

		fetchUserIdList.setArgs(argsFetchUserIdList);
		ResponsePojo userIdData = yamlService(fetchUserIdList);
		if (userIdData.getStatusCode() == 200 && userIdData.getData() != null) {
			JSONArray respJsonArray = (JSONArray) userIdData.getData();
			for (int j = 0; j < respJsonArray.size(); j++) {
				JSONObject respJsonObject = (JSONObject) respJsonArray.get(j);
				userIdList.add(Integer.parseInt(respJsonObject.get(Constants.COLUMN_USER_ID).toString()));
			}
			return userIdList;
		} else {
			return null;
		}
	}
}
