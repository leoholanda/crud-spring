package com.holanda.controller;

import com.holanda.model.Course;
import com.holanda.repository.CoursesRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CoursesCotroller {

    private CoursesRepository repository;

    @GetMapping
    public @ResponseBody List<Course> list() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(repository.save(course));
    }
}
