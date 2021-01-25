package com.zakenn.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdentityDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String type;
}
