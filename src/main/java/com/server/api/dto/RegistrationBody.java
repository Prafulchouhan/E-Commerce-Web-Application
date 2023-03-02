package com.server.api.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationBody {
    @NotNull
    @NotBlank
    @Size(min = 3,max = 255)
    private String username;

    @NotNull
    @Email
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 6,max = 32)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")
    private String password;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;
}
