package de.auinger.training.spring_boot.course;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@ToString
public class Course {

    @NonNull
    private final String id;

    @NonNull
    private final String title;

    private Integer maxEnrollments;

}
