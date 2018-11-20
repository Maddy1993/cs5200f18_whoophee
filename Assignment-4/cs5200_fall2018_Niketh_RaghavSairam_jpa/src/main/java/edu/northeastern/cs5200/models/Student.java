package edu.northeastern.cs5200.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Student extends Person {
	
	private int grad_year;
	private Long scholarship;
	@OneToMany(mappedBy="student", orphanRemoval=true)
	private List<Enrollment> enrollments;
	
	public Student() {}
	
	public Student(String username, String password, String first_name, String last_name, int grad_year, Long scholarship) {
		super(username, password, first_name, last_name);
		this.grad_year = grad_year;
		this.scholarship = scholarship;
	}
	
	public int getGradYear() {
		return grad_year;
	}
	public void setGradYear(int grad_year) {
		this.grad_year = grad_year;
	}
	public Long getScholarship() {
		return scholarship;
	}
	public void setScholarship(Long scholarship) {
		this.scholarship = scholarship;
	}

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

}

