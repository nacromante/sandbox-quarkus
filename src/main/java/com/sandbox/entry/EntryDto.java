package com.sandbox.entry;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import java.math.BigDecimal;
import java.time.Month;

@Data
public class EntryDto {

    @Max(value = 10)
    private String description;

    @DecimalMin(value = "0.10")
    private BigDecimal value;

    private FinancialOperation financialOperation;

    private Month month;
}
