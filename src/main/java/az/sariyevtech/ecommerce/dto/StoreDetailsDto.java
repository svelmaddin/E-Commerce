package az.sariyevtech.ecommerce.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

import java.time.LocalDate;

public class StoreDetailsDto {
    private Long id;
    private String country;
    private String city;
    private String street;
    private String zipcode;
    private String address;
    private String phoneNumber;
    private LocalDate createTime;
    private LocalDate updateTime;
    private Byte[] photo;
}
