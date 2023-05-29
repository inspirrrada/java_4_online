package ua.com.alevel.type.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusType {

    CREATED("Advance payment to a bank account"),
    ACCEPTED_FOR_PROCESSING("Accepted for processing"),
    PAYED("Payed"),
    SHIPPED("Shipped"),
    CANCELLED("Cancelled");

    private final String value;
}
