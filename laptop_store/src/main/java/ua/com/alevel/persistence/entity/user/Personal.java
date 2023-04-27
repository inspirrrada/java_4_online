package ua.com.alevel.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import ua.com.alevel.persistence.type.RoleType;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @Column(name = "birth_day")
    private OffsetDateTime birthDay;

    public Personal() {
        super();
        setRoleType(RoleType.ROLE_PERSONAL);
    }
}
