package com.holanda;

import com.holanda.model.Course;
import com.holanda.repository.CoursesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}


	@Bean
	CommandLineRunner initDatabase(CoursesRepository repository) {
		return args -> {
			repository.deleteAll();

			Course course = new Course();
			course.setName("Angular com Spring");
			course.setCategory("front-end");

			repository.save(course);
		};
	}
}
