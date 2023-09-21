package com.holanda.service;

import com.holanda.dto.CourseDTO;
import com.holanda.dto.mapper.CourseMapper;
import com.holanda.exception.RecordDuplicateException;
import com.holanda.exception.RecordNotFoundException;
import com.holanda.model.Course;
import com.holanda.repository.CoursesRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CourseService {
    private final CoursesRepository coursesRepository;
    private final CourseMapper courseMapper;

    private static final String STATUS_ATIVO = "Ativo";

    public CourseService(CoursesRepository coursesRepository, CourseMapper courseMapper) {
        this.coursesRepository = coursesRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> list() {
        return coursesRepository.findAll().stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO findById(@NotNull @Positive Long id) {
        return coursesRepository.findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO courseDTO) {
        if(!coursesRepository.findByNameAndCategoryAndStatus(courseDTO.name(), courseDTO.category(), STATUS_ATIVO).isEmpty()) {
            throw new RecordDuplicateException(String.format("Nome do curso %s já existe", courseDTO.name()));
        }
        return courseMapper.toDTO(coursesRepository.save(courseMapper.toEntity(courseDTO)));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid CourseDTO courseDTO) {
        return coursesRepository.findById(id)
                .map(recordFound -> {
                    var course = courseMapper.toEntity(courseDTO);
                    recordFound.setName(courseDTO.name());
                    recordFound.setCategory(courseDTO.category());
//                    recordFound.setLessons(course.getLessons());
                    recordFound.getLessons().clear();
                    course.getLessons().forEach(recordFound.getLessons()::add);
                    return courseMapper.toDTO(coursesRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        coursesRepository.delete(coursesRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }

}
