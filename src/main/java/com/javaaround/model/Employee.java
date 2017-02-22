package com.javaaround.model;
import javax.persistence.Entity;  
import javax.persistence.EntityListeners;  
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
import javax.persistence.Embedded;
import javax.persistence.AttributeOverrides;
import javax.persistence.AttributeOverride;
import com.javaaround.listener.EmployeeListener;
import java.time.LocalDate;
import com.javaaround.converter.BooleanConverter;
import javax.persistence.Convert;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import java.util.*;  
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;


@Entity 
//@Inheritance(strategy = InheritanceType.JOINED)
@Data 
public class Employee { 
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)  
	private int id;
	@Basic(optional=false)  
	private String firstName;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "department_id")
	private Department department;
	
}	