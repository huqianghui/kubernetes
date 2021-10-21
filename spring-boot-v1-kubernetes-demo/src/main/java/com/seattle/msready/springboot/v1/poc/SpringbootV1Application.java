package com.seattle.msready.springboot.v1.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SpringbootV1Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringbootV1Application.class, args);
	}

	@GetMapping("/version")
	public String getVersion(){
		return "v1";
	}
}
 