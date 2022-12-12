package com.assignment.order_management.dto;

import java.util.ArrayList;



public class DataPojo {
	
	private String name;
	private String type;
	private ArrayList<String> Args = new ArrayList<String>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public ArrayList<String> getArgs() {
		return Args;
	}
	public void setArgs(ArrayList<String> args) {
		Args = args;
	}
	@Override
	public String toString() {
		return "DataPojo [name=" + name + ", type=" + type + ", Args=" + Args + "]";
	}

	
}
