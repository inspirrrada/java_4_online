package ua.com.alevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.alevel.entity.BaseEntity;

public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Long> {
}
