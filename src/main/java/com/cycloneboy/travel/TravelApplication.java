package com.cycloneboy.travel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableScheduling
@EnableSwagger2
@RestController
@MapperScan("com.cycloneboy.travel.entity.*")
@SpringBootApplication
public class TravelApplication extends SpringBootServletInitializer {

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(TravelApplication.class);
//	}

	@RequestMapping("/")
	public String home(){
		return "This is success on  Travel Application !";
	}

	public static void main(String[] args) {
		SpringApplication.run(TravelApplication.class, args);
	}
}
