package de.auinger.training.spring_boot.student;

import java.util.function.Predicate;

public class StudentFilter implements Predicate<Student> {

    @Override
    public boolean test(Student student) {
        return false;
    }
}
