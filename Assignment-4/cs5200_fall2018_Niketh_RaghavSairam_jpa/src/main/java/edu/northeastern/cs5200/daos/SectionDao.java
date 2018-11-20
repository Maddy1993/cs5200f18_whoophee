package edu.northeastern.cs5200.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repositories.*;

@Component
public class SectionDao {

	@Autowired
	SectionRepository section_repository;

	@Autowired
	CourseRepository course_repository;

	private static SectionDao instance = null;

	private SectionDao() {
	}

	public static SectionDao getInstance() {
		if (instance == null) {
			instance = new SectionDao();
		}
		return instance;
	}

	public void createSection(Section section) {
		if (section_repository.findSectionByTitle(section.getTitle()) == null) {
			section_repository.save(section);
		}
	}

	public void addSectionToCourse(Section section, Course course) {
		section.setCourse(course);
		section_repository.save(section);
	}

	public List<Section> findAllSections() {

		return (List<Section>) section_repository.findAll();
	}

	public List<Section> findSectionForCourse(Course course) {

		return (List<Section>) section_repository.findSectionForCourse(course);
	}

	public Section findSectionByTitle(String title) {
		return section_repository.findSectionByTitle(title);
	}

	public void emptySectionTable() {
		section_repository.deleteAll();
	}

}
