package com.sandbox.entry;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

@Entity
@Data
public class Entry {


    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    @Digits(integer = 3,fraction = 2)
    private BigDecimal value;

    private FinancialOperation financialOperation;

    private Boolean active = true;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    @CreationTimestamp
    private LocalDateTime creationDate;

    private Month month;
}
