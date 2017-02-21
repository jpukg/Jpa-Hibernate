package com.javaaround.model;
import javax.persistence.Entity; 
import javax.persistence.Column; 
import javax.persistence.AttributeOverride; 
import javax.persistence.DiscriminatorValue; 
import lombok.Data; 
  
@Entity
@AttributeOverride( name="firstName", column = @Column(name="first_name") )
@DiscriminatorValue("outsideemp")  
@Data 
public class ContactEmployee extends Employee{  
    @Column(name="pay_per_hour")  
    private float pay_per_hour;  
      
    @Column(name="contract_duration")  
    private String contract_duration;  
   
}  