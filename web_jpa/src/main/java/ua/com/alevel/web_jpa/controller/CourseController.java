package ua.com.alevel.web_jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.web_jpa.persistance.entity.Course;
import ua.com.alevel.web_jpa.service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "course/course_all";
    }

    @GetMapping("/new")
    public String redirectToNewCourse(Model model) {
        model.addAttribute("course", new Course());
        return "course/course_new";
    }

    @PostMapping("/new")
    public String newCourse(@ModelAttribute Course course) {
        courseService.create(course);
        return "redirect:/courses";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.findAll());
        return "course_course_all";
    }
}
