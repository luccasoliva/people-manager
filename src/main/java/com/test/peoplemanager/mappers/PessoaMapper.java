package com.test.peoplemanager.mappers;

import com.test.peoplemanager.dtos.PessoaDto;
import com.test.peoplemanager.models.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {

     public PessoaDto toDto (Pessoa pessoa) {
        return PessoaDto.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .dataNascimento(pessoa.getDataNascimento())
                .enderecos(pessoa.getEnderecos())
                .build();
    }

     public Pessoa toEntity (PessoaDto pessoaDto) {
        return Pessoa.builder()
                .nome(pessoaDto.nome())
                .dataNascimento(pessoaDto.dataNascimento())
                .enderecos(pessoaDto.enderecos())
                .build();
    }
}
