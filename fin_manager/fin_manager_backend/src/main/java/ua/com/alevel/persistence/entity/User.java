package ua.com.alevel.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column
    private Integer age;

    @Column(name = "accounts_qty")
    private Integer accountsQty;

    @OneToMany
    private Set<Account> accounts;

    public User() {
        super();
    }
}
