package com.wayz.CFMS.services.user;

import com.wayz.CFMS.models.User;

import java.util.UUID;

/**
 * Интерфейс получения информации о пользователях
 */
public interface UserInfoService {

    /**
     * Получение пользователя по уникальному идентификатору
     *
     * @param id уникальный идентификатор пользователя
     * @return объект пользователя
     */
    User getUserById(UUID id);

    /**
     * Получение пользователя по логину
     *
     * @param login логин
     * @return объект пользователя
     */
    User getUserByLogin(String login);

    /**
     * Получение пользователя по почтовому адресу пользователя
     *
     * @param email почта пользователя
     * @return объект пользователя
     */
    User getUserByEmail(String email);

    /**
     * Проверить существует ли пользователь в системе по логину и почте
     *
     * @param email почта
     * @param login логин
     * @return логическое значение да или нет
     */
    boolean existsUserByEmailOrLogin(String email, String login);

    /**
     * Существует ли пользователь в системе с такой почтой
     *
     * @param email почта пользователя
     * @return логическое да или нет
     */
    boolean existsUserByEmail(String email);

    /**
     * Существует ли пользователь в системе с таким логином
     *
     * @param login логин пользователя
     * @return логическое да или нет
     */
    boolean existsUserByLogin(String login);

    /**
     * Проверить существует ли пользователь в системе по уникальному идентификатору пользователя
     *
     * @param id уникальный идентификатор
     * @return логическое значение да или нет
     */
    boolean existsUserById(UUID id);

    /**
     * Проверить последний вход в систему конкретного пользователя
     *
     * @param login логин пользователя
     * @return ответ строчкой с датой и временем
     */
    String checkLastUserLoginDate(String login);

    /**
     * Проверить последнее обновление пользователя (дата)
     *
     * @param login логин пользователя
     * @return ответ строчкой с датой и временем
     */
    String checkLastUserUpdateDate(String login);

    /**
     * Проверить дату регистрации пользователя
     *
     * @param login логин пользователя
     * @return ответ строчкой с датой и временем
     */
    String getUserRegistrationDate(String login);

}
