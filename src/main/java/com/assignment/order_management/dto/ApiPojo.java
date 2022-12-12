package com.assignment.order_management.dto;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class ApiPojo {
	private String type;
	private String query;
	private ArrayList<String> Args = new ArrayList<String>();
	private int id;
	private int applicationId;
	private String createdBy;
	private String createdOn;
	private String description;
	private int isActive;
	private int isDeleted;
	private String name;
	private String updatedBy;
	private String updatedOn;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public ArrayList<String> getArgs() {
		return Args;
	}
	public void setArgs(ArrayList<String> args) {
		this.Args = args;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
	@Override
	public String toString() {
		return "ApiPojo [type=" + type + ", query=" + query + ", Args=" + Args + "]";
	}

}
