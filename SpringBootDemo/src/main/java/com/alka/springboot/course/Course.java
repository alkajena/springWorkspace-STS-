package com.alka.springboot.course;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.context.annotation.Lazy;

import com.alka.springboot.topic.Topic;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Course implements Serializable{

	@Id
	private String courseId;
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)//in oneToMany eager is by default and manytoOne lazy is by default
	@JoinColumn(name="topic_topic_id")
	@JsonIgnore//to not show topic json while doing get call on course API
	private Topic topic;
	
	
	public String getcourseId() {
		return courseId;
	}
	public void setcourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
}
