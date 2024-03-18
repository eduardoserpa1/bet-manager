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

    @Pattern(regexp = "^[0-9]{1,2},[0-9]{1,2},[0-9]{1,2},([0-9]{1,2},){1,26}[0-9]{1,2}$", message = "sortition numbers must be formatted like '1,12,8,20,30' and contains numbers count greater than 5 and lesser than 30.")
    @NotNull
    private String numbers;

    @NotNull
    private Boolean isFinished;
}
