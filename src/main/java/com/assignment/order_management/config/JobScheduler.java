package com.assignment.order_management.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.assignment.order_management.service.OrderManagementServices;

@Configuration
public class JobScheduler {

	@Autowired
	OrderManagementServices service;

	@Scheduled(cron = "${sendEmail}")
	public void sendEmail() {
		List<Integer> userIdList = service.getUserIdList();
		if (userIdList != null) {
			for (int i = 0; i < userIdList.size(); i++) {
				int userCountById = service.userCount(Integer.toString(userIdList.get(i)));
				if (userCountById == 9 || userCountById == 19) {
					System.out.println("Buy 1 more to get Promoted user : " + userIdList.get(i));
				}
			}
		}
	}

}