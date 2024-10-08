package com.wayz.CFMS.repositories;

import com.wayz.CFMS.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Интерфейс взаимодействия с базой данных. Таблица "users"
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Проверить если ли пользователь в БД по заданным параметрам: email, login
     *
     * @param email почта
     * @param login логин
     * @return логическое выражение true/false
     */
    boolean existsUserByEmailOrLogin(String email, String login);

    /**
     * Получить пользователя по логину
     *
     * @param login логин
     * @return объект с пользователем, либо null
     */
    Optional<User> getUsersByLogin(String login);

    /**
     * Получить пользователя по email
     *
     * @param email логин
     * @return объект с пользователем, либо null
     */
    Optional<User> getUsersByEmail(String email);

    /**
     * Существует ли пользователь в системе с такой почтой
     *
     * @param email почта пользователя
     * @return логическое да или нет
     */
    boolean existsUserByEmail(String email);

    /**
     * Существует ли пользователь с таким логином
     *
     * @param login логин пользователя
     * @return логическое да или нет
     */
    boolean existsUserByLogin(String login);
}
