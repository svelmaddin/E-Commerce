package az.sariyevtech.ecommerce.dto.request;

import az.sariyevtech.ecommerce.model.user.Gender;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private Gender gender;
    private String confirmPas;
}
