package com.ms.betmanagerapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Generated;

@Data
@Entity
@Table(name = "bm_user")
public class UserModel {

    @Id
    private Integer id;
    private String name;
    private String cpf;
}
