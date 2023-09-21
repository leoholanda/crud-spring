package com.holanda.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.holanda.model.Lesson;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CourseDTO(
        @JsonProperty("_id") Long id,
        @NotBlank @Length(min = 5, max = 100) String name,
        @NotNull @Length(max = 10) @Pattern(regexp = "back-end|front-end") String category,
        List<LessonDTO> lessons) {
}
