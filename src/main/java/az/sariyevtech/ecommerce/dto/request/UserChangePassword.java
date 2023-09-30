package az.sariyevtech.ecommerce.dto.request;


public class UserChangePassword {
    public String password;
    private String confirmPas;
    public String getPassword() {
        return password;
    }

    public String getConfirmPas() {
        return confirmPas;
    }
}
