package com.alakesoftware.springhibernatedemo.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.alakesoftware.springhibernatedemo.entity.Student;


public class AppConfig {
	SessionFactory factory = new Configuration().addAnnotatedClass(Student.class)
			.buildSessionFactory();
	
	Session session = factory.getCurrentSession();

}
