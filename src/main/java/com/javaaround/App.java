package com.javaaround;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import com.javaaround.model.Employee;
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
          //save into db	      
	      em.persist( employee );
	      //end transaction
	      em.getTransaction( ).commit( );

	      //close resource
	      em.close( );
	      emf.close( );
    }
}
