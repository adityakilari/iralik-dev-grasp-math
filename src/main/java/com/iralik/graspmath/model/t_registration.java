package com.iralik.graspmath.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tregistration")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class t_registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String parentname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String studentname;
    @Column(nullable = false)
    private String grade;
    @Column(nullable = false)
    private String schoolname;

    @Column
    private String needs;

    @Column(nullable = false)
    private Long courseid;
    @Column(nullable = false)
    private float duration;

//    @OneToOne
//    @JoinColumn(name = "fkcourseid", referencedColumnName = "courseid")
//    private t_course course;
//
//
//    @OneToOne
//    @JoinColumn(name = "fkdurationid", referencedColumnName = "classid")
//    private t_duration classduration;


}
