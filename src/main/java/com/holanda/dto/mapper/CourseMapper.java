package com.holanda.dto.mapper;

import com.holanda.dto.CourseDTO;
import com.holanda.dto.LessonDTO;
import com.holanda.model.Course;
import com.holanda.model.Lesson;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if(course == null) {
            return null;
        }
        List<LessonDTO> lessonDTOList = course.getLessons()
                .stream()
                .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
                .collect(Collectors.toList());

        return new CourseDTO(course.getId(),
                course.getName(),
                course.getCategory(),
                lessonDTOList);
    }

    public Course toEntity(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }

        Course course = new Course();
        if(courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(courseDTO.category());

        List<Lesson> lessons = courseDTO.lessons()
                .stream()
                .map(lessonDTO -> {
                    var lesson = new Lesson();
                    lesson.setId(lessonDTO.id());
                    lesson.setName(lessonDTO.name());
                    lesson.setYoutubeUrl(lessonDTO.youtubeUrl());
                    lesson.setCourse(course);
                    return lesson;
                }).collect(Collectors.toList());

        course.setLessons(lessons);
        return course;
    }
}
