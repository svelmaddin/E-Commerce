package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.exception.CustomException;
import az.sariyevtech.ecommerce.model.user.User;
import az.sariyevtech.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static az.sariyevtech.ecommerce.util.ErrorMessages.PHOTO_NULL;


@Service
public class FileService {
    private final UserService userService;
    private final UserRepository userRepository;

    public FileService(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Transactional
    public void uploadPhoto(MultipartFile file) {
        User user = userService.currentUser();
        try {
            byte[] fileContent = file.getBytes();
            user.setProfilePhoto(fileContent);
            userRepository.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public byte[] downloadProfilePhoto() {
        User user = userService.currentUser();
        byte[] profilePhoto = user.getProfilePhoto();
        if (profilePhoto == null) {
            throw new CustomException(PHOTO_NULL, "Photo");
        }
        return profilePhoto;
    }
}
