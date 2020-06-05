package com.ms.assignment.service2.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/ms2")
@Api(value = "/ms2")
public class Service2Controller {
	private static final Logger LOG = Logger.getLogger(Service2Controller.class.getName());
	@Value("${server.port}")
	int port;
	
	@GetMapping("/hello")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> sayHello() {
		LOG.log(Level.INFO, "GET :: Service2, Running on port : "+ port);
		return new ResponseEntity<String>("Hello", HttpStatus.OK);
	}
}
