package com.wayz.CFMS.services.user;

import com.wayz.CFMS.dto.UserRegistrationData;
import com.wayz.CFMS.models.User;
import com.wayz.CFMS.models.subModels.UserActivityStatus;

public interface UserManageService {

    /**
     * Частичное обновления порльзователя собой же
     *
     * @param updateData объект с данными для обновления пользователя
     * @return обновленный объект пользователя
     */
    User updateCurrentUser(UserRegistrationData updateData);

    /**
     * Полное обновление пользователя администратором
     *
     * @param updateData объект с данными для обновления пользователя
     * @return обновленный объект пользователя
     */
    User updateUserByAdmin(User updateData);

    /**
     * Изменить статус активности пользоателю
     *
     * @param login      логин пользователя
     * @param userStatus новый статус пользователя
     * @return обновленный объект пользователя
     */
    User updateUserStatus(String login, UserActivityStatus userStatus);

    /**
     * Сохранить пользователя через JPA Repository в БД
     *
     * @param user объект пользователя для сохранения
     */
    void saveUserInDataBase(User user);

    /**
     * Удалить пользователя из базы данных
     *
     * @param user объект пользователя
     */
    void deleteUserFromDataBase(User user);

}
