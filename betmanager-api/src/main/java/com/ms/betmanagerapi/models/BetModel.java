package com.ms.betmanagerapi.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bm_bet")
public class BetModel {
    @Id
    private Integer id;
    private Integer idUser;
    private Integer idSortition;
    private String numbers;
    private Boolean result;
}
