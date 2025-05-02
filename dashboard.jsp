<%@ page import="java.util.*, Course" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <style>
        table {
            border-collapse: collapse;
            width: 70%;
            margin: 20px auto;
        }

        th, td {
            border: 1px solid #000;
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        h2, h3 {
            text-align: center;
        }
    </style>
</head>
<body>
<form action="logout" method="post" style="text-align:right; margin: 10px 50px;">
    <input type="submit" value="Logout"/>
</form>
<%
    String message = request.getParameter("msg");
    if (message != null) {
%>
<p style="color:green; text-align:center;"><%= message %>
</p>
<%
    }
%>

<h2>Available Courses</h2>
<table>
    <tr>
        <th>Course ID</th>
        <th>Course Name</th>
        <th>Instructor</th>
        <th>Action</th>
    </tr>
    <%
        List<Course> courses = (List<Course>) request.getAttribute("courseList");
        for (Course c : courses) {
    %>
    <tr>
        <td><%= c.getCourseId() %>
        </td>
        <td><%= c.getCourseName() %>
        </td>
        <td><%= c.getInstructor() %>
        </td>
        <td><a href="enroll?courseId=<%= c.getCourseId() %>">Enroll</a></td>
    </tr>
    <% } %>
</table>

<h3>Your Enrolled Courses</h3>
<%
    List<Course> enrolled = (List<Course>) session.getAttribute("enrolledCourses");
    if (enrolled != null && !enrolled.isEmpty()) {
%>
<table>
    <tr>
        <th>Course ID</th>
        <th>Course Name</th>
        <th>Instructor</th>
    </tr>
    <%
        for (Course ec : enrolled) {
    %>
    <tr>
        <td><%= ec.getCourseId() %>
        </td>
        <td><%= ec.getCourseName() %>
        </td>
        <td><%= ec.getInstructor() %>
        </td>
    </tr>
    <% } %>
</table>
<%
} else {
%>
<p style="text-align:center;">You have not enrolled in any courses yet.</p>
<% } %>

</body>
</html>
