package az.sariyevtech.ecommerce.response;

import az.sariyevtech.ecommerce.repository.ProductRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TokenResponse {
    private Long userId = 1L;
    private String token;
}
