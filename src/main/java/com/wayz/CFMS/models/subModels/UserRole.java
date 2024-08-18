package com.wayz.CFMS.models.subModels;

/**
 * Роли пользователя
 */
public enum UserRole {
    ADMINISTRATOR("Администратор"),
    EMPLOYEE("Сотрудник");

    private final String description;

    UserRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
