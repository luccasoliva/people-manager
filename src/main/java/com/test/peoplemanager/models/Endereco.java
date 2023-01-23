package com.test.peoplemanager.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
 import lombok.*;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String logradouro;
    private String cep;
    private Integer numero;
    private String cidade;

    private boolean isPrincipal;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "pessoaId")
    private Pessoa pessoa;
}
