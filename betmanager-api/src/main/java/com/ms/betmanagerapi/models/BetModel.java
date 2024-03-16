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
    private Integer Id;
    private Integer IdUser;
    private Integer IdSortition;
    private String Numbers;
    private Boolean Result;
}
