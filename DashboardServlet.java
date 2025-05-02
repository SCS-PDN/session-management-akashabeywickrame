import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            List<Course> courses = new ArrayList<>();
            courses.add(new Course("C101", "Java Programming", "Dr. Smith"));
            courses.add(new Course("C102", "Web Development", "Ms. Johnson"));
            courses.add(new Course("C103", "Database Systems", "Mr. Brown"));

            request.setAttribute("courseList", courses);

            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("login.html");
        }
    }
}
