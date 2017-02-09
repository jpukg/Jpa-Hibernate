package com.javaaround.model;
import javax.persistence.Entity;  
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Basic; 
import lombok.Data;

@Entity
@Data 
public class Employee { 
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private int id;
	//@Basic(fetch=FetchType.LAZY,optional=false)  
	private String firstName;
	private Double salary;  
	
}	