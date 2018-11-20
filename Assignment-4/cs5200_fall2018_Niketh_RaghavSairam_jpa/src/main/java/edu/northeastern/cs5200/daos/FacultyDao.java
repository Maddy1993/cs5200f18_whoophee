package edu.northeastern.cs5200.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repositories.*;

@Component
public class FacultyDao {
	
    @Autowired
	FacultyRepository faculty_repository;
    
	private static FacultyDao instance = null;

	private FacultyDao() {}

	public static FacultyDao getInstance() {
		if (instance == null) {
			instance = new FacultyDao();
		}
		return instance;
	}

	
	
	public void createFaculty(Faculty faculty) {
		if (faculty_repository.findFacultyByCredentials(faculty.getUsername(), faculty.getPassword()) == null) {
			faculty_repository.save(faculty);
		}
	}
	
	public List<Faculty> findAllFaculties() {
		
		return (List<Faculty>) faculty_repository.findAll();
	}
	
	public Faculty findFacultyByUsername(String username) {
		return faculty_repository.findFacultyByUsername(username);
	}

}
