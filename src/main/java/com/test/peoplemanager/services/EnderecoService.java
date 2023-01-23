package com.test.peoplemanager.services;


import com.test.peoplemanager.dtos.EnderecoDto;
import com.test.peoplemanager.mappers.EnderecoMapper;
import com.test.peoplemanager.models.Endereco;
import com.test.peoplemanager.models.Pessoa;
import com.test.peoplemanager.repositories.EnderecoRepository;
import com.test.peoplemanager.repositories.PessoaRepository;
import com.test.peoplemanager.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;

    private final PessoaRepository pessoaRepository;


    public List<EnderecoDto> buscarTodosEnderecosPorPessoa(Integer idPessoa){
        //get all enderecos by pessoa id
        List<Endereco> enderecos = enderecoRepository.findAllByPessoaId(idPessoa);
        return enderecos.stream()
                .map(enderecoMapper::toDto)
                .collect(Collectors.toList());
    }

    public EnderecoDto buscarEnderecoPrincipalPessoa(Integer idPessoa) {
        return enderecoMapper.toDto(enderecoRepository.findAllByPessoaId(idPessoa)
                .stream()
                .filter(Endereco::isPrincipal)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Endereço principal", "idPessoa", idPessoa)));
    }


    public EnderecoDto cadastrarEnderecoPessoa(EnderecoDto enderecoDto, Integer idPessoa){
        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa", "id", idPessoa));

        Endereco endereco = enderecoMapper.toEntity(enderecoDto);
        endereco.setPessoa(pessoa);

        return enderecoMapper.toDto(enderecoRepository.save(endereco));
    }

    public EnderecoDto tornarEnderecoPrincipalEOutrosNaoPrincipal(Integer idEndereco) {
        Endereco endereco = enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço", "id", idEndereco));

        enderecoRepository.findAllByPessoaId(endereco.getPessoa().getId())
                .stream()
                .filter(Endereco::isPrincipal)
                .forEach(enderecoPrincipal -> {
                    enderecoPrincipal.setPrincipal(false);
                    enderecoRepository.save(enderecoPrincipal);
                });

        endereco.setPrincipal(true);
        return enderecoMapper.toDto(enderecoRepository.save(endereco));
    }

    public void deletarEndereco(Integer idEndereco) {
        Endereco endereco = enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço", "id", idEndereco));

        enderecoRepository.delete(endereco);
    }


}
