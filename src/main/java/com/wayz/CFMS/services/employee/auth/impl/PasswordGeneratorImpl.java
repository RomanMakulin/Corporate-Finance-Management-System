package com.wayz.CFMS.services.employee.auth.impl;

import com.wayz.CFMS.services.employee.auth.PasswordGenerator;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

/**
 * Реализация интерфейса генерации пароля
 */
@Service
public class PasswordGeneratorImpl implements PasswordGenerator {
    // Набор символов для заглавных букв
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Набор символов для строчных букв
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";

    // Набор символов для цифр
    private static final String DIGITS = "0123456789";

    // Набор специальных символов
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";

    // Объединяем все символы в одну строку
    private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;

    // SecureRandom используется для безопасной генерации случайных чисел
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Метод для генерации пароля
     *
     * @param length длина пароля
     * @return пароль
     */
    @Override
    public String generatePassword(int length) {
        // Проверяем, что длина пароля не меньше 8 символов
        if (length < 8) {
            throw new IllegalArgumentException("Длина пароля должна быть не менее 8 символов.");
        }

        // Создаем StringBuilder для сборки пароля
        StringBuilder password = new StringBuilder(length);

        // Добавляем хотя бы один символ из каждой категории
        password.append(UPPERCASE.charAt(RANDOM.nextInt(UPPERCASE.length())));
        password.append(LOWERCASE.charAt(RANDOM.nextInt(LOWERCASE.length())));
        password.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARACTERS.charAt(RANDOM.nextInt(SPECIAL_CHARACTERS.length())));

        // Заполняем оставшиеся символы случайными символами из всех категорий
        for (int i = 4; i < length; i++) {
            password.append(ALL_CHARACTERS.charAt(RANDOM.nextInt(ALL_CHARACTERS.length())));
        }

        return shuffleString(password.toString());
    }

    /**
     * Метод для перемешивания символов в строке, чтобы избежать предсказуемых последовательностей
     *
     * @param input password
     * @return новый пароль (перемешанный)
     */
    private String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = RANDOM.nextInt(characters.length);
            // Обмен местами символов для их перемешивания
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }
}
