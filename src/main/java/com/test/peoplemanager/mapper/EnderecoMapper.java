package com.test.peoplemanager.mapper;

import com.test.peoplemanager.dto.EnderecoDto;
import com.test.peoplemanager.model.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    public EnderecoDto toDto (Endereco endereco) {
        return EnderecoDto.builder()
                .id(endereco.getId())
                .logradouro(endereco.getLogradouro())
                .cep(endereco.getCep())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .isPrincipal(endereco.isPrincipal())
                .build();
    }

    public Endereco toEntity (EnderecoDto enderecoDto) {
        return Endereco.builder()
                .id(enderecoDto.id())
                .logradouro(enderecoDto.logradouro())
                .cep(enderecoDto.cep())
                .numero(enderecoDto.numero())
                .cidade(enderecoDto.cidade())
                .isPrincipal(enderecoDto.isPrincipal())
                .build();
    }
}
