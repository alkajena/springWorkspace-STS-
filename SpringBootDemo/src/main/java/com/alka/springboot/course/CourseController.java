package com.alka.springboot.course;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alka.springboot.topic.Topic;

@RestController
public class CourseController {
	
	@Autowired
	public CourseService courseService;
	
	@RequestMapping("/topics/{id}/Courses")
	public List<Course> getCourses(@PathVariable String id){
		return courseService.getCourses(id);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="topics/{topicId}/Courses")
	@ResponseStatus(HttpStatus.CREATED)
	public void createCourses(@RequestBody Course Course,@PathVariable String topicId){
		Topic topic=new Topic();
		topic.setTopicId(topicId);
		Course.setTopic(topic);
		courseService.createCourse(Course);
	}
	
	@RequestMapping("topics/{topicId}/Courses/{id}")
	public Optional<Course> getCoursesById(@PathVariable String id, @PathVariable String topicId){
		return courseService.getCoursesById(id);
	}

	@RequestMapping(method=RequestMethod.PUT,value="topics/{topicId}/Courses/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateCourse(@PathVariable String topicId , @RequestBody Course course) {
		Topic topic=new Topic();
		topic.setTopicId(topicId);
		course.setTopic(topic);
		courseService.updateCourse(course);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="topics/{topicId}/Courses/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCourse(@PathVariable String id) {
		courseService.deleteCourse(id);
	}
}
