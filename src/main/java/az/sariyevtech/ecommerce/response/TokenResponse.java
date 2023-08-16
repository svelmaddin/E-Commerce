package az.sariyevtech.ecommerce.response;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TokenResponse {
    private final String userId = "3";
    private String token;
}
