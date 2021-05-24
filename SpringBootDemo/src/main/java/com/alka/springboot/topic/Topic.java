package com.alka.springboot.topic;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.alka.springboot.course.Course;

@Entity
public class Topic {

	@Id
	@GeneratedValue(generator="system-uuid")//As primary key and id is mainly is of integer type we are 
															 //using uuid to take primary key data type as String
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String topicId;
	private String name;
	
	/*@OneToMany(mappedBy = "topic")
	private List<Course> course;
	
	
	
	public List<Course> getCourse() {
		return course;
	}
	public void setCourse(List<Course> course) {
		this.course = course;
	}*/
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
