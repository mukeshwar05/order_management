package com.assignment.order_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.order_management.dto.DataPojo;
import com.assignment.order_management.dto.ResponsePojo;
import com.assignment.order_management.service.OrderManagementServices;
import com.assignment.order_management.utils.Constants;
import com.assignment.order_management.utils.ResponseMessage;

/**
 * @author mukeshwar.s
 */
@RestController
@RequestMapping("/api")
public class Controller {

	@Autowired
	OrderManagementServices services;

	@PostMapping
	public ResponseEntity<?> crud(@RequestBody DataPojo dataPojo) {

		if (dataPojo.getName().equals(Constants.CREATE_ORDER_API)) {
			double discountValue = services.checkDiscount(dataPojo);
			if (discountValue != -1) {
				dataPojo.getArgs().add(Integer.toString((int) Math.round(discountValue)));
			} else {
				ResponsePojo responsePojo = new ResponsePojo();
				responsePojo.setStatus(false);
				responsePojo.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				responsePojo.setMessage(ResponseMessage.somethingWentWrong);
				responsePojo.setData(null);
				return new ResponseEntity<>(responsePojo, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(services.yamlService(dataPojo), HttpStatus.OK);
	}
}
