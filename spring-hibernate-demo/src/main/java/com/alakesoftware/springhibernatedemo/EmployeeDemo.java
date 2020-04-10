package com.alakesoftware.springhibernatedemo;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alakesoftware.springhibernatedemo.entity.Employee;

@Component
public class EmployeeDemo implements CommandLineRunner {

	@PersistenceUnit
	EntityManagerFactory emf;
	
	@Autowired
	DateUtils dateUtils;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n\nEmployeeDemo");
		
		
		saveEmployee();
		getEmployee();
		getEmployees();
		deleteEmployee();
		
	}
	
	public void saveEmployee() throws ParseException {
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		
		try
		{
			session.beginTransaction();
			Date joinDate = dateUtils.parseDate("10/01/2019");
			Employee emp = new Employee("Zulfikar", "Ahamed", "SAB", joinDate);
			Employee emp1 = new Employee( "Abdul", "Atif", "Cloud Chain", joinDate);

			Employee emp2 = new Employee( "Abughosh", "Dubai", "SAB", joinDate);
			session.save(emp);
			session.save(emp1);
			session.save(emp2);
			
			session.getTransaction().commit();
		}
		finally {
			session.close();
		//	sessionFactory.close();
		}
		
		System.out.println("\n saveEmployee()");
	}
	
	public void getEmployee() {
		System.out.println("\n getEmployee()");
		
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			int empId = 1;
			Employee emp = session.get(Employee.class, empId);
			
			System.out.println("\n\n Employee: "+ emp);
			session.getTransaction().commit();
			
			
		}
		finally {
			session.close();
		}
		
		
	}
	
	public void getEmployees() {
		System.out.println("\n getEmployees()");
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			List<Employee> empList = session.createQuery("from Employee where company='SAB'").list();
			
			displayEmployee(empList);
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
	}
	
	public void deleteEmployee() {
		System.out.println("\n deleteEmployee()");
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		try
		{
			session.beginTransaction();
			session.createQuery("delete from Employee where id = 1").executeUpdate();
			
			session.getTransaction().commit();
			
		}
		finally {
			session.close();
			sessionFactory.close();
		}
	}
	
	public void displayEmployee(List<Employee> empList) {
		System.out.println("displayEmployee()");
		for (Employee tempEmp : empList) {
			System.out.println(tempEmp);
		}
	}

}
