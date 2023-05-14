package ua.com.alevel.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import ua.com.alevel.persistence.type.RoleType;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@DiscriminatorValue("PERSONAL")
public class Personal extends User {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Transient
    private String fullName;

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @Column(name = "birth_day")
    private OffsetDateTime birthDay;

    @Transient
    private Integer age;

    @Column(name="phone_number")
    private Integer phoneNumber;

    @Column
    private String region;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private String building;

    @Column
    private String apartment;

    public Personal() {
        super();
        setRoleType(RoleType.ROLE_PERSONAL);
    }
}
