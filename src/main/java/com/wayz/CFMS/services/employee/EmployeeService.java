package com.wayz.CFMS.services.employee;

import com.wayz.CFMS.dto.UserRegistrationData;

/**
 * Интерфейс для работы с сотрудниками
 */
public interface EmployeeService {

    /**
     * Обновляет данные сотрудника (например, фамилию, дату рождения, и т д..)
     *
     * @param updateData объект с данными для изменения
     */
    void updateEmployeeData(UserRegistrationData updateData);

    // TODO добавить другие методы для работы с сотрудниками

}
