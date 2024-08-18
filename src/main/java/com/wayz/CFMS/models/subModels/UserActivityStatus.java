package com.wayz.CFMS.models.subModels;

/**
 * Статус активности сотрудников
 */
public enum UserActivityStatus {
    ACTIVE("Сотрудник активен"),
    OUT("Сотрудник в отпуске");

    private final String description;

    UserActivityStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
