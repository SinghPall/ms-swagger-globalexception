package com.ms.assignment.service2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
public class ConfigurationMs2 {

	/* If you are exporting span data to Zipkin or Spring Cloud Stream,
	 * there is also an AlwaysSampler that exports everything and a PercentageBasedSampler 
	 * that samples a fixed fraction of spans.
	 */
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
	// swagger UI : http://localhost:portOfUrApp/swagger-ui.html
	@Bean
	public Docket produceApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ms.assignment.service2.controller"))
				.paths(paths())
				.build();
	}
	// Describe your apis
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Microservice-2")
				.description("This page lists all the rest apis for MS-2 App.")
				.version("1.0-SNAPSHOT")
				.build();
	}
	// Only select apis that matches the given Predicates.Match all paths except /error
	private Predicate<String> paths() {
		return Predicates.and(
				PathSelectors.regex("/ms2.*"),
				Predicates.not(PathSelectors.regex("/error.*")));
	}
}
