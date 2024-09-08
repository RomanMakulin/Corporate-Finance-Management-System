package com.wayz.CFMS.services.employee.auth;

import com.wayz.CFMS.dto.UserRegistrationData;
import com.wayz.CFMS.models.User;

/**
 * Интерфейс регистрации пользователей
 */
public interface RegistrationService {

    /**
     * Метод регистрации пользователя в системе
     *
     * @param regData объект с данными о пользователе
     * @return созданный объект нового пользователя
     */
    User registrationUser(UserRegistrationData regData);
}
