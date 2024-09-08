package com.wayz.CFMS.services.employee.user;

import com.wayz.CFMS.dto.UserRegistrationData;
import com.wayz.CFMS.models.User;
import com.wayz.CFMS.models.subModels.UserActivityStatus;

/**
 * Интерфейс управления пользователями
 */
public interface UserService {

    /**
     * Частичное обновление пользователя своих данных
     *
     * @param updateData объект с данными для обновления пользователя
     * @return обновленный объект пользователя
     */
    User updateCurrentUser(UserRegistrationData updateData);

    void saveUserInDataBase(User user);

}
