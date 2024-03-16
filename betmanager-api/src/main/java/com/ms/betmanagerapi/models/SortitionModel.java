package com.ms.betmanagerapi.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bm_sortition")
public class SortitionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String numbers;
}
