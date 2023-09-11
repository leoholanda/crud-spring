package com.holanda.service;

import com.holanda.model.Course;
import com.holanda.repository.CoursesRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Validated
@Service
public class CourseService {
    private final CoursesRepository coursesRepository;

    public CourseService(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    public List<Course> list() {
        return coursesRepository.findAll();
    }

    public Course create(Course course) {
        return coursesRepository.save(course);
    }

    public Optional<Course> update(@NotNull @Positive Long id, @Valid Course course) {
        return coursesRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    return coursesRepository.save(recordFound);
                });
    }

    public Optional<Course> findById(@NotNull @Positive Long id) {
        return coursesRepository.findById(id);
    }

    public boolean delete(@NotNull @Positive Long id) {
        return coursesRepository.findById(id)
                .map(recordFound -> {
                    coursesRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
