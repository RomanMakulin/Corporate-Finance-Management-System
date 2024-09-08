package com.wayz.CFMS.services.employee.impl;

import com.wayz.CFMS.dto.UserRegistrationData;
import com.wayz.CFMS.models.User;
import com.wayz.CFMS.repositories.UserRepository;
import com.wayz.CFMS.services.employee.EmployeeService;
import com.wayz.CFMS.services.employee.UserInfoService;
import com.wayz.CFMS.services.employee.auth.UserAuthService;

import java.util.Optional;

/**
 * Implementation of EmployeeService interface
 */
public class EmployeeServiceImpl implements EmployeeService {

    private final UserAuthService userAuthService;
    private final UserRepository userRepository;

    public EmployeeServiceImpl(UserAuthService userAuthService,
                               UserRepository userRepository) {
        this.userAuthService = userAuthService;
        this.userRepository = userRepository;
    }

    @Override
    public void updateEmployeeData(UserRegistrationData updateData) {
        User userToUpdate = userAuthService.getCurrentAuthUser(); // auth user to update

        Optional.ofNullable(updateData.getDateBirth()).ifPresent(userToUpdate::setBirthdayDate);
        Optional.ofNullable(updateData.getEmail()).ifPresent(userToUpdate::setEmail);
        Optional.ofNullable(updateData.getFirstName()).ifPresent(userToUpdate::setFirstName);
        Optional.ofNullable(updateData.getLastName()).ifPresent(userToUpdate::setLastName);
        Optional.ofNullable(updateData.getLogin()).ifPresent(userToUpdate::setLogin);
        Optional.ofNullable(updateData.getPassword()).ifPresent(userToUpdate::setPassword);

        userRepository.save(userToUpdate); // update user in DB
    }
}
