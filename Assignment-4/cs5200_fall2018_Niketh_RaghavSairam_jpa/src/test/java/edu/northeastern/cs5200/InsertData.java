package edu.northeastern.cs5200;

import java.util.ArrayList;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.northeastern.cs5200.daos.*;
import edu.northeastern.cs5200.models.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InsertData {
	
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
	public void emptyDatabase() {
		person_dao.emptyPersonTable();
		course_dao.emptyCourseTable();
		section_dao.emptySectionTable();
		enrollment_dao.emptyEnrollmentTable();
	}
	
	@Test
	public void testCreateFaculties() {
		faculty_dao.createFaculty(new Faculty("alan", "default", "Alan", "Turin", "123A", true));
		faculty_dao.createFaculty(new Faculty("ada", "default", "Ada", "Lovelace", "123B", true));
	}

	@Test
	public void testCreateStudents() {
		student_dao.createStudent(new Student("alice", "default", "Alice", "Wonderland", 202, 12000L));
		student_dao.createStudent(new Student("bob", "default", "Bob", "Hope", 2021, 23000L));
		student_dao.createStudent(new Student("charlie", "default", "Charlie", "Brown", 2019, 21000L));
		student_dao.createStudent(new Student("dan", "default", "dan", "Craig", 2019, 0L));
		student_dao.createStudent(new Student("edward", "default", "Edward", "Scissorhands", 2022, 11000L));
		student_dao.createStudent(new Student("frank", "default", "Frank", "Herbert", 2018, 0L));
		student_dao.createStudent(new Student("gregory", "default", "gregory", "Peck", 2023, 10000L));
	}

	@Test
	public void testCreateCourses() {
		
		Faculty alan = faculty_dao.findFacultyByUsername("alan");
		Faculty ada = faculty_dao.findFacultyByUsername("ada");

		course_dao.createCourse(new Course("CS1234", alan));
		course_dao.createCourse(new Course("CS2345", alan));
		course_dao.createCourse(new Course("CS3456", ada));
		course_dao.createCourse(new Course("CS4567", ada));

	}
	
	@Test
	public void testCreateSections() {
		
		ArrayList<Course> fetched_courses = new ArrayList<>();

		fetched_courses.add(course_dao.findCourseByLabel("CS1234"));
		fetched_courses.add(course_dao.findCourseByLabel("CS2345"));
		fetched_courses.add(course_dao.findCourseByLabel("CS3456"));
		
		section_dao.createSection(new Section("SEC4321", 50, fetched_courses.get(0)));
		section_dao.createSection(new Section("SEC5432", 50, fetched_courses.get(0)));
		section_dao.createSection(new Section("SEC6543", 50, fetched_courses.get(1)));
		section_dao.createSection(new Section("SEC7654", 50, fetched_courses.get(2)));
		
	}
	
	
	@Test
	public void testEnrollStudentInSection() {
		
		Section sec1 = section_dao.findSectionByTitle("SEC4321");
		Section sec2 = section_dao.findSectionByTitle("SEC5432");
		Section sec3 = section_dao.findSectionByTitle("SEC6543");

		Student alice = student_dao.findStudentByUsername("alice");
		Student bob = student_dao.findStudentByUsername("bob");
		Student charlie = student_dao.findStudentByUsername("charlie");
		
		enrollment_dao.createEnrollment(new Enrollment(alice, sec1));
		enrollment_dao.createEnrollment(new Enrollment(alice, sec2));
		enrollment_dao.createEnrollment(new Enrollment(bob, sec2));
		enrollment_dao.createEnrollment(new Enrollment(charlie, sec3));

	}
	
	
}
