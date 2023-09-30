package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.dto.request.RegisterRequest;
import az.sariyevtech.ecommerce.dto.request.UserChangePassword;
import az.sariyevtech.ecommerce.dto.request.UserRequest;
import az.sariyevtech.ecommerce.exception.CustomException;
import az.sariyevtech.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import static az.sariyevtech.ecommerce.util.ErrorMessages.*;


@Service
public class ValidationService {
    private final UserRepository userRepository;

    public ValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validationCheckRegister(RegisterRequest request) {
        emailCheck(request.getEmail());
        passwordCheck(request.getPassword(), request.getConfirmPas());
        nameAndSurnameCheck(request.getName(), request.getSurname());
    }
//    public void validationCheckCreateShop(CreateShopRequest request) {
//        emailCheck(request.getEmail());
//        passwordCheck(request.getPassword(), request.getConfirmPas());
//        nameAndSurnameCheck(request.getName(), request.getSurname());
//    }

    public void updateUserValidationCheck(UserRequest update) {
        nameAndSurnameCheck(update.getName(), update.getSurname());
        emailCheck(update.getEmail());
        usernameCheck(update.getEmail());
    }

    public void updatePasswordValidationCheck(UserChangePassword password) {
        passwordCheck(password.getPassword(), password.getConfirmPas());
    }

    private void usernameCheck(String username) {
        if (userRepository.existsUserByUsername(username)) {
            throw new CustomException(TAKEN_USERNAME, "username");
        }
    }

    private void passwordCheck(String password, String confirmPass) {
        if (password.isEmpty()) {
            throw new CustomException(PASSWORD_NOT_NULL, "password");
        }
        if (confirmPass.isEmpty()) {
            throw new CustomException(PASSWORD_NOT_NULL, "password");
        }
        if (!password.matches(PASSWORD_REGEX)) {
            throw new CustomException(INVALID_PASSWORD, "password");
        }
        if (!password.equals(confirmPass)) {
            throw new CustomException(PASSWORDS_MATCH, "password");
        }
    }

    private void emailCheck(String email) {
        if (userRepository.existsUserByEmail(email)) {
            throw new CustomException(TAKEN_EMAIL, "email");
        }
        if (email.isEmpty()) {
            throw new CustomException(EMAIL_NOT_NULL, "email");
        }
        if (!email.matches(EMAIL_REGEX)) {
            throw new CustomException(INVALID_EMAIL, "email");
        }
    }

    private void nameAndSurnameCheck(String name, String surname) {
        if (name.isEmpty() && name.isBlank()) {
            throw new CustomException(NAME_NOT_NLL, "name");
        }
        if (surname.isEmpty() && surname.isBlank()) {
            throw new CustomException(SURNAME_NOT_NLL, "username");
        }
    }

}
