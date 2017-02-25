package com.javaaround;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import com.javaaround.model.ContactEmployee;
import com.javaaround.model.Employee;
import com.javaaround.model.Department;
import com.javaaround.model.EmployeeDetails;
import com.javaaround.model.EmployeeInfo;
import com.javaaround.model.Address;
import com.javaaround.model.Project;
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
	      CriteriaBuilder cb = em.getCriteriaBuilder();
		  CriteriaQuery<Employee> criteriaQuery = cb.createQuery(Employee.class);
		  Root<Employee> root = criteriaQuery.from(Employee.class);
		  criteriaQuery.select(root);

		  TypedQuery<Employee> query = em.createQuery(criteriaQuery);
	      List<Employee> empList = query.getResultList();
	      for(Employee employee : empList)
	  		System.out.println(employee.getFirstName());
	      em.getTransaction( ).commit( );

	      //close resource
	      em.close( );
	      emf.close( );
    }
}
