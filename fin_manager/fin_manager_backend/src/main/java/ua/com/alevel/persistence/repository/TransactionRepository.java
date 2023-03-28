package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Transaction;

@Repository
public interface TransactionRepository extends BaseRepository<Transaction> {
}
