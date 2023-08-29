package com.holanda.service;

import com.holanda.model.Course;
import com.holanda.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CoursesRepository coursesRepository;

    public Course save(Course course) {
        return coursesRepository.save(course);
    }

    public Optional<Course> findById(Long id) {
        return coursesRepository.findById(id);
    }

    public void delete(Long id) {
        coursesRepository.deleteById(id);
    }
}
