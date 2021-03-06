package com.alka.springboot.topic;


import org.springframework.http.*;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class TopicController {
	
	public static final Logger logger=LoggerFactory.getLogger(TopicController.class);
	
	@Autowired
	public TopicService topicService;
	
	@Autowired
	public RestTemplate restTemplate;
	
	@RequestMapping("/topics")
	public List<Topic> getTopics(){
		return topicService.getTopics();
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/topics")
	@ResponseStatus(HttpStatus.CREATED)
	public Topic createTopics(@RequestBody Topic topic){
		 return topicService.createTopic(topic);
	}
	
	@RequestMapping("/topics/{id}")
	public ResponseEntity<Topic> getTopicsById(@PathVariable String id) throws topicNotFoundException{
		 if(!(topicService.getTopicsById(id).isPresent())){
			 logger.trace("trace");
			throw new topicNotFoundException("Topic with topicId : "+id+" not found in database");
		}
		 
		 return ResponseEntity.status(HttpStatus.FOUND).body(topicService.getTopicsById(id).get());
	}

	@RequestMapping(method=RequestMethod.PUT,value="/topics/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Topic updateTopic(@PathVariable String id , @RequestBody Topic topic) {
		return topicService.updateTopic(id,topic);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/topics/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTopic(@PathVariable String id) {
		topicService.deleteTopic(id);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/invalidport")
	public ResponseEntity<Object> invalidport(){
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid server port used...");
	}
	
	/*REst Template controller
	 * 
	 * 
	 */
	@RequestMapping("rest/topics")
	public String getRestTopics(){
		HttpHeaders header=new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity=new HttpEntity<String>(header);
		return restTemplate.exchange("http://localhost:8080/topics",HttpMethod.GET,entity,String.class).getBody();
	
		
	}
	
	
	@RequestMapping("rest/topics/{id}")
	public String getRestTopicById(@PathVariable String id) {
		HttpHeaders header=new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity=new HttpEntity<String>(header);
		return restTemplate.exchange("http://localhost:8080/topics/"+id, HttpMethod.GET, entity, String.class).getBody();
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="rest/topics/{id}")
	public Topic updateRestTopic(@PathVariable String id,@RequestBody Topic topic) {
		HttpHeaders header=new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Topic> entity=new HttpEntity<Topic>(topic,header);
		return restTemplate.exchange("http://localhost:8080/topics/"+id, HttpMethod.PUT, entity, Topic.class).getBody();
		
	}
	
	@RequestMapping("/getTopicsByPage")
	public Stream<Topic> getTopicsPaging(@RequestParam("page") int page,@RequestParam("size") int size){
		return topicService.getTopicsPaging(page, size).get();
		
	}
}
