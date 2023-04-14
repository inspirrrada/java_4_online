package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;

@Repository
public interface UserRepository extends BaseRepository<User> {

}
