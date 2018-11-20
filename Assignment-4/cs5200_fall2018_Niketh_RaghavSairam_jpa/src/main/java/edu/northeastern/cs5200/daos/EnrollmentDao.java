package edu.northeastern.cs5200.daos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repositories.*;

@Component
public class EnrollmentDao {

    @Autowired
    SectionRepository section_repository;
    
	@Autowired
	StudentRepository student_repository;
	
	@Autowired
	EnrollmentRepository enrollment_repository;
	
    
	private static EnrollmentDao instance = null;

	private EnrollmentDao() {}

	public static EnrollmentDao getInstance() {
		if (instance == null) {
			instance = new EnrollmentDao();
		}
		return instance;
	}
	
	public boolean createEnrollment(Enrollment enrollment) {
		
		Section section = enrollment.getSection();
		
		if (enrollment_repository.findEnrollment(enrollment.getStudent(), section) == null) {
			int seats = section.getSeats();
			if (seats > 0) {
				seats--;
				section.setSeats(seats);
				enrollment_repository.save(enrollment);
				section_repository.save(section);
				return true;
			}
		}
		return false;
		
	}

	public List<Student> findStudentsInSection(Section section) {
		
		List<Student> students = new ArrayList<Student>();
		List<Enrollment> enrolls = enrollment_repository.findEnrollmentBySection(section);
		for(Enrollment enrollment: enrolls) {
			Student student = enrollment.getStudent();
			students.add(student);
		}
		
		return students;
	}
	
	public List<Section> findSectionsForStudent(Student student) {
		
		List<Section> sections = new ArrayList<Section>();
		List<Enrollment> enrolls = enrollment_repository.findEnrollmentByStudent(student);
		for(Enrollment enrollment: enrolls) {
			Section section = enrollment.getSection();
			sections.add(section);
		}
		
		return sections;
	}

	public void removeEnrollment(Student student, Section section) {
		
		Enrollment enrollment = enrollment_repository.findEnrollment(student, section);
		if (enrollment != null) {
			int seats = section.getSeats();
			enrollment_repository.delete(enrollment);
			seats++;
			section.setSeats(seats);
			section_repository.save(section);
		}

	}

	public void emptyEnrollmentTable() {
		enrollment_repository.deleteAll();
	}
	
}
