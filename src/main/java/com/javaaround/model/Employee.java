package com.javaaround.model;
import javax.persistence.Entity;  
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Basic; 
import javax.persistence.FetchType;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Entity
@Data 
public class Employee { 
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private int id;
	@Basic(fetch=FetchType.LAZY,optional=false)  
	private String firstName;
	private Double salary; 
	@Temporal(TemporalType.DATE) 
	private Date joinDate;
	
}	