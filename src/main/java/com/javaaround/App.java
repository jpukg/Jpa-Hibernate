package com.javaaround;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import com.javaaround.model.Employee;
import com.javaaround.model.Department;
import com.javaaround.model.Address;
import com.javaaround.util.Gender;
import java.io.FileInputStream;
import org.apache.commons.io.IOUtils;
import java.time.LocalDateTime;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	  //Persistence.generateSchema("hibernatePU", null);
	      EntityManagerFactory emf = Persistence.createEntityManagerFactory( "hibernatePU" );
	  
	      EntityManager em = emf.createEntityManager();

	      //start transaction
	      em.getTransaction( ).begin( );

	      Employee employee = new Employee( ); 
	      Department dpt1 = new Department();
	      dpt1.setName("IT");

	      Department dpt2 = new Department();
	      dpt2.setName("Audit");

	      Address adr1 = new Address();
	      adr1.setStreet("parijat");
	      adr1.setCity("gazipure");
	      adr1.setPostcode("1200");

	      Address adr2 = new Address();
	      adr2.setStreet("bishasbettka");
	      adr2.setCity("tangail");
	      adr2.setPostcode("1900");


	      //employee.setId( 1204 );
	      employee.setFirstName( "Md.Shamim Miah" );
	      employee.setSalary( 40000.00 );
	      employee.setJoinDate(new Date());
	      employee.setGender(Gender.M);
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
	  	  employee.setIsActive(Boolean.TRUE);
	  	  employee.setCreateDate(LocalDateTime.now().toLocalDate());

	  	 /* employee.getDepartments().add(dpt1);
	  	  employee.getDepartments().add(dpt2);*/

	  	  employee.getAddress().add(adr1);
	  	  employee.getAddress().add(adr2);
          //save into db	      
	      em.persist( employee );
	     /* em.persist( dpt1 );
	  	  em.persist( dpt2 );*/
	      //end transaction
	      em.getTransaction( ).commit( );

	      //close resource
	      em.close( );
	      emf.close( );
    }
}
