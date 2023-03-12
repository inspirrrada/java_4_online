package ua.com.alevel.web_jpa.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column()
    private int age;

    @Column(nullable = false)
    private String email;

    @Column(name = "photo_url")
    private String photoUrl;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;

    public Student() {
        super();
    }
}
