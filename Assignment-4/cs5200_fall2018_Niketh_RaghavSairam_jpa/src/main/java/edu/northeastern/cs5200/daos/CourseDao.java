package edu.northeastern.cs5200.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repositories.*;

@Component
public class CourseDao {

    @Autowired
	FacultyRepository faculty_repository;
    
	@Autowired
	CourseRepository course_repository;
    
	private static CourseDao instance = null;

	private CourseDao() {}

	public static CourseDao getInstance() {
		if (instance == null) {
			instance = new CourseDao();
		}
		return instance;
	}
	
	public void createCourse(Course course) {
		if (course_repository.findCourseByLabel(course.getLabel()) == null) {
			course_repository.save(course);
		}
	}
    
	public List<Course> findAllCourses() {
		
		return (List<Course>) course_repository.findAll();
	}
	
	public List<Course> findCourseForAuthor(Faculty faculty) {
		
		return (List<Course>) course_repository.findCourseForAuthor(faculty);
	}
	
	public void setAuthorForCourse(Faculty faculty, Course course) {
		course.setAuthor(faculty);
		course_repository.save(course);
	}
	
	public Course findCourseByLabel(String label) {
		return course_repository.findCourseByLabel(label);
	}
	
	public void emptyCourseTable() {
		course_repository.deleteAll();
	}
	
	
}
