package de.auinger.training.spring_boot.enrollment;

import lombok.Setter;

import java.util.Collection;
import java.util.Optional;

public class EnrollmentService {

    @Setter
    private EnrollmentRepository enrollmentRepository;

    public Collection<Enrollment> getEnrollments() {
        return this.enrollmentRepository.findAll();
    }

    public Collection<Enrollment> getEnrollmentsForCourse(String courseId) {
        return this.enrollmentRepository.findAllByCourseId(courseId);
    }

    public boolean isEnrolled(long studentId, String courseId) {
        Optional<Enrollment> optEnrollment = this.enrollmentRepository.findByStudentIdAndCourseId(
                studentId, courseId);
        return optEnrollment.isPresent();
    }


    public Enrollment enroll(long studentId, String courseId) {
        return this.enrollmentRepository.save(
                new Enrollment(studentId, courseId)
        );
    }

}
