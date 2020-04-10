package com.alakesoftware.springhibernatedemo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="employee")
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="company")
	private String company;
	
	@Column(name="date_of_join")
	@Temporal(TemporalType.DATE)
	private Date dateOfJoin;

	public Employee() {
		
	}

	

	public Employee(String firstName, String lastName, String company, Date dateOfJoin) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
		this.dateOfJoin = dateOfJoin;
	}



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getId() {
		return id;
	}



	public Date getDateOfJoin() {
		return dateOfJoin;
	}



	public void setDateOfJoin(Date dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}



	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", company=" + company
				+ ", dateOfJoin=" + dateOfJoin + "]";
	}

	
	
	
	
}
