package com.javaaround.model;
import javax.persistence.Embeddable;  
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;

@Entity
@Data 
public class EmployeeDetails { 
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)  
	private int id;
	private String street;
	private String city;
	@Column(name="post_code")
	private String postcode;
	private String fatherName;

	@OneToOne
    private Employee emp;

}	