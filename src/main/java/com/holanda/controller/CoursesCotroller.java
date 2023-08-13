package com.holanda.controller;

import com.holanda.model.Course;
import com.holanda.repository.CoursesRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CoursesCotroller {

    private CoursesRepository repository;

    @GetMapping
    public List<Course> list() {
        return repository.findAll();
    }
}
