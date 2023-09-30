package az.sariyevtech.ecommerce.dto.authDto;

import az.sariyevtech.ecommerce.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto userModelToDto(User from) {
        return UserDto.builder()
                .id(from.getId())
                .name(from.getName())
                .surname(from.getSurname())
                .email(from.getEmail())
                .phoneNumber(from.getPhoneNumber())
                .role(from.getRole())
                .gender(from.getGender())
                .active(from.isActive())
                .profilePhoto(from.getProfilePhoto())
                .build();
    }
}
