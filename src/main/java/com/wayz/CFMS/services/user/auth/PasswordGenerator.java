package com.wayz.CFMS.services.user.auth;

/**
 * Интерфейс генерации пароля
 */
public interface PasswordGenerator {
    /**
     * Метод генерации пароля
     *
     * @param length длина пароля
     * @return готовый пароль
     */
    String generatePassword(int length);
}
