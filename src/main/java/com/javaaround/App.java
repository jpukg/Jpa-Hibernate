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

	      //start transaction
	      em.getTransaction( ).begin( );
	      /*CriteriaBuilder cb = em.getCriteriaBuilder();

		  CriteriaQuery criteriaQuery = cb.createQuery();
		  Root employee = criteriaQuery.from(Employee.class);
		  Join empDetails = employee.join("empDetails");
		  criteriaQuery.multiselect(employee.get("firstName"), empDetails.get("city"));

    	  Query query = em.createQuery("Select e.firstName, d.city FROM Employee e join  e.empDetails d");
		  Object[] employeeObj = (Object[]) query.getSingleResult();
		  List<Object[]> result = query.getResultList();

		  for(Object obj: result){
			   Object[] myArray = (Object[]) obj;
			   System.out.println("id=" + myArray[0] + "name=" + myArray[1]);
		  }
	      */
	      Employee persistedEmployee = em.find(Employee.class,1,LockModeType.PESSIMISTIC_FORCE_INCREMENT);
	      /*
		  // using lock method
		  	
	      */
		  System.out.print("Employee name = " + persistedEmployee.getFirstName());

	      em.getTransaction( ).commit( );

	      //close resource
	      em.close( );
	      emf.close( );
    }
}
