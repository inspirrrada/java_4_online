package ua.com.alevel.type.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethodType {

    ADVANCE_PAYMENT("Advance payment to a bank account"),
    CASH_ON_DELIVERY("Cash on delivery");

    private final String value;
}
