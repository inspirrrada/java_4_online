package ua.com.alevel.persistence.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentsType {

    ADVANCE_PAYMENT("Advance payment to a bank account"),
    CASH_ON_DELIVERY("Cash on delivery");

    private final String value;
}
