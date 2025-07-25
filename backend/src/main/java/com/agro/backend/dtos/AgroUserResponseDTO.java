package com.agro.backend.dtos;

import com.agro.backend.entities.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgroUserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Role role;
    private String address;
}
