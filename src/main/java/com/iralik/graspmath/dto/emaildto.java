package com.iralik.graspmath.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class emaildto {
    private String subject;
    private String body;
    private String recipient;
    private String sender;
}
