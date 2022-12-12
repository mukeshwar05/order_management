package com.assignment.order_management.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.order_management.dto.ApiPojo;
import com.assignment.order_management.dto.DbPojo;
import com.assignment.order_management.utils.Constants;

/**
 * @author mukeshwar.s
 */

@Component
public class MappingValidation {

	@Autowired
	ApiPojo apiMap;

	@Autowired
	DbPojo dbMap;

	public boolean requestMappingValidation(ArrayList<String> requestArr, String requestType) {
		if (dataTypeValidation(requestArr, apiMap.getArgs()) && apiMap.getType().equals(requestType))
			return true;
		return false;
	}

	public StringBuffer apiQueryMapping(String apiQuery, ArrayList<String> requestArr) {
		List<String> apiArr = apiMap.getArgs();
		StringBuffer query = new StringBuffer();

		ArrayList<String> requestArg = requestArr;
		int j = 0, index = 0;
		for (int i = 0; i < apiQuery.length(); i++) {
			if (apiQuery.charAt(i) == '?') {
				query.insert(index, "'");
				index += 1;
				if (apiArr.get(j).equals(Constants.DATATYPE_TIMESTAMP)) {
					Timestamp timestampValue = Timestamp.valueOf(requestArr.get(j));
					query.insert(index, timestampValue);
					index = index + requestArg.get(j).length() + 2;
				} else {
					query.insert(index, requestArg.get(j));
					index = index + requestArg.get(j).length();
				}
				query.insert(index, "'");
				index += 1;
				j += 1;
			} else {
				query.insert(index, apiQuery.charAt(i));
				index += 1;
			}
		}
		apiMap.setQuery(query.toString());
		return query;
	}

	public boolean dataTypeValidation(ArrayList<String> requestArr, List<String> apiArr) {
		if (apiArr.get(0).equals("") && requestArr.size() == 0 && apiArr.size() == 1) {
			return true;
		}
		if (requestArr.size() == apiArr.size()) {
			List<String> dataTypeArr = new ArrayList<String>();
			for (int i = 0; i < requestArr.size(); i++) {
				boolean check = (requestArr.get(i)).matches("-?\\d+");
				if (check)
					dataTypeArr.add(Constants.DATATYPE_INTEGER);
				else
					dataTypeArr.add(Constants.DATATYPE_STRING);
			}
			for (int j = 0; j < apiArr.size(); j++) {
				if (apiArr.get(j).equals(dataTypeArr.get(j)) || apiArr.get(j).equals(Constants.DATATYPE_TIMESTAMP))
					continue;
				else
					return false;
			}
			return true;
		}
		return false;
	}
}
