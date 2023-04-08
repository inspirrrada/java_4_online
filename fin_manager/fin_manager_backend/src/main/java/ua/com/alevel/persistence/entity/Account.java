package ua.com.alevel.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(precision = 7, scale = 2)
    private BigDecimal balance;

    @ManyToOne
    private User user;

    @Column(name = "active_status")
    private Boolean isActive;

    public Account() {
        super();
        this.balance = new BigDecimal("00.00");
    }
}
