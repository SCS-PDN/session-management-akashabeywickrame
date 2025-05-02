import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String courseId = request.getParameter("courseId");
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null && courseId != null) {
            List<Course> allCourses = new ArrayList<>();
            allCourses.add(new Course("C101", "Java Programming", "Dr. Smith"));
            allCourses.add(new Course("C102", "Web Development", "Ms. Johnson"));
            allCourses.add(new Course("C103", "Database Systems", "Mr. Brown"));

            Course selectedCourse = null;
            for (Course c : allCourses) {
                if (c.getCourseId().equals(courseId)) {
                    selectedCourse = c;
                    break;
                }
            }

            if (selectedCourse != null) {
                List<Course> enrolledCourses = (List<Course>) session.getAttribute("enrolledCourses");
                if (enrolledCourses == null) {
                    enrolledCourses = new ArrayList<>();
                }

                boolean alreadyEnrolled = false;
                for (Course c : enrolledCourses) {
                    if (c.getCourseId().equals(courseId)) {
                        alreadyEnrolled = true;
                        break;
                    }
                }

                if (!alreadyEnrolled) {
                    enrolledCourses.add(selectedCourse);
                    session.setAttribute("enrolledCourses", enrolledCourses);
                    response.sendRedirect("dashboard?msg=Enrolled+in+" + selectedCourse.getCourseName());
                } else {
                    response.sendRedirect("dashboard?msg=Already+enrolled+in+" + selectedCourse.getCourseName());
                }
            } else {
                response.sendRedirect("dashboard?msg=Invalid+Course+ID");
            }
        } else {
            response.sendRedirect("login.html");
        }
    }
}