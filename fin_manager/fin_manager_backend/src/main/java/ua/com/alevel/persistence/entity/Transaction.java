package ua.com.alevel.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    @Column(precision = 7, scale = 2)
    private BigDecimal sum;

    @ManyToOne
    private Account fromAccount;

    @ManyToOne
    private Account toAccount;

    public Transaction() {
        super();
    }
}
