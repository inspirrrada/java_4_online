package ua.com.alevel.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    @Column(precision = 7, scale = 2)
    private BigDecimal sum;

    @ManyToOne //TODO?
    private User fromUser;

    @ManyToOne //TODO?
    private User toUser;

    public Transaction() {
        super();
    }
}
