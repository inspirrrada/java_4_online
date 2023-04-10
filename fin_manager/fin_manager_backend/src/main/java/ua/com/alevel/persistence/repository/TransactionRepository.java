package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Transaction;

import java.util.Collection;

@Repository
public interface TransactionRepository extends BaseRepository<Transaction> {

//    Collection<Transaction> findAllByFromAccountIdOrToAccountId(Long accountId);

    @Query(value = "select * from transactions where from_account_id = ?1 or to_account_id = ?1", nativeQuery = true)
    Collection<Transaction> findAllByAccountId(Long accountId);

}
