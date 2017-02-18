package com.javaaround.model;
import javax.persistence.Embeddable;  
import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PostPersist;
import lombok.Data;

@Embeddable
@Data 
public class Address { 
	
	private String street;
	private String city;
	@Column(name="post_code")
	private String postcode;

	@PrePersist
	public void beforeSave(){
		System.out.println("before save...");
	}

	@PostPersist
	public void afterSave(){
		System.out.println(" after save...");
	}

}	