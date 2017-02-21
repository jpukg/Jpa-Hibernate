package com.javaaround.model;
import javax.persistence.Entity; 
import javax.persistence.Column; 
import javax.persistence.DiscriminatorValue; 
import javax.persistence.AttributeOverride; 
import lombok.Data; 
  
@Entity
@AttributeOverride( name="firstName", column = @Column(name="first_name") )
@DiscriminatorValue("dckilemp")  
@Data 
public class RegularEmployee extends Employee{  
    private Double salary;  
    private int bonus;  
   
}  