package com.javaaround;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import com.javaaround.model.ContactEmployee;
import com.javaaround.model.Employee;
import com.javaaround.model.Department;
import com.javaaround.model.Address;
import com.javaaround.util.Gender;
import java.io.FileInputStream;
import org.apache.commons.io.IOUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.*;
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
	      Set<String> contacts = new HashSet<String>();
	      contacts.add("20111112550");
	      contacts.add("20111555550");
	      Employee employee = new Employee( ); 
	      employee.setFirstName("Md.Shamim");
	      employee.setContacts(contacts);
	      
	      /*  Department dpt1 = new Department();
	      dpt1.setName("IT");

	      Department dpt2 = new Department();
	      dpt2.setName("Audit");*/

	      //employee.setId( 1204 );
	     //  employee.setFirstName( "Md.Shamim Miah" );
	     // // employee.setSalary( 40000.00 );
	     //  employee.setJoinDate(new Date());
	     //  employee.setGender(Gender.M);
	     //  employee.setRemarks("luren ipsomluren ipsomluren ipsomluren " 
	     //  	+ "ipsomluren ipsomluren ipsomluren ipsomluren ipsomluren "
	     //  	+ "ipsomluren ipsomluren ipsomluren ipsomluren ipsomluren "
	     //  	+ "ipsomluren ipsomluren ipsomluren ipsomluren ipsomluren "
	     //  	+ "ipsomluren ipsomluren ipsomluren ipsomluren ipsomluren "
	     //  	+ "ipsomluren ipsomluren ipsomluren ipsomluren ipsomluren "
	     //  	+ "ipsomluren ipsomluren ipsomluren ipsomluren ipsomluren "
	     //  	);

	     //  //
	     //  FileInputStream fis;
	     //  try{
	     //  	fis = new FileInputStream("E:/shamim/image/shamim.jpg");
	     //  	byte[] bytes = IOUtils.toByteArray(fis);
	     //  	employee.setPicture(bytes);
	  	  // }catch(Exception e){

	  	  // }
	  	 
	  	 /* employee.getDepartments().add(dpt1);
	  	  employee.getDepartments().add(dpt2);*/

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
