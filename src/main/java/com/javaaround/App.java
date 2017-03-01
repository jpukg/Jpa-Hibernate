package com.javaaround;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

	      //start transaction
	      em.getTransaction( ).begin( );
	      CriteriaBuilder cb = em.getCriteriaBuilder();
	      Metamodel m = em.getMetamodel();

		  CriteriaQuery criteriaQuery = cb.createQuery();
		  EntityType<Employee> employee_ = m.entity(Employee.class);
		  Root<Employee> employee = criteriaQuery.from(employee_);
		  criteriaQuery.select(employee);
		 /* Root<Employee> employee = criteriaQuery.from(Employee.class);
		  EntityType<Employee> employee_ = employee.getModel();*/
		  criteriaQuery.where(cb.equal(employee.<Integer>get(Employee_.id), 1));

		  Query query = em.createQuery(criteriaQuery);
		  List<Employee> result = query.getResultList();
		  
	      for(Employee emp : result)
    		System.out.println(emp.getFirstName());
	      
	      em.getTransaction( ).commit( );

	      //close resource
	      em.close( );
	      emf.close( );
    }
}
