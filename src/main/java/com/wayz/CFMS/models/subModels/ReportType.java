package com.wayz.CFMS.models.subModels;

/**
 * Тип отчета
 */
public enum ReportType {

    DAILY("Дневной"),
    WEEKLY("Недельный"),
    MONTHLY("Месячный"),
    QUARTERLY("Квартальный"),
    ANNUAL("Годовой"),
    CUSTOM("Другое");

    /**
     * Описание расхода
     */
    private final String description;


    ReportType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
