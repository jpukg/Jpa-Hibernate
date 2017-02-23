### Persistence by java ###
Following solution offer by java to persistence object into RDBMS

1. Jdbc
2. Entity Beans(J2EE)
3. JDO
4. JPA	

### JPA ###
The Java Persistence API (JPA) is a vendor independent specification(classes and interface) for mapping Java objects(POJO) to the tablesof relational databases(ORM = Object relational mapping)

JPA is an open source API, therefore various enterprise vendors such as Oracle, Redhat, Eclipse, etc. provide new products by adding the JPA persistence flavor in them. Some of these products(Persistence Provider) include:

Hibernate, Eclipselink(reference implementation of JPA), Toplink, Spring Data JPA, etc.

JPA specification current version 2.1 (JSR No 338)
 
### Warmup ###

1. create maven java project by following command

	`mvn archetype:generate -DgroupId=com.javaaround -DartifactId=Jpa -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false`

2. Update AppTest.java
	```java
	package com.javaaround;

	import org.junit.Test;;

	
	/**
	 * Unit test for simple App.
	 */
	public class AppTest {
	   @Test
	   public void AppTest( ){
	        App.main(null);
	   }
	}
	```

3. Update junit version at pom.xml

	```xml
	<version>4.8.1</version>
	```

4. Run app by following command

	`mvn clean package`

### Steps To create Jpa Desktop App ###
Add Jpa 2.1.1 , Hibernate 4.3.9.Final since Hibernate 4.3+ now implements JPA 2.1. also mysql Dependency at pom.xml
```xml
<dependency>
  <groupId>org.eclipse.persistence</groupId>
  <artifactId>javax.persistence</artifactId>
  <version>2.1.1</version>
</dependency>
<dependency>
  <groupId>org.hibernate</groupId>
  <artifactId>hibernate-entitymanager</artifactId>
  <version>4.3.9.Final</version>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.6</version>
</dependency>
```
1. Create Entity class(Employee.java)

	```java
	package com.javaaround.model;
	import javax.persistence.Entity;  
	import javax.persistence.Id;  


	@Entity 
	public class Employee { 
		@Id   
		private int id;  
		private String firstName
		private Double salary;  
		  
		public int getId() {  
		    return id;  
		}  
		public void setId(int id) {  
		    this.id = id;  
		}  
		public String getFirstName() {  
		    return firstName;  
		}  
		public void setFirstName(String firstName) {  
		    this.firstName = firstName;  
		}  
		public Double getSalary() {  
		    return salary;  
		}  
		public void setSalary(Double salary) {  
		    this.salary = salary;  
		}  
	}	
	```	
2. Confiqure App(create main/resources/META-INF/persistence.xml file)
	```java
	<?xml version="1.0" encoding="UTF-8" ?>
	<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
	 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.1"
	 xsi:schemaLocation="
	 http://xmlns.jcp.org/xml/ns/persistence 
	 http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"> 
	  <persistence-unit name="hibernatePU" transaction-type="RESOURCE_LOCAL">
	   	  
	      <provider>org.hibernate.ejb.HibernatePersistence</provider>
	      	
	      <class>com.javaaround.model.Employee</class>
	      <properties>
	          <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/test" />
	          <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver" />
	          <property name="javax.persistence.jdbc.user" value="root" />
	          <property name="javax.persistence.jdbc.password" value="" />
	          <!-- flag for sql show or not at console -->
	          <property name="javax.persistence.jdbc.show_sql" value="true" /> 
	          <!-- create db table automatically -->
	          <property name="javax.persistence.schema-generation.database.action" value="create"/> 
	         
	    </properties>
	      
	   </persistence-unit>      	
	</persistence>

	```	

3. Use `EntityManager` (JPA Api) to perform crud(create,read,update,delete);

	Update App.java
	
	```java
	EntityManagerFactory emf = Persistence.createEntityManagerFactory( "hibernatePU" );
	EntityManager em = emf.createEntityManager();

	//start transaction
	em.getTransaction( ).begin( );

	Employee employee = new Employee( ); 
	employee.setId( 1201 );
	employee.setFirstName( "Gopal" );
	employee.setSalary( 40000 );

	em.persist( employee );
    
    //find by id
	Employee persistedEmployee = em.find(Employee.class,1201)
	System.out.print("Employee name = " + persistedEmployee.getFirstName());

	//update  
	//there is no update method to update.Update is done by make change of entity
	persistedEmployee.setSalary(20000);
    
    //delete 
     //em.remove(persistedEmployee);

	//end transaction
	em.getTransaction( ).commit( );

	//close resource
	em.close( );

	system.out.print(employee.getFirstName());
	emf.close( );

	```	

Run app by following command

`mvn clean package`	

### JPA Entity Life Cycle ###

![Image of Nested](images/jpa_object_lifecycle2.png) 

There are five states: 

1. does not exist : 
2. New/Transient : when an entity created by new operator.it has no effect at database
.if you want to any field is not managed/saved,you can mark it @Transient.

	```java
	@Transient
	private byte[] picture;
	``` 

3. Managed/Persisted  : 	when an entity is assoicate with `EntityManager` by its method e.g persist,find.merge etc then that object is going to managed state. 
	1. em.persist(employee);
	2. @PrePersist
	3. Database insert
	4. PostPersist

	when managed object property changed,then automatically update with db also.

	1. @PreUpdate
	2. Database Update
	3. @PostUpdate	

	Persistence context is the collection of all managed entity of an EntityManager. you can check if a specified entity object is in the persistence context:

	` boolean isManaged = em.contains(employee);`

	The persistence context can be cleared by using the clear method, as so:

    `em.clear();`

	If an entity object that has already exists in the persistence context the existing managed entity object is returned without actually accessing the database by call `em.find(employee.class,1201)`.

	but em.refresh(employee)  executes then always

		1. fetch the object from database
		2. @PostLoad

4. Remove : when `em.remove(persistedEmployee);` then object goes to remove state

	1. @PreRemove
	2. Pending removal from database until transaction is commit. if transaction commit then finally remove from db.
	3. @PostRemove

5. Detached : when 	`em.detach(employee);` or `em.close` then object to detached state

	1.  the entity is serialized to another tier

	if you want to detached back to managed, the following occurs: the entity is de-serialized, `em.merge(employee)` is invoked		

Update Employee.java
```java
import javax.persistence.PrePersist;
import javax.persistence.PostPersist;
import javax.persistence.PreUpdate;
import javax.persistence.PostUpdate;

@PrePersist
public void validate() {
   System.out.println("validating employee......");
}

@PostPersist
public void afterSave(){
	System.out.println("saved successfully.thank you");
}

@PreUpdate
public void validateUpdate() {
   System.out.println("validating  updating......");
}

@PostUpdate
public void afterUpdate(){
	System.out.println("Update successfully.thank you");
}
```

Run App

### Entity Listener ###
Mixing lifecycle event(cross-cutting event) code into your persistent classes is not recommended because it is not usable. JPA allows for use to separate another class and include it by `@EntityListeners` annotation.


create EmployeeListener.java

```java
package com.javaaround.listener;
import javax.persistence.PrePersist;
import javax.persistence.PostPersist;
import javax.persistence.PreUpdate;
import javax.persistence.PostUpdate;
import com.javaaround.model.Employee;
public class EmployeeListener {
	@PrePersist
    public void validate(Object obj) {
       System.out.println("validating employee......" + ((Employee) obj).getId ());
    }

	@PostPersist
	public void afterSave(Object obj){
		System.out.println("saved successfully.thank you");
	}

	@PreUpdate
	public void validateUpdate(Object obj) {
	   System.out.println("validating  updating......");
	}

	@PostUpdate
	public void afterUpdate(Object obj){
		System.out.println("Update successfully.thank you");
	}
}
```

Update Employee.java 

```java
@Entity
@EntityListeners({ 
	EmployeeListener.class
})
@Data 
public class Employee { 

```

### Default Entity Listener ### 

Default entity listeners are listeners that should be applied by default to all the entity classes.to skip it using the @ExcludeDefaultListeners annotation. Currently, default listeners can only be specified in a mapping XML file because there is no equivalent annotation.The mapping file has to be located either in the default location, META-INF/orm.xml or  in another location that is specified explicitly in the persistence unit definition

```xml
<persistence-unit name="hibernatePU" transaction-type="RESOURCE_LOCAL">
   <mapping-file>META-INF/myFile.xml</mapping-file>   
</persistence-unit> 
```

create DefaultListener.java

```java
package com.javaaround.listener;
import javax.persistence.PrePersist;
import javax.persistence.PostPersist;
import javax.persistence.PreUpdate;
import javax.persistence.PostUpdate;
import com.javaaround.model.Employee;
public class DefaultListener {
	@PrePersist
    public void validate(Object obj) {
       System.out.println("default validating ......");
    }

	@PostPersist
	public void afterSave(Object obj){
		System.out.println("default saved successfully.thank you");
	}

	@PreUpdate
	public void validateUpdate(Object obj) {
	   System.out.println(" default validating  updating......");
	}

	@PostUpdate
	public void afterUpdate(Object obj){
		System.out.println("default Update successfully.thank you");
	}
}
```

create orm.xml at META-INF

```xml
<entity-mappings xmlns="http://java.sun.com/xml/ns/persistence/orm"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://java.sun.com/xml/ns/persistence/orm
 http://java.sun.com/xml/ns/persistence/orm_1_0.xsd" version="1.0">
  <persistence-unit-metadata>
    <persistence-unit-defaults>
      <entity-listeners>
        <entity-listener class="com.javaaround.listener.DefaultListener" />
        <!-- <entity-listener class="samples.MyDefaultListener2" /> -->
      </entity-listeners>
    </persistence-unit-defaults>
  </persistence-unit-metadata>
</entity-mappings>
```

Run app again

### Superclass listener ###

By default Listeners that are attached to an entity class are inherited by its subclasses. to skip invoking any listeners declared in superclasses using the @ExcludeSuperclassListeners annotation

```java
import javax.persistence.ExcludeSuperclassListeners;
@Entity @ExcludeSuperclassListeners
public class Manager extends Employee {
}
```

### Callback Invocation Order ###

If more than one callback method has to be invoked for a lifecycle event (e.g. from multiple listeners) the invocation order is based on the following rules:

Default Listener -> top super class-> then super class-> actual enity listener




@NotNull: Checks whether the value is not null, disregarding the content
@NotEmpty: Checks whether the value is not null nor empty. If it has just empty spaces, it will allow it as not empty
@NotBlank: Checks whether the value is not null nor empty, trimming the value first. It means that, it won’t allow just empty spaces


### Parameters ###

JPA Parameter | Description | Hibernate Equivalent | Spring Data Equivalent
-------------- | ----------- | -------------------- | ----------------------
javax.persistence.jdbc.url | specify jdbc url | hibernate.connection.url | spring.datasource.url
javax.persistence.jdbc.driver |  specify jdbc driver | hibernate.connection.driver_class | spring.datasource.driver-class-name
javax.persistence.jdbc.user |  specify db user | hibernate.connection.username | spring.datasource.username
javax.persistence.jdbc.password |  specify db password | hibernate.connection.password | spring.datasource.password
javax.persistence.schema-generation.database.action : value allow `none,create,drop,drop-create` | automatically create schema(Table).  | hibernate.hbm2ddl.auto : value allow `create,update` | spring.jpa.generate-ddl=true &spring.jpa.hibernate.ddl-auto : value allow `create,update,none`
javax.persistence.jdbc.show_sql | flag for sql show or not at console | hibernate.show_sql | spring.jpa.show-sql
javax.persistence.sql-load-script-source | Defines the location of the SQL script that shall be used load data into the table at startup.| hibernate.hbm2ddl.import_files | spring.datasource.initialize<br>=true
javax.persistence.schema-generation.scripts.create-target | Defines the target location of the create script(DLL) generated by the persistence provider. | "" | ""
javax.persistence.schema-generation.scripts.drop-target | Defines the drop-target location of the create script(DLL) generated by the persistence provider. | "" | ""

Recommendation : Dont use provider specific thing.use jpa specific thing so that your code needs less code to change if you move another jpa provider(JPA provider independent)

### sql-load-script-source ###
create data.sql at main/resources

```sql
INSERT INTO employee (id, firstName,salary) VALUES (1, 'Md.Alamin',2000.00);
INSERT INTO employee (id, firstName,salary) VALUES (2, 'Md.Rafiq',2000.00);
```

update persistence.xml
```xml
<property name="javax.persistence.schema-generation.database.action" value="drop-and-create"/>
<property name="javax.persistence.sql-load-script-source" value="data.sql"/>
```
Run app by following command

`mvn clean package`	


### Generate create & drop schema ### 

update persistence.xml
```xml
<property name="javax.persistence.schema-generation.scripts.action" value="drop-and-create"/>
<property name="javax.persistence.schema-generation.scripts.create-target" value="create.sql"/>
<property name="javax.persistence.schema-generation.scripts.drop-target" value="drop.sql"/>
```

this will generate `create.sql & drop.sql` at project root.


### Generator ###
In the above example
`employee.setId( 1201 ); ` need to set id manually but we can get value from database system e.g auto increment column.For that purpose,we need to use generator. 

There are 4 generator types<br/>

1. IDENTITY : Provider relies on an auto-incremented database column to generate the primary key
	-	Update Employee.java
		```java
		@Id 
		@GeneratedValue(strategy = GenerationType.IDENTITY)  
		private int id; 
		```

		Update App.java
		```java
		//employee.setId( 1204 );
		```
2. SEQUENCE : Provider requests the primary key value from a database sequence
	-	 if there is no sequence defined, provider creates a sequence automatically e.g. in case of Oracle database, hibernate it creates a sequence named HIBERNATE_SEQUENCE.

		Update Employee.java
		```java
		@Id 
		@GeneratedValue(strategy = GenerationType.SEQUENCE)  
		private int id; 
		```
	- if you want to give your own sequence

	  	Suppose you have following sequence
	  	```sql
	  	create sequence emp_seq
		minvalue 1
		maxvalue 9999999999999999999999999999
		start with 1
		increment by 2;
	  	```

		```java
		@Id 
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_generator")
		@SequenceGenerator(name="emp_generator", sequenceName = "emp_seq", initialValue=1, allocationSize=2)  
		private int id; 
		```
		`allocationSize :`
		increment value.default value 50

3. TABLE : Provider uses a database table to get next sequence.The TABLE strategy is very similar to the SEQUENCE strategy:
	-   Suppose you have following table

		![Image of Nested](images/tablegenerator.png) 

		```java
		@Id 
		@GeneratedValue(strategy = GenerationType.TABLE, generator = "t_generator")
		@TableGenerator(name="t_generator",table="generator", pkColumnName = "gen_key", 
		<br/>pkColumnValue = "gen_valueemp_id",valueColumnName = "gen_value",
		<br/>
		initialValue = 1, allocationSize = 1 )
		private int id; 
		```
4. AUTO : Provider selects the above generation strategy based on the used dialect.It is recommended to use when your database is not fixed.it is default strategy
	-	Example

		```java
		@Id
		@GeneratedValue 
		//or @GeneratedValue(strategy = GenerationType.AUTO)
		private int id;
		```

### lombok ###

Lombok tools automatically generates appropriate getters, setters, toString(),hashCode(),equals()

Add depenency at pom.xml

```xml
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <version>1.16.12</version>
</dependency>
```	
Update Employee.java
```java
/*	public int getId() {  
	    return id;  
	}  
	public void setId(int id) {  
	    this.id = id;  
	}  
	public String getFirstName() {  
	    return firstName;  
	}  
	public void setFirstName(String firstName) {  
	    this.firstName = firstName;  
	}  
	public Double getSalary() {  
	    return salary;  
	}  
	public void setSalary(Double salary) {  
	    this.salary = salary;  
	}  */
```
You can test (employee.class) methods add or not by following command <br> 
`javap employee`

```java
public class com.javaaround.model.Employee {
  public com.javaaround.model.Employee();
  public int getId();
  public java.lang.String getFirstName();
  public java.lang.Double getSalary();
  public void setId(int);
  public void setFirstName(java.lang.String);
  public void setSalary(java.lang.Double);
  public boolean equals(java.lang.Object);
  protected boolean canEqual(java.lang.Object);
  public int hashCode();
  public java.lang.String toString();
}
```
### Column Definition ###

@Column is used define database table column definition like length,column name,nullable etc

### @Column Element ###

| Element        | Description           | Default value  |
| ------------- |:-------------:| -----:|
| name     | Defines name of column | propertyName |
| unique      |  define a unique constraint on the column.Value allow (true,false)     |   false |
| nullable      |  define column value allow null or not.Value allow (true,false)     |   false |
| insertable      |   Whether the column is included in SQL INSERT statements generated by the persistence provider .Value allow (true,false)     |   true |
| updatable      |  Whether the column is included in SQL UPDATE statements generated by the persistence provider .Value allow (true,false)     |   true |
| columnDefinition       |  define column data types.     |   basic mapping |
| length       |  define column size of string type     | highest value|
| precision       |  The precision is the number of digits in the unscaled value. For instance, for the number 123.45, the precision returned is 5.      | |
| Scale       |  Number of digits to the right of the decimal point. For instance, for the number 123.45, the scale returned is 2.      | |
| description       |  remarks of the column      | |
| table       |  specify secondary table to store it with this table      | |

Update Employee.java

```java
@Basic(fetch=FetchType.LAZY,optional=false)
@Column(name="first_name",length=35,nullable=true)  
private String firstName;
//salary less than 10000000(1crore)
@Column(precision=7, scale=2)
private Double salary; 
@Column(columnDefinition="TIMESTAMPTZ")
private Date joinDate;
@Column(description="M for Male,F for female")
private Gender gender;
```

### @Table Element ###

Bedefault class name is used to table name . you can give your custom name by @Table annotation
`@Table(name="employesssss")`

@Table annotation can also for used to define constraints of a field.`@Table(uniqueConstraints=@UniqueConstraint(columnNames="firstName"))`

### JPA Types ###

1. Entity Types : object has own identity(primary key value) e.g Employee
2. Value Types : Object has no database identity(no primary key) . Value Type object belongs to an Entity Type Object.
	1. Basic Types :  they map a single database value (column) to a single Java type. e.g String, Character, Boolean, Integer, Long, Byte
	2. Composite(Embeddable) Types : There are some custom object(Address) are associated of another entity object(Employee). it is not saved into db as separate table.
	3. Collection types :  collection of Basic value types, Composite types and custom types. it is saved into db as a separate table.
			
### Attribute Mappings ###

1. Basic Mappings : 

	Mappings of simple Java types(String, Number, Wrapper,Date ,primitive,enums, and any other type that implements java.io.Serializable) map directly to the column value in the database. The following table shows mapping java type to db types
	
	| Java Type        | Database Type | 
	| ------------- |:-------------:| 
	| String (char, char[]) |	VARCHAR (CHAR, VARCHAR2, CLOB, TEXT) | 
	| Number (BigDecimal, BigInteger, Integer, Double, Long, Float, Short, Byte) |	NUMERIC (NUMBER, INT, LONG, FLOAT, DOUBLE | 
	| int, long, float, double, short, byte |	NUMERIC (NUMBER, INT, LONG, FLOAT, DOUBLE) | 
	| byte[] |	VARBINARY (BINARY, BLOB) | 
	| boolean (Boolean) |	BOOLEAN (BIT, SMALLINT, INT, NUMBER) | 
	| java.util.Date |	TIMESTAMP (DATE, DATETIME) | 
	| java.sql.Date |	DATE (TIMESTAMP, DATETIME) | 
	| java.sql.Time |	TIME (TIMESTAMP, DATETIME) | 
	| java.sql.Timestamp |	TIMESTAMP (DATETIME, DATE) | 
	| java.util.Calendar |	TIMESTAMP (DATETIME, DATE) | 
	| java.lang.Enum |	NUMERIC (VARCHAR, CHAR) | 
	| java.io.Serializable |	VARBINARY (BINARY, BLOB) | 

	In JPA a basic attribute is mapped through the `@Basic` annotation.it is default annotation 

	### @Basic Element ###

	| Element        | Description           | Default value  |
	| ------------- |:-------------:| -----:|
	| fetch     | Defines whether the value of the field should be lazily loaded or eagerly fetched. | EAGER |
	| optional      | Defines whether the value of the field  may be null.     |   true |


	Update Employee.java

	```java
	@Basic(fetch=FetchType.LAZY,optional=false)  
	private String firstName;
	```

	update App.java
	```java
	 //employee.setFirstName( "Md.Shamim Miah" );
	``` 

	not-null property references a null or transient value : com.javaaround.model.Employee.firstName exception shown

	Update Employee.java

	```java
	@Basic(fetch=FetchType.LAZY,optional=false) 
	private String firstName;
	```


	### Date Mapping ###
	Java 1.0, Java only had a java.util.Date type, which was both a date, time and milliseconds. 
	In Java 1.1 this was expanded to support the common database types with java.sql.Date, java.sql.Time, and java.sql.Timestamp, then to support internationalization Java created the java.util.Calendar type 

	Update Employee.java

	```java
	private Date joinDate;
	```

	update App.java
	```java
	  employee.setJoinDate(new Date());
	``` 

	above example will map database datetime datatype(default). but some database don't support datetime,they support date or time only.if wish to map it to a DATE or TIME then `@Temporal` annotation is used 

	### @Temporal ###
	| Element        | Value allow           | Default value  |
	| ------------- |:-------------:| -----:|
	| TemporalType     | DATE,TIME,DATETIME | DATETIME |

	```java
	@Temporal(TemporalType.DATE) 
	private Date joinDate;
	```

	### Enum Mapping ###

	create Gender.java

	```java
	package com.javaaround.util;
	public enum Gender{
		F,M
	}	
	```

	Update Employee.java

	```java
	private Gender gender;
	```

	Update App.java

	```java
	employee.setGender(Gender.M);
	```

	By Default (ORDINAL) enum store  position of enum constant above example Gender.M is 1

	if you want to string (M or F) instead of position(1 or 2) value need to override  @Enumerated

	### @Enumerated ###
	| Element        | Value allow           | Default value  |
	| ------------- |:-------------:| -----:|
	| EnumType     | ORDINAL,STRING | ORDINAL |

	Update Employee.java

	```java
	@Enumerated(EnumType.STRING)
	private Gender gender;
	```

	### Large OBject ###
	There two database types of large object.
	1. BLOB (Binary LOB)
	2. CLOB (Character LOB). we know VARCHAR(255 character) have size limitations.for allow big text by using CLOB.

	JPA defines the @Lob annotation  to define an attribute maps to a LOB type in the database.A @Lob may be either a binary or character or serialize type.

	update Employee.java
	```java
	@Lob
	@Basic(fetch=FetchType.LAZY)	
	private byte[] picture;
	@Lob
	//private char[] remarks;	
	private String remarks;
	```

	To generate byte[] array we use apache commons io

	add dependency at pom.xml

	```xml
	<dependency>
        <groupId>commons-io</groupId>
        <artifactId>commons-io</artifactId>
        <version>2.5</version>
    </dependency>
	```

	Update App.java

	```java
	employee.setRemarks("luren ipsomluren ipsomluren ipsomluren " 
	  	+ "ipsomluren ipsomluren ipsomluren ipsomluren ipsomluren "
	  	+ "ipsomluren ipsomluren ipsomluren ipsomluren ipsomluren "
	  	+ "ipsomluren ipsomluren ipsomluren ipsomluren ipsomluren "
	  	+ "ipsomluren ipsomluren ipsomluren ipsomluren ipsomluren "
	  	+ "ipsomluren ipsomluren ipsomluren ipsomluren ipsomluren "
	  	+ "ipsomluren ipsomluren ipsomluren ipsomluren ipsomluren "
	  	);

	  //
	  FileInputStream fis;
	  try{
	  	fis = new FileInputStream("E:/shamim/image/shamim.jpg");
	  	byte[] bytes = IOUtils.toByteArray(fis);
	  	employee.setPicture(bytes);
	  }catch(Exception e){

	  }
	```

	### Converter ###

	The following example use java 8 date.or you can use jodatime date

	Update Employee.java

	```java
	import java.time.LocalDate;

	private Boolean isActive;
	private LocalDate createDate;
	```

	Update App.java

	```java
	 employee.setIsActive(Boolean.TRUE);
	 employee.setCreateDate(LocalDateTime.now().toLocalDate());
	```
	Run App

	Result : Boolean use @Basic mapping it converts 0,1
	LocalDate can't convert date type !!!

	if you want to Boolean convert to String eg. "T", "F"
	LocalDate need to convert Date object at java side 

	For that JPA 2.1 introduced Attribute Converter .  A Converter is a user defined class that provides custom conversion Logic in Java code and mark it @Converter annotation

	Create BooleanConverter.java

	```java
	package com.javaaround.converter;
	import javax.persistence.AttributeConverter;
	import javax.persistence.Converter;

	@Converter
	public class BooleanConverter implements AttributeConverter<Boolean, String> {

	 
	   @Override
	   public String convertToDatabaseColumn(Boolean isActive) {
	     return Boolean.TRUE.equals(isActive) ? "T" : "F";
	   }
	   
	   @Override
	   public Boolean convertToEntityAttribute(String value) {
	       return "T".equals(value);
	   }

	}
	```

	Create LocalDateConverter.java

	```java
	package com.javaaround.converter;
	import javax.persistence.AttributeConverter;
	import javax.persistence.Converter;
	import java.sql.Date;
	import java.time.LocalDate;

	@Converter
	public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

	 
	   @Override
	   public Date convertToDatabaseColumn(LocalDate localDate) {
	     return localDate == null ? null : Date.valueOf(localDate);
	   }
	   
	   @Override
	   public LocalDate convertToEntityAttribute(Date date) {
	       return date == null ? null : date.toLocalDate();
	   }

	}
	```

	There are two way to apply the above converter

	1. Using @Convert annotation
	2. global Converter(autoApply=true)

	We are using LocalDateConverter by global converter

	Update LocalDateConverter.java

	`@Converter(autoApply=true)`

	We are using BooleanConverter by @Convert annotation

	Update Employee.java

	```java
	import com.javaaround.converter.BooleanConverter;
	import javax.persistence.Convert;

	@Convert(converter=BooleanConverter.class)
	private Boolean isActive;
	
	```

2. User defined type Mapping :
	

	1. Embeddable Object : Eevery property of Embeddable(Address) are mapped to db column. Such object are marked by @Embeddable annotation.

		Add Address.java

		```java
		package com.javaaround.model;
		import javax.persistence.Embeddable;  
		import javax.persistence.Column;
		import lombok.Data;

		@Embeddable
		@Data 
		public class Address { 
			
			private String street;
			private String city;
			@Column(name="post_code")
			private String postcode;

		}	
		```

		Embedd this object to associated entity by @Embedded annotation

		Update Employee.java

		```java
		@Embedded
		private Address homeAddress;
		```

		if you have two Address object in same entity what happens ? 


		```java
		@Embedded
		private Address homeAddress;
		@Embedded
		private Address officeAddress;
		```
	    Run app 

		it shows Repeated column in mapping for entity.How we can solve it.We need to override default column name(street,city,post_code) by using @AtrributeOverride Annotation of homeAddreess or officeAddress(any one . other take default).

		Update Employee.java

		```java
		Embedded
		//street = field name
		@AttributeOverrides({
			@AttributeOverride(name="street",column=@Column(name="home_street")),
			@AttributeOverride(name="city",column=@Column(name="home_city")),
			@AttributeOverride(name="postcode",column=@Column(name="home_post_code"))
		})
		private Address homeAddress;

		@Embedded
		private Address officeAddress;
		```

		Some time we need composite primary key to identify records. For example employee are identity by id and department name. There are two way to assign composite primary key

		1. Using @IdClass
		2. Using @EmbeddedId 

		Create Embeddable class EmployeeId.java

		```java
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
		```

		Embedded above object to Employee and mark it by @EmbeddedId annotation

		Update Employee.java

		```java
		import javax.persistence.EmbeddedId;
		import javax.persistence.Entity; 
		@Entity
		@Data 
		public class Employee { 
			@EmbeddedId
			private EmployeeId employeeId;
			@Basic(optional=false)  
			private String firstName;
		```

		Update App.java

		```java
		  Employee employee = new Employee( );
	      employee.setFirstName( "Md.Shamim Miah" );

	      EmployeeId  empId = new EmployeeId();
	      empId.setId(222);
	      empId.setDeptName("IT");
	      employee.setEmployeeId(empId);
          //save into db	      
	      em.persist( employee );
		```

		Run App

		Above example using  @IdClass

		Update EmployeeId.java hide //@Embeddable

		```java
		//@Embeddable
		
		```
		Update Employee.java

		```java
		@Entity
		@IdClass(EmployeeId.class)
		@Data 
		public class Employee { 
			@Id
			private int id;
			@Id
			private String deptName;
			@Basic(optional=false)  
			private String firstName;
		}
		```

		update App.java

		```java
		Employee employee = new Employee( );
        employee.setFirstName( "Md.Shamim Miah" );
        employee.setId(222);
        employee.setDeptName("IT");
        em.persist(employee);
		```
	

	2. Inheritance Mapping

		![Image of Nested](images/inheritance1.jpg) 

		There are four strategy of inheritance mapping. Strategy are define by @InheritanceType
		```java
		public enum InheritanceType {
		    SINGLE_TABLE,
		    JOINED,
		    TABLE_PER_CLASS
		}; 

		```

		1. Mapped SupperClass

			In Mapped Supperclass strategy,keep basic properties in a super class and mark it @MappedSuperclass. It will not be associated with any database table.If we want to change the column name different from super class, we need to use @AttributeOverride

			Update Employee.java

			```java
			import javax.persistence.MappedSuperclass;
			@MappedSuperclass
			@Data 
			public class Employee { 
				@Id 
				@GeneratedValue(strategy = GenerationType.AUTO)  
				private int id;
				@Basic(optional=false)  
				private String firstName;
			}
			```

			Create RegularEmployee.java

			```java
			package com.javaaround.model;
			import javax.persistence.Entity; 
			import javax.persistence.Column; 
			import javax.persistence.AttributeOverride; 
			import lombok.Data; 
			  
			@Entity
			@AttributeOverride( name="firstName", column = @Column(name="first_name") )
			@Data 
			public class RegularEmployee extends Employee{  
			    private Double salary;  
			    private int bonus;  
			   
			}  
			```

			Create ContactEmployee.java

			```java
			package com.javaaround.model;
			import javax.persistence.Entity; 
			import javax.persistence.Column; 
			import javax.persistence.AttributeOverride; 
			import lombok.Data; 
			  
			@Entity
			@AttributeOverride( name="firstName", column = @Column(name="first_name") )
			@Data 
			public class ContactEmployee extends Employee{  
			    @Column(name="pay_per_hour")  
			    private float pay_per_hour;  
			      
			    @Column(name="contract_duration")  
			    private String contract_duration;  
			   
			}  
			```

			Update App.java

			```java
			ContactEmployee employee = new ContactEmployee( ); 
	        employee.setFirstName("Md.Shamim");
	        employee.setPay_per_hour(122);
	        employee.setContract_duration("2 years");
	        RegularEmployee employee1 = new RegularEmployee( ); 
	        employee1.setFirstName("Md.Shamim1");
	        employee1.setSalary(122.00);
	        employee1.setBonus(1000);

            //save into db	      
	        em.persist( employee );
	        em.persist( employee1 );
			```

			There are following problem of above strategy
			1.  Cannot query, persist, or have relationships because it has no entity

		2. SINGLE_TABLE	 strategy

			The single table strategy maps all entities of the inheritance structure to the same database table. It is default strategy . Here, an extra column (also known as discriminator column) is created in the table to identify the  subclass.


			Update Employee.java

			```java
			@Entity 
			//@MappedSuperclass
			```

			Run App

			Result : 

			![Image of Nested](images/single_table.png)

			In the above image, By default

			1. discriminator column = DTYPE . you can overrid by @DiscriminatorColumn

				### @DiscriminatorColumn ###
				| property        | Description 
				| ------------- |:-------------:
				| name     | Column name of discriminator
				| discriminatorType     | DiscriminatorType enum.value CHAR,INTEGER,STRING,CLASS.default String
				| 	columnDefinition     | Define column data type. The default is generated by the Persistence provider and is implementation-specific
				| 	length     | The column length for String-based discriminator types

			2. discriminator Value = class name . you can overrid by @DiscriminatorValue
			
			Update Employee.java

			```java
			import javax.persistence.DiscriminatorColumn;
			import javax.persistence.DiscriminatorType;
			@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)  
			```	

			Update ContactEmployee.java

			```java
			import javax.persistence.DiscriminatorValue; 
			@DiscriminatorValue("outsideemp")  
			```

			Update RegularEmployee.java

			```java
			import javax.persistence.DiscriminatorValue; 
			@DiscriminatorValue("outsideemp")  
			```

			### Advantages ###

			Single table inheritance mapping is the fastest of all inheritance models, since it never requires a join to retrieve a persistent instance from the database. Similarly, persisting or updating a persistent instance requires only a single INSERT or UPDATE statement. Finally, relations to any class within a single table inheritance hierarchy are just as efficient as relations to a base class.

			### Disadvantages ###

			The larger the inheritance model gets, the "wider" the mapped table gets, in that for every field in the entire inheritance hierarchy, a column must exist in the mapped table. This may have undesirable consequence on the database size, since a wide or deep inheritance hierarchy will result in tables with many mostly-empty columns 

		3. TABLE_PER_CLASS	Strategy

			In this strategy,each concrete class + supper class is mapped to a separate table in the database . To define this strategy need to use `@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS` annotation at parent table

			Update Employee.java

			```java
			@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
			```

			Disadvantage of this approach is that duplicate columns are created in the subclass tables.This strategy provides poor support for polymorphic relationships and usually requires either SQL UNION queries or separate SQL queries for each subclass for queries that cover the entire entity class hierarchy.

		4. JOINED Strategy	

		   JOINED Strategy is very similar TABLE_PER_CLASS but now supper class property can't store sub class .  they are reated using primary and foreign key. So there will not be duplicate columns in the relation.

		   Some JPA providers support joined inheritance with or without a discriminator column, some required the discriminator column, and some do not support the discriminator column. So joined inheritance does not seem to be fully standardized yet.

		   Hibernate: A discriminator column on joined inheritance is supported but not require

		   Update Employee.java

		   ```java
		   @Inheritance(strategy = InheritanceType.JOINED)
		   ````

		   Subclass map by @PrimaryKeyJoinColumn annotation  to define foreign key
		   ### @PrimaryKeyJoinColumn ###
			| property        | Description 
			| ------------- |:-------------:
			| name     | Column name of foreign key
			| referencedColumnName      | The name of the primary key column of the table being joined to.
			| 	columnDefinition     | Define column data type. 
			
			Update ContactEmployee.java

			```java
			import javax.persistence.PrimaryKeyJoinColumn; 
		    @PrimaryKeyJoinColumn(name="employee_id", referencedColumnName="id")
		    ````

		    Update RegularEmployee.java
			
			```java
			import javax.persistence.PrimaryKeyJoinColumn; 
		    @PrimaryKeyJoinColumn(name="employee_id", referencedColumnName="id")
		    ````

		    Disadvange  : 

		    the joined strategy is often the slowest of the inheritance models


3. Collection Mapping

	| Collection Form        | Description 
	| ------------- |:-------------:
	| indexed     | List and Map are index based collection, so an extra column will be created in the table for store index / key.
	| 	non-indexed     | Set is non-indexed.

	@ElementCollection annotation is used to collection(Collection or Map of Basic or Embeddable objects) mapping and stored in a separate table.

	### Basic Type Collection ###

	1. List

		Update Employee.java
		```java
		@ElementCollection
		@Column(name="CONTACT_LIST")
		private List<String> contacts;
		```

		Update App.java

		```java
		List<String> contacts = new ArrayList<String>();
        contacts.add("20111112550");
        contacts.add("20111555550");
        Employee employee = new Employee( ); 
        employee.setFirstName("Md.Shamim");
        employee.setContacts(contacts);
      
		```

		Run App

		Default Table created = Entity property name here contacts.
        Dafault foreign key = id

        you can override by `@JoinTable` or `@CollectionTable` annotation

        Update Employee.java

        ```java
        @JoinTable(name="Emp_contacts", joinColumns=@JoinColumn(name="employee_id"))
        ```

        Run App again

    2. Map
    
    	Update Employee.java

    	```java
    	@ElementCollection
		@Column(name="CONTACT_LIST")
		private Map<String, String> contacts;
    	```  

    	Update App.java

		```java
		Map contactMap = new HashMap(); 
	    contactMap.put("home","000-289-3214"); 
	    contactMap.put("mobile","001-760-2332"); 
        Employee employee = new Employee( ); 
        employee.setFirstName("Md.Shamim");
        employee.setContacts(contacts);
      
		```

		Run App 

		Default Table created = employeeentityname_property name e.g EMPLOYEE_ADDRESS . 
		Dafault foreign key = employeeentityname_employeeentityid field e.g EMPLOYEE_ID
		Extra column is create to store key value = property name_KEY e.g CONTACTS_KEY.It can overrid by `@MapKeyColumn` annotation `name` property

		Update Employee.java

		```java
		@ElementCollection
		@JoinTable(name="Emp_contacts", joinColumns=@JoinColumn(name="empl_id"))
		@MapKeyColumn(name="CONTACT_TYPE")
		@Column(name="CONTACT_LIST")
		private Map<String, String> contacts;
		```


		Run App  Again

		When key is enum type

		```java
		package com.javaaround.util;
		public enum PhoneType{
			HOME,OFFICE
		}
		```

		```java
		@ElementCollection
	    @CollectionTable(name="EMP_PHONE")
	    @MapKeyEnumerated(EnumType.STRING) // default orinal
	    @MapKeyColumn(name="PHONE_TYPE")
	    @Column(name="PHONE_NUM")
	    private Map<PhoneType, String> phoneNumbers = new HashMap();
		```

		when key is date type

		```java
		@MapKeyTemporal(TemporalType.DATE)
        protected java.util.Map<java.util.Date, Employee> employees;
		```


	### Embeddable Object Collection ###
	1. Set/List
	
		In the above we have two object(homeAddress,officeAddress) but if you have collection address(if you don't know how many address are needed) then lots of column created .it is not recommended. Instead we can create separate table and reference to employee id foreign key

		Update Employee.java

		```java
		import javax.persistence.ElementCollection;

		@ElementCollection
		private Set<Address> address = new HashSet();
		```
        For non generic Collection

        ```java
		import javax.persistence.ElementCollection;

		@ElementCollection(targetClass=Address.class)
		private Set address = new HashSet();
		```

		Update App.java

		```java
		 Address adr1 = new Address();
	     adr1.setStreet("parijat");
	     adr1.setCity("gazipure");
	     adr1.setPostcode("1200");

	     Address adr2 = new Address();
	     adr2.setStreet("bishasbettka");
	     adr2.setCity("tangail");
	     adr2.setPostcode("1900");

	     employee.getAddress().add(adr1);
	  	 employee.getAddress().add(adr2);
	     //save into db	      
	     em.persist( employee );
		```

		Run App

		![Image of Nested](images/collectionmap.png) 

		Default Table created = employeeentityname_property_name e.g EMPLOYEE_ADDRESS . 
		Dafault foreign key = employeeentityname_employeeentityid field e.g EMPLOYEE_ID
		you can override by `@JoinTable` or `@CollectionTable` annotation `joinColumns` property

		Update Employee.java

		```java
		import javax.persistence.JoinColumn;
		import javax.persistence.JoinTable;
		@JoinTable(
			name="emp_address",
			joinColumns = @JoinColumn(name="emp_id")
		)
		private Set<Address> address = new HashSet();
		```

		Address table has no primary key (Id) column. if you want to provide it through @CollectionId annotation.It is not standarise of jpa.it is hibernate specific feature.

		Remember :  Set do not support @CollectionId

		Update Employee.java

		```java
		@GenericGenerator(name = "hilo-gen",strategy = "hilo")
		@CollectionId(
			columns = { @Column(name="address_id")},
			generator = "hilo-gen",
			type = @Type(type="long")
		)
		private List<Address> address = new ArrayList<Address>();
		```
		Address table has generate now primary key (address_id) column.
	2. Map

		Key is basic and value is embeddable object

		Update Employee.java

		```java
		private Map<String,Address> contacts;
		```
		Update App.java
		```java
		Address homeAddress = new Address();
        homeAddress.setStreet("parijat");
        homeAddress.setCity("gazipure");
        homeAddress.setPostcode("1200");

        Address officeAddress = new Address();
        officeAddress.setStreet("bishasbettka");
        officeAddress.setCity("tangail");
        officeAddress.setPostcode("1900"); 

        Map<String,Address> contacts = new HashMap<String,Address>();
        contacts.put("home",homeAddress);
        contacts.put("office",officeAddress);
        Employee employee = new Employee( ); 
        employee.setFirstName("Md.Shamim");
        employee.setContacts(contacts);

		```

		Override Address column

		@AttributeOverride(name="street",column=@Column(name="street_name"))


		Key is entity and value is basic object

		Update Department.java

		```java
		@ElementCollection
	    @CollectionTable(name="EMP_SENIORITY")
	    @MapKeyJoinColumn(name="EMP_ID") // define key map column since it is join
	    @Column(name="SENIORITY")
	    private Map<Employee, Integer> seniorities;
		```

		Update App.java

		```java
		 Employee employee = new Employee( ); 
	      employee.setFirstName("Md.Shamim");
	      Employee employee1 = new Employee( ); 
	      employee1.setFirstName("Md.Shamim");
	      Map<Employee,Integer> seniorities = new HashMap<Employee,Integer>();
	      seniorities.put(employee,1);
	      seniorities.put(employee1,2);
	      
	      Department department = new Department();
	      department.setName("IT");
	      department.setSeniorities(seniorities);
	     
          //save into db	      
	      em.persist( department );
	      em.persist( employee );
	      em.persist( employee1 );	
		```

	### Entity Object Collection ###

	Entity Object Collection means relationship mapping at database

	1. OneToMany   -> ManyToOne : 


		![Image of Nested](images/oneToMany.png)

		Update Employee.java

		```java
		import javax.persistence.ManyToOne;
		@Entity 
		@Data 
		public class Employee { 
			@Id 
			@GeneratedValue(strategy = GenerationType.AUTO)  
			private int id;
			@Basic(optional=false)  
			private String firstName;
			@ManyToOne
			@JoinColumn(name = "department_id")
			private Department department;
			
		}	
		```

		Update Department.java

		```java
		@Entity
		@Data 
		public class Department { 
			@Id 
			@GeneratedValue
			private int id;
			@Basic(optional=false)  
			private String name;
			
		}		
		```

		Run App

		error :  object references an unsaved transient instance - save the transient instance before flushing

		Solution :
		1. Save manually department instance
		`em.persist(department);`
		2. Save automatically using cascade (recommended)


		JPA allows us to propagate entity state changes from Parents to Child entities automatically by `CascadeType` mappings. 
		### CascadeType Element ###

		| CascadeType        | Description
		| ------------- |:-------------:| 
		| ALL     | Cascade all operations | 
		| PERSIST     | Cascade PERSIST operations | 
		| MERGE     | Cascade MERGE operations | 
		| REMOVE     | Cascade REMOVE operations | 
		| REFRESH     | Cascade REFRESH operations | 
		| DETACH     | Cascade DETACH operations | 

		`@ManyToOne(cascade=CascadeType.ALL)`

		### Orphan Removal (JPA 2.0) ### 

		Cascading of the remove operation if and only occurs when the remove is called on the object(em.remove(employee)). This is not normally what is desired on a dependent relationship.When dependent entity(Department) setting null or refer to  another department then previous entity should be automatically without remove operation occur

		It is done by setting `orphanRemoval=true` on @OneToOne and @OneToMany annotations:


		In th above example is an unidirectional relationship since only one entity has a relationship field that refers to the other among two

		In a bidirectional relationship, both entity have a reference to each other 


		Update Department.java

		```java
		//Generic
		@OneToMany(mappedBy = "department")
		private List<Employee> employees;

		/* for non generic
		@OneToMany(mappedBy = "department",targetEntity=Phone.class)
		private List<Employee> employees;
		*/
		```

		Specifies the ordering of the elements of a collection by @OrderBy annotation 

		Order by primary key

		```java
		//Generic
		@OneToMany(mappedBy = "department")
		@OrderBy
		private List<Employee> employees;

		```

		Order by a string field.default asc

		```java
		//Generic
		@OneToMany(mappedBy = "department")
		@OrderBy("firstName")
		private List<Employee> employees;

		```

		Order by a string field by desc

		```java
		//Generic
		@OneToMany(mappedBy = "department")
		@OrderBy("firstName desc")
		private List<Employee> employees;

		```

		if embeddable use .(dot) notation

		```java
		@Entity 
	    public class Person {
	         ...
	       @ElementCollection
	       @OrderBy("zipcode.zip, zipcode.plusFour")
	       public Set<Address> getResidences() {...};
	       ...
	    }
	 
	    @Embeddable 
	    public class Address {
	       protected String street;
	       protected String city;
	       protected String state;
	       @Embedded protected Zipcode zipcode;
	    }
	 
	    @Embeddable 
	    public class Zipcode {
	       protected String zip;
	       protected String plusFour;
	    }
		```

		You can fetch lazy loading

		```
		@OneToMany(mappedBy = "department",fetch=FetchType.LAZY)
		private List<Employee> employees;
		```

		Update Department

		```java
		@OneToMany(mappedBy = "department")
	    private Map<Integer, Employee> empMap;
		```

		Run App : extra column `EMPMAP_KEY` is generated . If the Map key is the primary key or a persistent field or property of the entity that is the Map value, use the @MapKey annotation then `EMPMAP_KEY` is not generated

		Update Department

		```java
		@OneToMany(mappedBy = "department")
		@MapKey
	    private Map<Integer, Employee> empMap;
		```

		if you want tp name of the persistent field or property of the associated entity that is used as the map key instead of primary field

		`@MapKey(name="empddd_id")`

		For non generic map @MapKeyClass is used define key type
		```java
	    @MapKeyClass(String.class)
        Map images; 
		```

		Note : @MapKeyClass && @MapKey is not use same time at a field


	2. ManyToMany

		![Image of Nested](images/manyTomany.png)

		In database,many to many relationships does not support. To suppor manyTomany,we require extra table that have two oneTomany relationship

		In JPA , ManyToMany relationship is defined through the @ManyToMany annotation

		Update Employee.java

		```java
		@ManyToMany
        private List<Project> projects;
		```
		Create project.java

		```java
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
		```

### OneToOne Relation Mapping ###

## Access Type ###
There are two accsss type
1. Field access : JPA provider access fields directly, like how we can access fields within a class.
2. Property access : JPA provider calls getter and setter methods runtime to load/store

The default behaviour is, location of the mandatory id property of the POJO with @Id  annotation in jpa determines the access level of this domain object. It can be property and field

```java
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "ID")
private Long studentId;
```

Here default access is field.

```java
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "ID")
public Long getStudentId() {
	return studentId;
}
```

Here default access is property.

JPA provides @Access annotation for overriding the default behavior by using AccessType.FIELD and AccessType.PROPERTY


```java
@Access(value=AccessType.FIELD)
@Entity
public class Employee{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "FNAME")
	private String firstName;

	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
 
	public String getFirstName() {
		return firstName;
	}
 
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


}
```

JPA supports Mixed Access by override the access strategy of individ- ual properties with @Access annotation

```java
@Access(value=AccessType.FIELD)
@Entity
public class Employee{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "FNAME")
	private String firstName;

	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
 	@Access(value=AccessType.PROPERTY)
	public String getFirstName() {
		return firstName;
	}
 
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
```

### Steps To create Jpa EE App ###

![Image of Nested](images/EE.png) 

1. create javaee project using maven by following command

	`mvn archetype:generate`

	Search -j2ee-simple .
	choose project no
	Give groupId,arctifactId etc

2. Add dependency at ejbs/pom.xml
	```xml
	<dependency>
	  <groupId>javax.ejb</groupId>
	  <artifactId>javax.ejb-api</artifactId>
	  <version>3.2</version>
	</dependency>
	<dependency>
	 <groupId>org.hibernate</groupId>
	 <artifactId>hibernate-entitymanager</artifactId>
	 <version>4.3.9.Final</version>
	</dependency>
	<dependency>
	   <groupId>mysql</groupId>
	   <artifactId>mysql-connector-java</artifactId>
	   <version>5.1.6</version>
	</dependency>
	<dependency>
	 <groupId>org.eclipse.persistence</groupId>
	 <artifactId>javax.persistence</artifactId>
	 <version>2.1.1</version>
	</dependency>
	<dependency>
	 <groupId>org.projectlombok</groupId>
	 <artifactId>lombok</artifactId>
	 <version>1.16.12</version>
	</dependency>
	```
	Update ejb version 3.1 of maven-ejb-plugin< at ejbs/pom.xml

	`<ejbVersion>3.1</ejbVersion>`

	Update ejb-jar.xml at ejbs/resource/META-INF

	```xml
	<ejb-jar xmlns="http://java.sun.com/xml/ns/javaee"
	         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
	         http://java.sun.com/xml/ns/javaee/ejb-jar_3_0.xsd"
	         version="3.0">
	</ejb-jar>
	```

	Add persistence.xml at ejbs/resource/META-INF

	```xml
	<?xml version="1.0" encoding="UTF-8" ?>
	<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
	 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.1"
	 xsi:schemaLocation="
	 http://xmlns.jcp.org/xml/ns/persistence 
	 http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"> 
	  <persistence-unit name="hibernatePU" transaction-type="JTA">
	   	  
	   <jta-data-source>jdbc/empDB</jta-data-source>
	   <exclude-unlisted-classes>false</exclude-unlisted-classes>  
	   </persistence-unit>      	
	</persistence>
	```

3. Create Employee.java at ejbs/src/main/com/javaaround/model

	```java
	package com.javaaround.model;
	import javax.persistence.Entity;  
	import javax.persistence.Id;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import lombok.Data;

	@Entity
	@Data 
	public class Employee { 
		@Id 
		@GeneratedValue(strategy = GenerationType.IDENTITY)  
		private int id;  
		private String firstName;
		private Double salary;  
		
	}	
	```

	Create HelloEjb.java at ejbs/src/main/com/javaaround

	```java
	package com.javaaround;

	import javax.ejb.Singleton;
	import javax.ejb.LocalBean;
	import javax.ejb.Startup;
	import javax.annotation.PostConstruct;
	import javax.persistence.EntityManager;
	import javax.persistence.PersistenceContext;
	import com.javaaround.model.Employee;

	@Singleton
	@LocalBean
	@Startup
	public class HelloEjb{
		@PersistenceContext(unitName = "hibernatePU")
		EntityManager em;

		@PostConstruct
		public void main(){
			System.out.println("Hello ejb");
			Employee employee = new Employee( );
	        employee.setFirstName( "Md.Shamim Miah" );
	        employee.setSalary( 40000.00 );
	        //save into db	      
	        em.persist( employee );
		}
	}
	```

4. package app by following command

	`mvn clean package`	

3. Deploy App

	We want to deploy this app into glassfish.Add jdbc data resource(jdbc/empDB) at glassfish4\glassfish\domains\domain1\config

	```xml
	<jdbc-connection-pool datasource-classname="com.mysql.jdbc.jdbc2.optional.MysqlDataSource" 
	name="mysql_ladb_adminPool" 
	wrap-jdbc-objects="false" 
	connection-validation-method="auto-commit" 
	res-type="javax.sql.DataSource">
	  <property name="URL" value="jdbc:mysql://127.0.0.1:3306/test"></property>
	  <property name="driverClass" value="com.mysql.jdbc.Driver"></property>
	  <property name="Password" value=""></property>
	  <property name="portNumber" value="3306"></property>
	  <property name="databaseName" value="test"></property>
	  <property name="User" value="root"></property>
	  <property name="serverName" value="127.0.0.1"></property>
	</jdbc-connection-pool>
	<jdbc-resource pool-name="mysql_ladb_adminPool" jndi-name="jdbc/empDB"></jdbc-resource>
	```

	upload ear/target/ear-1.0.ear by glassfish administrator UI <br>


Complete project download linke <br>

[Download](https://www.dropbox.com/s/06m6h3o47f52aky/jpaEE2.zip?dl=0)