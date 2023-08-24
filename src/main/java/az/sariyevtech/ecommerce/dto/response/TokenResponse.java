package az.sariyevtech.ecommerce.dto.response;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TokenResponse {
    private final String storeId = "415db339-5a29-4fac-80f2-ea39ccfe0423";
    private final String userId = "97df6cc4-ed60-42fc-8079-828fb9f2046c";

    private String token;
}
