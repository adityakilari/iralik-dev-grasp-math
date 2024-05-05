package com.iralik.graspmath.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tcourse")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class t_course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseid;
    private String coursename;

}
