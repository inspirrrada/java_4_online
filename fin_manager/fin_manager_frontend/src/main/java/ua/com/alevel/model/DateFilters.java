package ua.com.alevel.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class DateFilters {

    private String fromDate;
    private String toDate;
}
