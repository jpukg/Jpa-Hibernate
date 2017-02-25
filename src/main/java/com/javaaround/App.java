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
		  CriteriaQuery<Tuple> criteriaQuery = cb.createTupleQuery();
		  Root employee = criteriaQuery.from(Employee.class);
		  criteriaQuery.multiselect(employee.get("firstName").alias("first"), employee.get("id").alias("eid"));

		  Query query = em.createQuery(criteriaQuery);
		  List<Tuple> result = query.getResultList();
		  //specfic index element
		  String firstName = (String) result.get(0).get("first"); 
		  Integer id = (Integer) result.get(0).get("eid");
		  System.out.println("id=" + id + "name=" + firstName);
		  //all element
	      for(Tuple tuple : result){
	      	String fName = (String) tuple.get("first"); //oth value
		    Integer  eid = (Integer) tuple.get("eid"); // 1th value
    		System.out.println("id=" + eid + "name=" + fName);
	      }
	      em.getTransaction( ).commit( );

	      //close resource
	      em.close( );
	      emf.close( );
    }
}
