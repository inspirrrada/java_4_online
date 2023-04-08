package ua.com.alevel.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionModel {

    private Long id;
    private BigDecimal sum;
    private AccountModel fromAccount;
    private AccountModel toAccount;

    @Override
    public String toString() {
        return "TransactionModel{" +
                "id=" + id +
                ", sum=" + sum +
                ", fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                '}';
    }
}
