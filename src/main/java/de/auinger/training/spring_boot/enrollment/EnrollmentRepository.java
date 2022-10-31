package de.auinger.training.spring_boot.enrollment;

import java.util.Collection;
import java.util.Optional;

public interface EnrollmentRepository {

    Collection<Enrollment> findAllByCourseId(String courseId);

    Optional<Enrollment> findByStudentIdAndCourseId(long studentId, String courseId);

    Enrollment save(Enrollment enrollment);

    Collection<Enrollment> findAll();
}
