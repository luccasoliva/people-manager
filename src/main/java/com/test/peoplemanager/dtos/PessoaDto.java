package com.test.peoplemanager.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.peoplemanager.models.Endereco;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record PessoaDto (@ApiModelProperty(hidden = true)
                         Integer id,
                         String nome,
                         @ApiModelProperty(dataType = "string", example = "dd/MM/yyyy")
                         @JsonFormat(pattern = "dd/MM/yyyy")
                         LocalDate dataNascimento,
                         @ApiModelProperty(hidden = true)
                         List<Endereco> enderecos) {
}
