package com.example.part4.ex06;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 스프링에서의 RestTemplate 과  바동기  MVC의 결합
 * 
 * Ex01App with Spring Boot
 * 
 */

@SpringBootApplication
public class Ex01SpringBootApp {
	
	@RestController
	public class MyController {
		
		@GetMapping("/rest")
		public String rest() {
			return "Hello World";
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Ex01SpringBootApp.class, args);
	}

//	@Autowired
//	MyService myService;
//	
//	@Bean
//	ApplicationRunner applicationRunner() {
//		return new ApplicationRunner() {
//			@Override
//			public void run(ApplicationArguments args) throws Exception {
//				Future<String> f = myService.service();
//				
//				while(f.isDone() == false) {
//					System.out.println("[Thread-" + Thread.currentThread().getId() +"] 긴작업1");
//					Thread.sleep(1000);
//				}
//				System.out.println("[Thread-" + Thread.currentThread().getId() +"] " + f.get());
//				System.out.println("[Thread-" + Thread.currentThread().getId() +"] Exit");	
//			}
//		};
//	}
//	
//	@Component
//	public static class MyService {
//		@Async
//		public Future<String> service() throws InterruptedException {
//			System.out.println("[Thread-" + Thread.currentThread().getId() +"] 긴작업2");
//			Thread.sleep(5000);
//			return new AsyncResult<String>("Hello");
//		}
//	}
}