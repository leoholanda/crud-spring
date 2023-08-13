package com.holanda.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TB_COURSES")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private String category;

}
