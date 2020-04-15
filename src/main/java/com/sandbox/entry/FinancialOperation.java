package com.sandbox.entry;

public enum FinancialOperation {

    EXPENSE("Despesa"), RECIPE("Receita");

    private String description;

    FinancialOperation(String description) {
        this.description = description;
    }
}
