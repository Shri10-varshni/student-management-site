
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    public record Student(int id, String name, String department) {}

    public List<Student> getStudents() {
        return List.of(
            new Student(101, "Aarav", "Computer Science"),
            new Student(102, "Diya", "Electronics"),
            new Student(103, "Kabir", "Information Technology")
        );
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home() {
        StringBuilder html = new StringBuilder();

        html.append("""
            <!DOCTYPE html>
            <html>
            <head>
                <title>Student Management Site</title>
            </head>
            <body>
                <h2>STUDENT MANAGEMENT SITE</h2>
                <table border="1" cellpadding="5">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Department</th>
                    </tr>
            """);

        for (Student student : getStudents()) {
            html.append("<tr>")
                .append("<td>").append(student.id()).append("</td>")
                .append("<td>").append(student.name()).append("</td>")
                .append("<td>").append(student.department()).append("</td>")
                .append("</tr>");
        }

        html.append("""
                </table>
            """);

        html.append("<p>Total students: ")
            .append(getStudents().size())
            .append("</p>");

        html.append("""
            </body>
            </html>
            """);

        return html.toString();
    }
}