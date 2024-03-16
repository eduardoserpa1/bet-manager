package com.ms.betmanagerapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bm_sortition")
public class SortitionModel {
    @Id
    private Integer Id;
    private String Numbers;
}
