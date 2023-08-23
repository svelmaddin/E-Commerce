package az.sariyevtech.ecommerce.dto.delivery;

import az.sariyevtech.ecommerce.model.delivery.City;
import jakarta.validation.constraints.Null;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto {
    private Long id;
    private String customerId;
    private String customerFullName;
    private String city;
    private String phoneNumber;
    private String zipCode;
    private String address;
}
