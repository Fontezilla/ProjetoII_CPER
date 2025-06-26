package com.example.cper_core.dtos.auth;

import com.example.cper_core.validation.ValidPassword;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatePassword {
    @ValidPassword
    private String currentPassword;

    @ValidPassword
    private String newPassword;
}
