package com.javaaround.model;
import javax.persistence.Entity;  
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Basic; 
import javax.persistence.FetchType;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.TemporalType;
import javax.persistence.Column;
import lombok.Data;
import com.javaaround.util.Gender;
import javax.persistence.Lob;

@Entity
@Data 
public class Employee { 
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)  
	private int id;
	@Basic(optional=false)  
	private String firstName;
	private Double salary; 
	@Temporal(TemporalType.DATE) 
	private Date joinDate;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Lob
	@Basic(fetch=FetchType.LAZY)	
	private byte[] picture;
	@Lob
	//private char[] remarks;	
	private String remarks;
	
}	