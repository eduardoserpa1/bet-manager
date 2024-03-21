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
    @SequenceGenerator(name = "betIdGenerator", initialValue = 1000)
    @GeneratedValue(generator = "betIdGenerator")
    @Column(unique = true)
    private Integer id;

    @Pattern(regexp = "^[0-9]{1,2},[0-9]{1,2},[0-9]{1,2},[0-9]{1,2},[0-9]{1,2}$", message = "bet numbers must be formatted like '1,12,5,20,30' and contains exactly five numbers.")
    @NotNull
    private String numbers;

    @NotNull
    private Integer idUser;

    @NotNull
    private Integer idSortition;

    @NotNull
    private Boolean isWinner;
}
