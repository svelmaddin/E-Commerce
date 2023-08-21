package az.sariyevtech.ecommerce.dto.response;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TokenResponse {
    private final String storeId = "452dc02f-16e1-4509-af24-28d30681d817";
    private final String userId = "0cba0b56-a740-4fa9-8de5-836a32906be8";

    private String token;
}
