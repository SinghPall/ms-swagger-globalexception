package com.ms.assignment.service1.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ms.assignment.service1.entity.Person;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/ms1")
@Api(value="/ms1", produces ="application/json")
public class Service1Controller {
	private static final Logger LOG = Logger.getLogger(Service1Controller.class.getName());
	@Autowired
	RestTemplate restTemplate;

	@Value("${server.port}")
	private int port;

	@GetMapping("/hello")
	public String sayHello() {
		LOG.log(Level.INFO, "GET :: Service1, Running on port : "+ port);
		return "Up";
	}

	@ApiOperation(value="Aggregate response from Ms2 and MS3",response = Person.class)
	@ApiResponses(value={
			@ApiResponse(code = 200, message = "Person details are fine", response = Person.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 415, message = "Media not supported")
	})
	@PostMapping(value = "/person", consumes = "application/json", produces = "application/json")
	public String aggregate(@Valid @RequestBody Person person) {
		LOG.info("Aggregate response from ms2 and ms3");

		String ms2Url = "http://localhost:8081/ms2/hello";
		LOG.info("Calling ms2" + ms2Url);
		String response1 = (String) restTemplate.exchange(ms2Url, HttpMethod.GET, null, String.class).getBody();
		LOG.info("The response received by ms2 is " + response1);

		String ms3Url = "http://localhost:8082/ms3/concat"; 
		LOG.info("Calling ms3" + ms3Url);
		String response2 = (String)restTemplate.postForObject(ms3Url, person, String.class);
		LOG.info("The response received by ms3 is " + response2);

		return response1 +" "+ response2;
	}
}
