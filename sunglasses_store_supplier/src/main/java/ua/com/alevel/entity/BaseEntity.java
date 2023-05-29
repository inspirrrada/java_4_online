package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @Column(nullable = false)
    private OffsetDateTime created;

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @Column(nullable = false)
    private OffsetDateTime updated;

    private Boolean visible;

    public BaseEntity() {
        this.created = OffsetDateTime.now();
        this.updated = OffsetDateTime.now();
        this.visible = true;
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updated = OffsetDateTime.now();
    }
}
