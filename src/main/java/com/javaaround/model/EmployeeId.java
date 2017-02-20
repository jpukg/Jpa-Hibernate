package com.javaaround.model;
import java.io.Serializable;
import javax.persistence.Embeddable;  
import lombok.Data;

@Embeddable
@Data 
public class EmployeeId implements Serializable{ 
	private int id;
	private String deptName;
	
}	