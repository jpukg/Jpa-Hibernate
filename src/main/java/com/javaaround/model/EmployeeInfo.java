package com.javaaround.model;
import lombok.Data;

@Data 
public class EmployeeInfo { 
	private int id;
	private String firstName;
	public EmployeeInfo(String firstName,int id){
		this.id = id;
		this.firstName = firstName;
	}
}	