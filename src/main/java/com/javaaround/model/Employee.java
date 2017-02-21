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
import javax.persistence.CollectionTable;
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
	@ElementCollection
	@CollectionTable(name="Contacts", joinColumns=@JoinColumn(name="id"))
	@Column(name="CONTACT_LIST")
	private List<String> contacts;
	/*//private Double salary; 
	@Temporal(TemporalType.DATE) 
	private Date joinDate;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Lob
	@Basic(fetch=FetchType.LAZY)	
	private byte[] picture;
	@Lob
	//private char[] remarks;	
	private String remarks;*/

	/*@Embedded
	//street = field name
	@AttributeOverrides({
		@AttributeOverride(name="street",column=@Column(name="home_street")),
		@AttributeOverride(name="city",column=@Column(name="home_city")),
		@AttributeOverride(name="postcode",column=@Column(name="home_post_code"))
	})
	private Address homeAddress;

	@Embedded
	private Address officeAddress;

	@Convert(converter=BooleanConverter.class)
	private Boolean isActive;
	private LocalDate createDate;

	/*@OneToMany
	private List<Department> departments = new ArrayList<Department>();
	@ElementCollection
	@JoinTable(name="emp_address",joinColumns = @JoinColumn(name="emp_id"))
	@GenericGenerator(name = "hilo-gen",strategy = "hilo")
	@CollectionId(
		columns = { @Column(name="address_id")},
		generator = "hilo-gen",
		type = @Type(type="long")
	)
	private List<Address> address = new ArrayList<Address>();*/

}	