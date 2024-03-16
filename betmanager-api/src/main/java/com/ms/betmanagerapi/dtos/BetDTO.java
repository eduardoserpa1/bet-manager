package com.ms.betmanagerapi.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BetDTO {
    @NotBlank
    private Integer id;
    @NotBlank
    private Integer idUser;
    @NotBlank
    private Integer idSortition;
    @NotBlank
    private String numbers;
    @NotBlank
    private Boolean result;
}
