package com.test.peoplemanager.services;

import com.test.peoplemanager.dto.PessoaDto;
import com.test.peoplemanager.mapper.PessoaMapper;
import com.test.peoplemanager.model.Pessoa;
import com.test.peoplemanager.repositories.PessoaRepository;
import com.test.peoplemanager.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;


    public List<PessoaDto> listarPessoas() {
        return pessoaRepository.findAll().stream()
                .map(pessoaMapper::toDto)
                .collect(Collectors.toList());
    }

    public PessoaDto buscarPessoaPeloId(Integer idPessoa) {
        return pessoaRepository.findById(idPessoa)
                .map(pessoaMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa", "id", idPessoa));
    }




    public PessoaDto cadastrarPessoa(PessoaDto pessoaDto) {
        Pessoa pessoa = pessoaMapper.toEntity(pessoaDto);
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        return pessoaMapper.toDto(pessoaSalva);
    }

    public PessoaDto atualizarPessoa(Integer idPessoa, PessoaDto pessoaDto) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new  ResourceNotFoundException("Pessoa", "id", idPessoa));

        pessoa.setNome(pessoaDto.nome());
        pessoa.setDataNascimento(pessoaDto.dataNascimento());

        return pessoaMapper.toDto(pessoaRepository.save(pessoa));
    }

    public void deletarPessoa(Integer idPessoa) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new  ResourceNotFoundException("Pessoa", "id", idPessoa));
        pessoaRepository.delete(pessoa);
    }



}
