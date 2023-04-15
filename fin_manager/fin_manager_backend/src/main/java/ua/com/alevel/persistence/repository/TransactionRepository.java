package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Transaction;

import java.sql.Timestamp;
import java.util.Collection;

@Repository
public interface TransactionRepository extends BaseRepository<Transaction> {

    @Query(value = "select * from transactions where (created >= ?1 and created <= ?2) and (from_account_id = ?3 or to_account_id = ?3)", nativeQuery = true)
    Collection<Transaction> findAllByAccountId(Timestamp startPeriod, Timestamp endPeriod, Long accountId);

    @Query(value = "select * from transactions where created between ?1 and ?2", nativeQuery = true)
    Collection<Transaction> findTest(Timestamp startPeriod, Timestamp endPeriod);
}
