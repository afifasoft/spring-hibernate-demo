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

import com.alakesoftware.springhibernatedemo.entity.Student;

@Component
public class CreateStudentDemo implements CommandLineRunner{
	
	@PersistenceUnit
	EntityManagerFactory emf;
	
	@Autowired
	DateUtils dateUtils;
	
	

	public  void run(String...args) throws Exception{
		System.out.println("saveStudent");
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		try {
			// create a student object
			String dateOfBirth = "12/01/2000";
			
			Date dob =  (Date) dateUtils.parseDate(dateOfBirth);
			System.out.println("Creating new student object...");
			Student tempStudent = new Student("Rose", "Mike", "mike@gmail.com", dob);
			
			// start a transaction
			session.beginTransaction();
			// save the student object
			System.out.println("Saving the student ...");
			session.save(tempStudent);
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done");
			saveStudent();
			
			getStudent();
			getStudents();
			updateStudents();
			deleteStudent();
			
		} catch (ParseException e) {
			// TODO: handle exception
		}
		
		finally {
			//sessionFactory.close();
		}

	}
	
	// save the data into the database
	public void saveStudent() throws ParseException {
		System.out.println("saveStudent() called");
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		try {
			String dateOfBirth = "12/01/2000";
			
			Date dob =  (Date) dateUtils.parseDate(dateOfBirth);
			Student s1 = new Student( "John","Doe","john@gmail.com",dob);
			Student s2 = new Student("Mary","Public","mary@gmail.com", dob);
			Student s3 = new Student("Bonita","Applebum","bonita@gmail.com", dob);
		
			session.beginTransaction();
			
			session.save(s1);
			session.save(s2);
			session.save(s3);
			
			session.getTransaction().commit();
			session.close();
		
		}
		
		finally {
			//sessionFactory.close();
		}
	}
	
	// Read Data from the database
	public void getStudent() throws ParseException {
			
		System.out.println("getStudent()");
			
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
			
			
			try {
				String dateOfBirth = "12/01/2000";
				
				Date dob =  (Date) dateUtils.parseDate(dateOfBirth);
				Student s1 = new Student("Faris", "Salman", "faris@gmail.com", dob);
				session.beginTransaction();
				session.save(s1);
				session.getTransaction().commit();
				System.out.println("Saved student. Generated Id: "+ s1.getId());
				
				
				session.beginTransaction();
				Student  myStudent = session.get(Student.class, s1.getId());
				
				System.out.println("Get complete: "+ myStudent);
				
				session.getTransaction().commit();
			}
			finally {
				session.close();
				//sessionFactory.close();
			}
			
			
	}
	
	public void getStudents() {
		
		System.out.println("getStudents() : list");
		
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		
		try {
			
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").list();
			
			
			//display the students
			
			displayStudents(theStudents);
			
			// query Students: lastName='Doe'
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").list();
			
			System.out.println("\n\nStudents who have last name of Doe");
			displayStudents(theStudents);
			
			// query Students: lastName='Doe' OR firstName='Rose'
			theStudents = 
					session.createQuery("from Student s where"
							+ " s.lastName='Doe' OR s.firstName='Rose'").list();
			System.out.println("\n\nStudents who have last name of Doe OR first name Rose");
			displayStudents(theStudents);
			
			
			// query students where email LIKE '%gmail.com'
			theStudents =
					session.createQuery("from Student s where s.email"
							+ " LIKE '%gmail.com'").list();
			System.out.println("\n\nStudents whose email end with gmail.com");
			displayStudents(theStudents);
			// commit transcation
			
			session.getTransaction().commit();
			
		}
		finally {
			session.close();
			//sessionFactory.close();
		}
	}
	
	public void updateStudents() {
		System.out.println("\n\n updateStudents()");
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		try
		{
			session.beginTransaction();
			int studentId = 1;
			System.out.println("\n Getting student with id: "+ studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("\n\n Updating student...");
			myStudent.setFirstName("Scooby");
			
			session.getTransaction().commit();
			session.close();
			
			Session session1 = sessionFactory.openSession();
			session1.beginTransaction();
			
			// update email for all students
			System.out.println("Update email for all students");
			
			session1.createQuery("update Student set email='foo@gmail.com'")
				.executeUpdate();
			
			session1.getTransaction().commit();
			session1.close();
			System.out.println("Done!");
		}
		finally {
			//sessionFactory.close();
		}
	}
	
	public void deleteStudent() {
		
		System.out.println("\n\n deleteStudent()");
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
	
		try
		{
			session.beginTransaction();
			int studentId = 1;
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("\n\n Deleting student: "+myStudent);
			session.delete(myStudent);
			
			// delete student id=2
			session.createQuery("delete from Student where id=2")
				.executeUpdate();
			
			session.getTransaction().commit();
			session.close();
		}
		finally {
		//	sessionFactory.close();
		}
	}

	private void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
