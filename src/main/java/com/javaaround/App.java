package com.javaaround;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Join;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.Date;
import com.javaaround.model.ContactEmployee;
import com.javaaround.model.Employee;
import com.javaaround.model.Department;
import com.javaaround.model.EmployeeDetails;
import com.javaaround.model.EmployeeInfo;
import com.javaaround.model.Employee_;
import com.javaaround.model.Address;
import com.javaaround.model.Project;
import com.javaaround.util.Gender;
import java.io.FileInputStream;
import org.apache.commons.io.IOUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.persistence.Tuple;
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
	      em.getTransaction( ).begin( );
	      
	      Employee emp1 = em.find(Employee.class,1);
	     
	      em.getTransaction( ).commit( );
	      em.close( );
	      emf.close( );
	      EntityManagerFactory emf1 = Persistence.createEntityManagerFactory( "hibernatePU" );
	      EntityManager em1 = emf1.createEntityManager();
	      em1.getTransaction( ).begin( );
	      
	      Employee emp2 = em1.find(Employee.class,1);
	      
	     
	      em1.getTransaction( ).commit( );

	      //close resource
	      em1.close( );
	      emf1.close( );
	      
    }
}
