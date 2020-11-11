package com.dbms.coaching.validators;

import com.dbms.coaching.models.User;
import com.dbms.coaching.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StudentValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("user.username", "Duplicate.user.username");
        }
        if (userService.findByEmailAddress(user.getEmailAddress()) != null) {
            errors.rejectValue("user.emailAddress", "Duplicate.user.emailAddress");
        }

    }
}
