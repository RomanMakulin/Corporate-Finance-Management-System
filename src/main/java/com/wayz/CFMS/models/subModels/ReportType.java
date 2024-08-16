package com.wayz.CFMS.models.subModels;

public enum ReportType {

    DAILY("Daily"),
    WEEKLY("Weekly"),
    MONTHLY("Monthly"),
    QUARTERLY("Quarterly"),
    ANNUAL("Annual"),
    CUSTOM("Custom");

    private final String description;


    ReportType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
