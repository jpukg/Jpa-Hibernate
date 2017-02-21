package com.javaaround.model;
import javax.persistence.Embeddable;  
import javax.persistence.Column;
import lombok.Data;

//@Embeddable
@Data 
public class Address { 
	
	private String street;
	private String city;
	@Column(name="post_code")
	private String postcode;

}	