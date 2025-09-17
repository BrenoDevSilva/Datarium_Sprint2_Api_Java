package com.datarium.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "T_DATARIUM_CLIENTE")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @NotBlank(message = "O email não pode estar em branco")
    @Email(message = "O email deve ser válido")
    private String email;

    @NotNull(message = "O perfil do investidor é obrigatório")
    private PerfilInvestidor perfilInvestidor;

    @NotNull(message = "O objetivo é obrigatório")
    private Objetivo objetivo;

    private LocalDate dataCadastro;

}