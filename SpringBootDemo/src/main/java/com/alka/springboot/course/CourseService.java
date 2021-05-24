package com.alka.springboot.course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
	
	@Autowired
	CourseRepository courseRepo;

	public List<Course> getCourses(String id) {
		
		return courseRepo.findByTopicTopicId(id);
	}

	public Optional<Course> getCoursesById(String id) {
		return courseRepo.findById(id);
	}

	public void createCourse(Course Course) {
		courseRepo.save(Course);
	}

	public void updateCourse(Course Course) {
		courseRepo.save(Course);
	}

	public void deleteCourse(String id) {
		courseRepo.deleteById(id);
		
	}

}
