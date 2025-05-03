package com.vijaya.payload.dto;

import com.vijaya.domain.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String fullName;
    private String email;

}
