package de.auinger.training.spring_boot.course;

import java.util.Collection;

public class CourseDemoDataWriter {

    private CourseService courseService;

    public CourseDemoDataWriter() {
        // this constructor is empty "by bad design" - so we must use setCourseService() in CDI
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public void setCourses(Collection<Course> courses) {
        courseService.createCourses(courses);
    }
}
