package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {

    @Email(message = "O email deve ser preenchido")
    @NotBlank(message = "O campo deve ser preenchido")
    private String email;

    @NotBlank(message = "O campo deve ser preenchido")
    private String senha;
}