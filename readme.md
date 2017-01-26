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
	//end transaction
	em.getTransaction( ).commit( );

	//close resource
	em.close( );
	emf.close( );

	```	

Run app by following command

`mvn clean package`	


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

Note : Dont use provider specific parameter.use jpa parameter so that your code keeps JPA implementation independent

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


### create-target & drop-target ### 

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
	  Update Employee.java
		```java
		@Id 
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_generator")
		@SequenceGenerator(name="emp_generator", sequenceName = "emp_seq", initialValue=1, allocationSize=50)  
		private int id; 
		```
3. TABLE : Provider uses a database table to get next sequence
4. AUTO : Provider selects the above generation strategy based on the used dialect.It is not recommended to use production.Only recommended to use development




