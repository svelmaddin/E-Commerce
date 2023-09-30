package az.sariyevtech.ecommerce.controller.authController;

import az.sariyevtech.ecommerce.dto.authDto.UserDto;
import az.sariyevtech.ecommerce.dto.request.UserChangePassword;
import az.sariyevtech.ecommerce.dto.request.UserRequest;
import az.sariyevtech.ecommerce.model.user.Role;
import az.sariyevtech.ecommerce.services.StoreService;
import az.sariyevtech.ecommerce.services.impl.FileService;
import az.sariyevtech.ecommerce.services.impl.UserService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static az.sariyevtech.ecommerce.util.ErrorMessages.PHOTO_UPLOAD_SUCCESS;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final FileService fileService;
    private final StoreService storeService;

    public UserController(UserService userService,
                          FileService fileService, StoreService storeService) {
        this.userService = userService;
        this.fileService = fileService;
        this.storeService = storeService;
    }

    @PostMapping("/editUser")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserRequest request) {
        return ResponseEntity.ok().body(userService.updateUser(request));
    }

    @PostMapping("/profilePhoto")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) {
        fileService.uploadPhoto(file);
        return ResponseEntity.ok(PHOTO_UPLOAD_SUCCESS);
    }

    @GetMapping("/profilePhoto")
    public ResponseEntity<byte[]> downloadProfilePhoto() {
        byte[] profilePhoto = fileService.downloadProfilePhoto();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("profilePhoto.jpg").build());
        return new ResponseEntity<>(profilePhoto, headers, HttpStatus.OK);
    }

    @GetMapping("/getUserById")
    public ResponseEntity<UserDto> getUserById() {
        return ResponseEntity.ok().body(userService.findUserById());
    }

    @PatchMapping("/editPassword")
    public ResponseEntity<Void> updateUserPassword(@RequestBody UserChangePassword request) {
        userService.updatePassword(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/admin/userList")
    public ResponseEntity<List<UserDto>> getUserListForAdmin() {
        return ResponseEntity.ok(userService.getUserList());
    }

    @GetMapping("/api/admin/userList/{role}")
    public ResponseEntity<List<UserDto>> findByRole(@PathVariable Role role) {
        return ResponseEntity.ok(userService.findByRole(role));
    }

    @DeleteMapping("/api/admin/deleteUser")
    public void deleteUser(String id) {
        userService.deleteUser(id);
    }

//    @PostMapping("/api/admin/edit/status")
//    public ResponseEntity<Void> setUserActiveStatus(@RequestParam(name = "id") String id,
//                                                    @RequestParam(name = "status") boolean status) {
//        storeService.setShopActiveStatus(id, status);
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/getEmailByUserId/{id}")
    public ResponseEntity<String> getUserEmail(@PathVariable String id) {
        return ResponseEntity.ok(userService.findUserEmail(id));
    }


}
