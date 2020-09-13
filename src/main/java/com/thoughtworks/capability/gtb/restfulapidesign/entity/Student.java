package com.thoughtworks.capability.gtb.restfulapidesign.entity;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private Integer id;
    private String name;
    private Gender gender;
    private String note;
}
