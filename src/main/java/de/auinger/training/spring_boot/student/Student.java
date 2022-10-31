package de.auinger.training.spring_boot.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Student {

    private long id;

    private String firstName;

    private String lastName;

    private String city;

}
