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
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.*;  
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@NamedQueries({
	@NamedQuery(name="findAllEmployees",query="SELECT e FROM Employee e")
})
@Entity 
@Data 
public class Employee { 
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)  
	private Integer id;
	@Basic(optional=false)  
	private String firstName;

    @OneToOne(cascade=CascadeType.ALL)
    //@JoinColumn(name="empdetails_id")
    private EmployeeDetails empDetails;

    @Version
    @Column(columnDefinition = "integer DEFAULT 0", nullable = false)
    protected int version;
	
}	