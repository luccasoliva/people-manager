package com.test.peoplemanager.controller;


import com.test.peoplemanager.dto.EnderecoDto;
import com.test.peoplemanager.services.EnderecoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping("/{idPessoa}")
    public List<EnderecoDto> buscarTodosEnderecosPorPessoa(@PathVariable Integer idPessoa){
        return enderecoService.buscarTodosEnderecosPorPessoa(idPessoa);
    }

    @GetMapping("/principal/{idPessoa}")
    public EnderecoDto buscarEnderecoPrincipalPessoa(@PathVariable Integer idPessoa){
        return enderecoService.buscarEnderecoPrincipalPessoa(idPessoa);
    }

    @PostMapping("/{idPessoa}")
    //change the format of LocalDate in swagger to dd-mm-yyyy
    public ResponseEntity<EnderecoDto> cadastrarEnderecoPessoa(@RequestBody EnderecoDto enderecoDto, @PathVariable Integer idPessoa){
        EnderecoDto endereco = enderecoService.cadastrarEnderecoPessoa(enderecoDto, idPessoa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("id")
                .buildAndExpand(enderecoDto.id()).toUri();

        return ResponseEntity.created(uri).body(endereco);
    }

    @PatchMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDto> tornarEnderecoPrincipalEOutrosNaoPrincipal(@PathVariable Integer idEndereco){
        EnderecoDto endereco = enderecoService.tornarEnderecoPrincipalEOutrosNaoPrincipal(idEndereco);
        return ResponseEntity.ok().body(endereco);
    }

    @DeleteMapping("/{idEndereco}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Integer idEndereco){
        enderecoService.deletarEndereco(idEndereco);
        return ResponseEntity.noContent().build();
    }


}
