package com.ms.assignment.service1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import brave.sampler.Sampler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ConfigurationMs1 {
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	/* If you are exporting span data to Zipkin or Spring Cloud Stream,
	 * there is also an AlwaysSampler that exports everything and a PercentageBasedSampler 
	 * that samples a fixed fraction of spans.
	 */
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

	@Bean
	public Docket produceApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ms.assignment.service1.controller"))
				.paths(paths())
				.build();
	}
	// Describe your apis
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Microservice-1")
				.description("This page lists all the rest apis for MS1 App.")
				.version("1.0-SNAPSHOT")
				.build();
	}
	// Only select apis that matches the given Predicates.Match all paths except /error
	private Predicate<String> paths() {
		return Predicates.and(
				PathSelectors.regex("/ms1.*"),
				Predicates.not(PathSelectors.regex("/error.*")));
	}
}
