package com.javaaround.model;
import javax.persistence.Embeddable;  
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Id;
import javax.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor 
public class EmployeeDetails { 
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)  
	private int id;
	private String street;
	private String city;
	@Column(name="post_code")
	private String postcode;
	private String fatherName;
/*
	@OneToOne(mappedBy="empDetails",cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
    private Employee emp;*/

}	