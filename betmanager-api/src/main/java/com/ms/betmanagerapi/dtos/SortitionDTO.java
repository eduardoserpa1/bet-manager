package com.ms.betmanagerapi.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SortitionDTO {
    @NotBlank
    private Integer id;
    @NotBlank
    private String numbers;
    @NotBlank
    private Boolean isFinished;
}
