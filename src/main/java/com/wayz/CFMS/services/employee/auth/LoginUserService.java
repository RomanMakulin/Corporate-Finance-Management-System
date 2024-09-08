package com.wayz.CFMS.services.employee.auth;

import org.springframework.http.ResponseEntity;

/**
 * Интерфейс для входа пользователя в систему
 */
public interface LoginUserService {

    /**
     * Реализация функционала входа пользователя в систему
     *
     * @param login    логин для входа
     * @param password пароль для входа
     * @return ответ от сервера в виде строки
     */
    ResponseEntity<String> login(String login, String password);
}
