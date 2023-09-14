package com.holanda.repository;

import com.holanda.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursesRepository extends JpaRepository<Course, Long> {

    List<Course> findByNameAndCategoryAndStatus(String name, String category, String status);

}
