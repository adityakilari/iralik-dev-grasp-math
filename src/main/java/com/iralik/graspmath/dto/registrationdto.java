package com.iralik.graspmath.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class registrationdto {

    private String parentname;
    private String email;
    private String phone;
    private String studentname;
    private String grade;
    private String needs;
    private String schoolname;
    private Long courseid;
    private float duration;
}
