package az.sariyevtech.ecommerce.response;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TokenResponse {
    private final Long userId = 3L;
    private String token;
}
