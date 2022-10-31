package de.auinger.training.spring_boot.enrollment;

import de.auinger.training.spring_boot.course.Course;
import de.auinger.training.spring_boot.course.CourseService;
import de.auinger.training.spring_boot.student.Student;
import de.auinger.training.spring_boot.student.StudentService;
import lombok.Setter;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/enroll")
public class EnrollmentServlet extends HttpServlet {

    @Setter
    private StudentService studentService;

    @Setter
    private CourseService courseService;

    @Setter
    private EnrollmentService enrollmentService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.setContentType("text/plain");

            long studentId = Long.parseLong(request.getParameter("sid"));
            String courseId = request.getParameter("cid");

            // call getters to make sure entities exist -- methods throw exception if missing
            studentService.getStudent(studentId);
            courseService.getCourse(courseId);

            // perform enrollment
            enrollmentService.enroll(studentId, courseId);

            response.getWriter().println("Enrollment done");
        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
            System.err.println(e);
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();

        // since this servlet has been created by Tomcat, not by Spring it hasn't been subject
        // to any CDI processing -- which is what we are doing now by hand
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        ctx.getAutowireCapableBeanFactory().autowireBeanProperties(
                this,
                AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE,
                true);
    }
}
