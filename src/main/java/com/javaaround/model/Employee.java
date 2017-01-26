package com.javaaround.model;
import javax.persistence.Entity;  
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity 
public class Employee { 
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private int id;  
	private String firstName;
	private Double salary;  
	  
	public int getId() {  
	    return id;  
	}  
	public void setId(int id) {  
	    this.id = id;  
	}  
	public String getFirstName() {  
	    return firstName;  
	}  
	public void setFirstName(String firstName) {  
	    this.firstName = firstName;  
	}  
	public Double getSalary() {  
	    return salary;  
	}  
	public void setSalary(Double salary) {  
	    this.salary = salary;  
	}  
}	