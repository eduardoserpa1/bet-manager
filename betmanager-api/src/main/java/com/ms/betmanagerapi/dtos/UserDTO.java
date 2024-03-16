package com.ms.betmanagerapi.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    @NotBlank
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String cpf;
}
