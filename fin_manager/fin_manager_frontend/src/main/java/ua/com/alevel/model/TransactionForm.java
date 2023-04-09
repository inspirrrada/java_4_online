package ua.com.alevel.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionForm {

    private BigDecimal sum;
    private String fromAccountNumber;
    private String toAccountNumber;
}
