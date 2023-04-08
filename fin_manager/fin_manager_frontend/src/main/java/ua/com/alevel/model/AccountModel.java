package ua.com.alevel.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountModel {

    private Long id;
    private String accountNumber;
    private BigDecimal balance;

    @Override
    public String toString() {
        return "AccountModel{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
