package com.ms.betmanagerapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Generated;

@Data
@Entity
@Table(name = "bm_user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Integer id;

    @NotNull
    private String name;

    @Pattern(regexp = "^[0-9]{11}$", message = "cpf must be 11 numbers in sequence.")
    @NotNull
    private String cpf;
}
