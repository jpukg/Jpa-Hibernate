package com.javaaround;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import com.javaaround.model.ContactEmployee;
import com.javaaround.model.Employee;
import com.javaaround.model.Department;
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

	      Employee shamim = new Employee( ); 
	      shamim.setFirstName("Md.Shamim");

	      EmployeeDetails shamimDetails = new EmployeeDetails();
	      shamimDetails.setStreet("244");
	      shamimDetails.setCity("Tangail");
	      shamimDetails.setPostcode("1902");
	      shamimDetails.setFatherName("Md.Shamsul Alam");
	      
	      shamim.setEmpDetails(shamimDetails);
	      /*Employee rafiq = new Employee( ); 
	      rafiq.setFirstName("Md.Rafig");

	      Project consProject = new Project();
	      consProject.setName("Construction");

	      Project trainingProject = new Project();
	      trainingProject.setName("Training");

	      //shamim have engaged many project
	      List<Project> projects = new ArrayList<Project>();
	      projects.add(consProject);
	      projects.add(trainingProject);

	      shamim.setProjects(projects);
	      rafiq.setProjects(projects);
          //save into db	      
	      em.persist(shamim);*/
	      em.persist(shamim);


	      em.getTransaction( ).commit( );

	      //close resource
	      em.close( );
	      emf.close( );
    }
}
