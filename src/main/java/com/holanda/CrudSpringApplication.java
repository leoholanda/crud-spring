package com.holanda;

import com.holanda.model.Course;
import com.holanda.model.Lesson;
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

			Lesson lesson1 = new Lesson();
			lesson1.setName("Protótipo");
			lesson1.setYoutubeUrl("watch?v=1");
			lesson1.setCourse(angular);
			angular.getLessons().add(lesson1);

			Lesson lesson2 = new Lesson();
			lesson2.setName("Angular");
			lesson2.setYoutubeUrl("watch?v=2");
			lesson2.setCourse(angular);
			angular.getLessons().add(lesson2);

			courses.add(angular);

			Course spring = new Course();
			spring.setName("Spring Boot");
			spring.setCategory("back-end");
			spring.getLessons()
					.add(new Lesson(null,
							"Introdução",
							"watch?v=1",
							spring));
			courses.add(spring);

			repository.saveAll(courses);

			System.out.println("Started");
		};
	}
}
