package com.javaaround.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmployeeDetails.class)
public abstract class EmployeeDetails_ {

	public static volatile SingularAttribute<EmployeeDetails, String> fatherName;
	public static volatile SingularAttribute<EmployeeDetails, String> city;
	public static volatile SingularAttribute<EmployeeDetails, String> street;
	public static volatile SingularAttribute<EmployeeDetails, String> postcode;
	public static volatile SingularAttribute<EmployeeDetails, Employee> emp;
	public static volatile SingularAttribute<EmployeeDetails, Integer> id;

}

