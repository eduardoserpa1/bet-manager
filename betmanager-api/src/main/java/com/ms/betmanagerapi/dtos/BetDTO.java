package com.ms.betmanagerapi.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BetDTO {
    @NotBlank
    private Integer Id;
    @NotBlank
    private Integer IdUser;
    @NotBlank
    private Integer IdSortition;
    @NotBlank
    private String Numbers;
    @NotBlank
    private Boolean Result;
}
