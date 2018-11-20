package edu.northeastern.cs5200.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repositories.*;

@Component
public class StudentDao {
	
    @Autowired
	StudentRepository student_repository;
    
	private static StudentDao instance = null;

	private StudentDao() {}

	public static StudentDao getInstance() {
		if (instance == null) {
			instance = new StudentDao();
		}
		return instance;
	}
    
	
	
	public void createStudent(Student student) {
		String username = student.getUsername();
		String password = student.getPassword();
		if (student_repository.findStudentByCredentials(username, password) == null) {
			student_repository.save(student);
		}
	}
    
	public List<Student> findAllStudents() {
		return (List<Student>) student_repository.findAll();
	}
	
	public Student findStudentByUsername(String username) {
		return student_repository.findStudentByUsername(username);
	}
	
	public Student findStudentByCredentials(String username, String password) {
		return student_repository.findStudentByCredentials(username, password);
	}
	
    
    
}
