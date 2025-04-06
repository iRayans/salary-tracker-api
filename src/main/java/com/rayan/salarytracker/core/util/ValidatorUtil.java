package com.rayan.salarytracker.core.util;

import com.rayan.salarytracker.service.impls.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ValidatorUtil {

    @Inject
    UserService userService;

}
