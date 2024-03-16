package com.ms.betmanagerapi.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SortitionDTO {
    @NotBlank
    private Integer Id;
    @NotBlank
    private String Numbers;
}
