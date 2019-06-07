package com.ascend.example.demospringboot;

import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ascend.example.demospringboot.model.User;
import com.ascend.example.demospringboot.repository.UserRepository;

@SpringBootApplication
public class DemoSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringbootApplication.class, args);
	}
//	@Bean
//	CommandLineRunner init(UserRepository repository) {
//		return args -> {
//			repository.deleteAll();
//			LongStream.range(1, 11)
//					.mapToObj(i -> new User(i, "name- " + i, "email-" + i + "@email.com", "address-"))
//					.map(v -> repository.save(v))
//					.forEach(System.out::println);
//		};
//
//	}
}
