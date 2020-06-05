package com.ms.assignment.service3.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.assignment.service2.entity.Person;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/ms3")
@Api(value = "/ms3", produces = "application/json")
public class Service3Controller {
	private static final Logger LOG = Logger.getLogger(Service3Controller.class.getName());
	@Value("${server.port}")
	String port;
	
	@PostMapping(value = "/concat", consumes = "application/json", produces = "application/json")
	public String concate(@RequestBody Person person) {
		LOG.log(Level.INFO, "Service3, getting req for : "+ person);
		return person.getName()+" "+person.getSurname();
	}
	
	
	@GetMapping("/hello")
	public ResponseEntity<String> sayHello() {
		LOG.log(Level.INFO, "Service3, Running on port : "+ port);
		return new ResponseEntity<String>("Hello", HttpStatus.OK);
	}
}
