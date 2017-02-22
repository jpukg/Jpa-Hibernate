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
import javax.persistence.ElementCollection;
import javax.persistence.CollectionTable;
import javax.persistence.OneToMany;
import javax.persistence.MapKey ;
import com.javaaround.listener.EmployeeListener;
import java.time.LocalDate;
import com.javaaround.converter.BooleanConverter;
import javax.persistence.Convert;
import java.util.List;
import java.util.Map;

@Entity
@Data 
public class Department { 
	@Id 
	@GeneratedValue
	private int id;
	@Basic(optional=false)  
	private String name;
	@OneToMany(mappedBy = "department")
	@MapKey 
	private Map<Integer, Employee> empMap;
}	