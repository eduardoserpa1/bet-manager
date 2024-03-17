package com.ms.betmanagerapi.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "bm_bet")
public class BetModel {
    @Id
    @SequenceGenerator(name = "EntityTwoSequence", initialValue = 1000)
    @GeneratedValue(generator = "EntityTwoSequence")
    @Column(unique = true)
    private Integer id;

    @Pattern(regexp = "^([0-9]{1,2},){4}[0-50]{1,2}$", message = "numbers must be formatted like '11,12,13,20,30' and contains exactly five numbers.")
    @NotNull
    private String numbers;

    @NotNull
    private Boolean result;

    @NotNull
    private Integer idUser;

    @NotNull
    private Integer idSortition;
}
