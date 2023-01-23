package com.test.peoplemanager.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

@Builder
public record EnderecoDto (@ApiModelProperty(hidden = true)
                           Integer id,

                           String logradouro,
                           String cep,
                           Integer numero,
                           String cidade,
                           @ApiModelProperty(hidden = true)
                           boolean isPrincipal) {
}
