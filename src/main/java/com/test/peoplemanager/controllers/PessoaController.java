package com.test.peoplemanager.controllers;

import com.test.peoplemanager.dtos.PessoaDto;
import com.test.peoplemanager.services.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<PessoaDto>> listarPessoas() {
        return ResponseEntity.ok(pessoaService.listarPessoas());
    }

    @GetMapping("/{idPessoa}")
    public ResponseEntity<PessoaDto> buscarPessoaPeloId(@PathVariable Integer idPessoa) {
        return ResponseEntity.ok(pessoaService.buscarPessoaPeloId(idPessoa));
    }

    @PostMapping
    public ResponseEntity<PessoaDto> cadastrarPessoa(@RequestBody PessoaDto pessoaDto){
        PessoaDto pessoa = pessoaService.cadastrarPessoa(pessoaDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("id")
                .buildAndExpand(pessoaDto.id()).toUri();

        return ResponseEntity.created(uri).body(pessoa);
    }

    @PatchMapping("/{idPessoa}")
    public ResponseEntity<PessoaDto> atualizarPessoa(@PathVariable Integer idPessoa, @RequestBody PessoaDto pessoaDto){
        PessoaDto pessoa = pessoaService.atualizarPessoa(idPessoa, pessoaDto);
        return ResponseEntity.ok().body(pessoa);
    }

    @DeleteMapping("/{idPessoa}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Integer idPessoa){
        pessoaService.deletarPessoa(idPessoa);
        return ResponseEntity.noContent().build();
    }


}
