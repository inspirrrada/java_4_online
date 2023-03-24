package ua.com.alevel.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transactions_register")
public class TransactionRegister extends BaseEntity {

    @ManyToOne //TODO ???
    private Transaction transaction;

    @ManyToOne //TODO ???
    private TransactionCategory transactionCategory;

    @ManyToOne //TODO ???
    private Account account;

    @Column
    private String description;

    public TransactionRegister() {
        super();
    }
}
