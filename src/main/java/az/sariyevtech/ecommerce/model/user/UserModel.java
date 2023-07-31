//package az.sariyevtech.ecommerce.model.user;
//
//import az.sariyevtech.ecommerce.model.store.StoreModel;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import javax.management.relation.Role;
//import java.util.Set;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Setter
//@Getter
//@Entity
//public class UserModel {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//    private String surname;
//    private String email;
//    private String password;
//    private byte[] photo;
//
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    @OneToMany(mappedBy = "user")
//    private Set<StoreModel> sore;
//
//}
