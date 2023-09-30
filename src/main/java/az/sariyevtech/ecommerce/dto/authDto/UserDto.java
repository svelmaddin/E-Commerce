package az.sariyevtech.ecommerce.dto.authDto;


import az.sariyevtech.ecommerce.model.user.Gender;
import az.sariyevtech.ecommerce.model.user.Role;
import lombok.Builder;

@Builder
public record UserDto(
        String id,
        String email,
        String name,
        String surname,
        String phoneNumber,
        Role role,
        Gender gender,
        boolean active,
        byte[] profilePhoto
) {
}
