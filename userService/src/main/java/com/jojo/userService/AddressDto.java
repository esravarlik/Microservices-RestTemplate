package com.jojo.userService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Long id;
    private String fullAddress;
    private String city;
    private Integer postalCode;
    private String country;
}
