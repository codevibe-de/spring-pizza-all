package de.auinger.training.spring_boot.course;

import de.auinger.training.spring_boot.error.NotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseService {

    private final Map<String, Course> coursesMap = new HashMap<>();

    public CourseService() {
    }

    public Course getCourse(String courseId) throws NotFoundException {
        return this.coursesMap.computeIfAbsent(
                courseId,
                key -> {
                    throw new NotFoundException(Course.class.getSimpleName(), courseId);
                });
    }

    public Collection<Course> getCourses() {
        return this.coursesMap.values();
    }

    void createCourses(Iterable<Course> courses) {
        coursesMap.clear();
        courses.forEach(c -> this.coursesMap.put(c.getId(), c));
    }
}
