package com.javaaround.listener;
import javax.persistence.PrePersist;
import javax.persistence.PostPersist;
import javax.persistence.PreUpdate;
import javax.persistence.PostUpdate;
import com.javaaround.model.Employee;
public class EmployeeListener {
	@PrePersist
    public void validate(Object obj) {
       System.out.println("validating employee......" + ((Employee) obj).getId ());
    }

	@PostPersist
	public void afterSave(Object obj){
		System.out.println("saved successfully.thank you");
	}

	@PreUpdate
	public void validateUpdate(Object obj) {
	   System.out.println("validating  updating......");
	}

	@PostUpdate
	public void afterUpdate(Object obj){
		System.out.println("Update successfully.thank you");
	}
}