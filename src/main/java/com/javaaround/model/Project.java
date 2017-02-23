package com.javaaround.model;
import javax.persistence.Entity;  
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import lombok.Data;
import java.util.List;

@Entity
@Data 
public class Project { 
	@Id 
	@GeneratedValue
	private int id;
	private String name;
	@ManyToMany(mappedBy="projects")
    private List<Employee> employees;
}	