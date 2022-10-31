package de.auinger.training.spring_boot.enrollment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Enrollment {

    private long studentId;

    private String courseId;

}
