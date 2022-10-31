package de.auinger.training.spring_boot.enrollment;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.Optional;

public class EnrollmentDao implements EnrollmentRepository, RowMapper<Enrollment> {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public EnrollmentDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public Collection<Enrollment> findAll() {
        return this.jdbcTemplate.query(
                "SELECT e.* FROM enrollments e",
                new Object[]{},
                new int[]{},
                this
        );
    }

    @Override
    public Collection<Enrollment> findAllByCourseId(String courseId) {
        return this.jdbcTemplate.query(
                "SELECT e.* FROM enrollments e WHERE e.crs_id = ?",
                new Object[]{courseId},
                new int[]{Types.VARCHAR},
                this
        );
    }

    @Override
    public Optional<Enrollment> findByStudentIdAndCourseId(long studentId, String courseId) {
        return Optional.empty();
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        this.jdbcTemplate.update(
                "INSERT INTO enrollments (std_id, crs_id) VALUES (?, ?)",
                new Object[]{enrollment.getStudentId(), enrollment.getCourseId()}
        );
        return enrollment;
    }

    // --- interface RowMapper ---

    @Override
    public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Enrollment(
                rs.getLong("std_id"),
                rs.getString("crs_id")
        );
    }
}
