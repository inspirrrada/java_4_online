package ua.com.alevel.persistence.type.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusType {

    CREATED("Created"),
    ACCEPTED_FOR_PROCESSING("Accepted for processing"),
    PAYED("Payed"),
    SHIPPED("Shipped"),
    CANCELLED("Cancelled");

    private final String value;
}
