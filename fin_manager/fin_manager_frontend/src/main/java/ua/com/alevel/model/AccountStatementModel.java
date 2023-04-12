package ua.com.alevel.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class AccountStatementModel {

    private OffsetDateTime date;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private TransactionType transactionCategory;
    private String relatedUserFullName;
    private BigDecimal sum;
}
