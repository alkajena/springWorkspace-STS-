package com.alka.springboot.course;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alka.springboot.topic.Topic;

@Repository
public interface CourseRepository extends CrudRepository<Course,String>{
	 
	public List<Course> findByTopicTopicId(String id);
	
}
