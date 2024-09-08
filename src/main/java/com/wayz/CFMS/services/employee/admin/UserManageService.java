package com.wayz.CFMS.services.employee.admin;

import com.wayz.CFMS.models.User;
import com.wayz.CFMS.models.subModels.UserActivityStatus;

public interface UserManageService {

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
     * Удаление пользователя администратором
     *
     * @param login логин пользователя для удаления
     */
    void deleteUser(String login);
}
