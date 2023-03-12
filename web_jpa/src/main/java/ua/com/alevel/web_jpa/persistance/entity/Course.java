package ua.com.alevel.web_jpa.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.web_jpa.persistance.type.CourseType;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_type")
    private CourseType courseType;

    @Column(name = "duration_months")
    private int durationInMonths;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "students_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Student> students;

    public Course() {
        super();
    }
}
