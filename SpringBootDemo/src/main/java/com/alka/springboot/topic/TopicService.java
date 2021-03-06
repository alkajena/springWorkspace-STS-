package com.alka.springboot.topic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
	
	@Autowired(required=true)
	TopicRepository topicRepo;

	public List<Topic> getTopics() {
		List<Topic> topics=new ArrayList<>();
		for(Topic itr:topicRepo.findAll()) {
			topics.add(itr);
		}
		return topics;
	}

	public Optional<Topic> getTopicsById(String id) {
		return topicRepo.findById(id);
	}

	public Topic createTopic(Topic topic) {
		
		return topicRepo.save(topic);
	}

	public Topic updateTopic(String id, Topic topic) {
		return topicRepo.save(topic);
	}

	public void deleteTopic(String id) {
		topicRepo.deleteById(id);
		
	}
	
	
	public Page<Topic> getTopicsPaging(int page,int size) {
		Pageable pageable=PageRequest.of(page, size,Sort.by("name").and(Sort.by("topicId").descending()));
		 return topicRepo.findAll(pageable);
	}

}
