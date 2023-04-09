package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;

import java.util.Collection;

@Repository
public interface AccountRepository extends BaseRepository<Account> {

//    Collection<Account> findAllByUserId(Long id);

//    @Query(value = "from Account a join a.user as u where u.id =: userId")
//    Collection<Account> findAllByUser(@Param("userId") Long id);

//    @Query(value = "from Account a where a.accountNumber =: accountNumber")
//    Account findByAccountNumber(@Param("accountNumber") String accountNumber);

//    @Query(value = "select * from accounts a where a.account_number = 1?", nativeQuery = true)
//    Account findByAccountNumber(String accountNumber);

//    @Query(value = "select * from accounts a where a.account_number = 1?", nativeQuery = true)
    Account findByAccountNumber(String accountNumber);

    @Query(value = "from Account where user.id =: userId")
    Collection<Account> findAllByUser(@Param("userId") Long id);

//    @Query(value = "from Account where id =: accountId")
//    User findByAccountId(@Param("accountId") Long id);

    @Query(value = "select user_id from accounts where id = ?1", nativeQuery = true)
    Long findUserIdByAccountId(Long id);

}
