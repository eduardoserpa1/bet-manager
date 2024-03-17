package com.ms.betmanagerapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "bm_sortition")
public class SortitionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Integer id;

    @Pattern(regexp = "^([0-9]{1,2},)([0-9]{1,2},)([0-9]{1,2},)([0-9]{1,2},)+[0-50]{1,2}$", message = "numbers must be formatted like '11,12,13,20,30' and contains at least five numbers.")
    @NotNull
    private String numbers;
}
