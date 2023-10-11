package com.multipolar.bootcamp.gateway.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CustomerDTO {
    private String id;
    private String nik;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private AddressDTO address;
    private MembershipStatusDTO membershipStatus;
}
