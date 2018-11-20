package edu.northeastern.cs5200;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.northeastern.cs5200.daos.*;
import edu.northeastern.cs5200.models.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidateData {
	
	@Autowired
	PersonDao person_dao;
	
	@Autowired
	FacultyDao faculty_dao;
	
	@Autowired
	StudentDao student_dao;
	
	@Autowired
	CourseDao course_dao;
	
	@Autowired
	SectionDao section_dao;
	
	@Autowired
	EnrollmentDao enrollment_dao;

	
	@Test
	public void validatePersons() {
		System.out.println("Start person validation");
		System.out.println(person_dao.findAllPersons().size());
		System.out.println("End validation");
		
	}
	
	@Test
	public void validateFaculties() {

		System.out.println("Start faculty validation");
		System.out.println(faculty_dao.findAllFaculties().size());
		System.out.println("End validation");
		
	}
	
	@Test
	public void validateStudents() {

		System.out.println("Start student validation");
		System.out.println(student_dao.findAllStudents().size());
		System.out.println("End validation");
	}
	
	
	@Test
	public void validateCourses() {

		System.out.println("Start course validation");
		System.out.println(course_dao.findAllCourses().size());
		System.out.println("End validation");
		
	}
	
	@Test
	public void validateSections() {

		System.out.println("Start section validation");
		System.out.println(section_dao.findAllSections().size());
		System.out.println("End validation");
		
	}
	
	@Test
	public void validateCourseAuthorship() {

		System.out.println("Start course author validation");
		for(Faculty faculty: faculty_dao.findAllFaculties()) {
			System.out.println(faculty.getFirstName() + " " + faculty.getAuthoredCourses().size());
		}
		System.out.println("End validation");
	}
	
	@Test
	public void validateSectionPerCourse() {

		System.out.println("Start course section validation");
		for(Course course: course_dao.findAllCourses()) {
			System.out.println(course.getLabel() + " " + course.getCourseSections().size());
		}
		System.out.println("End validation");
	}
	
	@Test
	public void validateSectionEnrollments() {

		System.out.println("Start section enrollment validation");
		for(Section section: section_dao.findAllSections()) {
			System.out.println(section.getTitle() + " " + enrollment_dao.findStudentsInSection(section).size());
		}
		System.out.println("End validation");
	}
		
	@Test
	public void validateStudentEnrollments() {
		
		System.out.println("Start student enrollment validation");
		for(Student student: student_dao.findAllStudents()) {
			System.out.println(student.getFirstName() + " " + enrollment_dao.findSectionsForStudent(student).size());
		}
		System.out.println("End validation");
	}
	
	@Test
	public void validateSectionSeats() {

		System.out.println("Start section seats validation");
		for(Section section: section_dao.findAllSections()) {
			System.out.println(section.getTitle() + " " + section.getSeats());
		}
		System.out.println("End validation");
	}
		

}
