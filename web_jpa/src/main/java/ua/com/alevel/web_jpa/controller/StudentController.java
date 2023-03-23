package ua.com.alevel.web_jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.web_jpa.service.CourseService;
import ua.com.alevel.web_jpa.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "student/student_all";
    }

    @GetMapping("/{id}/courses")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("courses", courseService.findAllByStudent(id));
        return "course/course_all";
    }
}
