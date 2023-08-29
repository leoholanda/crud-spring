package com.holanda;

import com.holanda.model.Course;
import com.holanda.repository.CoursesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}


	@Bean
	CommandLineRunner initDatabase(CoursesRepository repository) {
		return args -> {
			repository.deleteAll();

			List<Course> courses = new ArrayList<>();

			Course angular = new Course();
			angular.setName("Angular");
			angular.setCategory("front-end");
			courses.add(angular);

			Course spring = new Course();
			spring.setName("Spring Boot");
			spring.setCategory("back-end");
			courses.add(spring);

			repository.saveAll(courses);

			System.out.println("Started");
		};
	}
}
