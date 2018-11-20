package edu.northeastern.cs5200.models;

import java.util.List;

import javax.persistence.*;

@Entity
public class Faculty extends Person {
	
	private String office;
	private Boolean tenure;
	@OneToMany(mappedBy="author", orphanRemoval=true)
	private List<Course> courses;
	
	
	public Faculty() {}
	
	public Faculty (String username, String password, String first_name, String last_name, String office, Boolean tenure) {
		super(username, password, first_name, last_name);
		this.office = office;
		this.tenure = tenure;
	}
	
	public Faculty (String username, String password, String first_name, String last_name) {
		super(username, password, first_name, last_name);
		this.office = "Unknown";
		this.tenure = false;
	}
	
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public Boolean getTenure() {
		return tenure;
	}
	public void setTenure(Boolean tenure) {
		this.tenure = tenure;
	}
	public List<Course> getAuthoredCourses() {
		return courses;
	}
	public void setAuthoredCourses(List<Course> courses) {
		this.courses = courses;
	}
	public void addCourse(Course course) {
		this.courses.add(course);
	}
	public void removeCourse(Course course) {
		this.courses.remove(course);
	}
}

