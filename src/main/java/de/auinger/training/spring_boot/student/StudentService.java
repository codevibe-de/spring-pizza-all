package de.auinger.training.spring_boot.student;

import de.auinger.training.spring_boot.course.Course;
import de.auinger.training.spring_boot.error.NotFoundException;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StudentService {

    public StudentService() {
        System.out.println("Constructing StudentService");
    }

    public List<Student> getStudents() {
        return List.of(
                new Student(1L, "Fred", "Feuerstein", "Steintal"),
                new Student(2L, "Homer", "Simpson", "Springfield")
        );
    }

    public Student getStudent(long studentId) throws NotFoundException {
        List<Student> filteredStudents = getStudents(s -> s.getId() == studentId);
        if (filteredStudents.size() == 1) {
            return filteredStudents.get(0);
        } else if (filteredStudents.size() == 0) {
            throw new NotFoundException(Student.class.getSimpleName(), Long.toString(studentId));
        } else {
            throw new IllegalStateException("More than one Student for given id found");
        }
    }

    public List<Student> getStudents(Predicate<Student> predicate) {
        return getStudents().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
