package com.multipolar.bootcamp.gateway.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AddressDTO {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
