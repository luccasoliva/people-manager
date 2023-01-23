package com.test.peoplemanager.services;

import com.test.peoplemanager.dto.EnderecoDto;
import com.test.peoplemanager.mapper.EnderecoMapper;
import com.test.peoplemanager.model.Endereco;
import com.test.peoplemanager.model.Pessoa;
import com.test.peoplemanager.repositories.EnderecoRepository;
import com.test.peoplemanager.repositories.PessoaRepository;
import com.test.peoplemanager.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {EnderecoService.class})
@ExtendWith(SpringExtension.class)
class EnderecoServiceTest {
    @MockBean
    private EnderecoMapper enderecoMapper;

    @MockBean
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @MockBean
    private PessoaRepository pessoaRepository;


    private EnderecoDto enderecoDto1;
    private EnderecoDto enderecoDto2;
    private Endereco endereco1;
    private Endereco endereco2;
    private Pessoa pessoa1;
    @BeforeEach
    public void setUp(){
        Pessoa pessoa1 = Pessoa.builder()
                .id(1)
                .nome("John Doe")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .build();

        endereco1 = Endereco.builder()
                .id(1)
                .logradouro("Rua 1")
                .numero(1)
                .cidade("Cidade 1")
                .cep("12345-678")
                .isPrincipal(true)
                .pessoa(pessoa1)
                .build();

        endereco2 = Endereco.builder()
                .id(2)
                .logradouro("Rua 2")
                .numero(2)
                .cidade("Cidade 2")
                .cep("12345-678")
                .isPrincipal(false)
                .pessoa(pessoa1)
                .build();

        enderecoDto1 = EnderecoDto.builder()
                .id(1)
                .logradouro("Rua 1")
                .numero(1)
                .cidade("Cidade 1")
                .cep("12345-678")
                .isPrincipal(true)
                .build();

        enderecoDto2 = EnderecoDto.builder()
                .id(2)
                .logradouro("Rua 2")
                .numero(2)
                .cidade("Cidade 2")
                .cep("12345-678")
                .isPrincipal(false)
                .build();

        when(enderecoRepository.findAllByPessoaId(1)).thenReturn(Arrays.asList(endereco1, endereco2));
        when(enderecoMapper.toDto(endereco1)).thenReturn(enderecoDto1);
        when(enderecoMapper.toDto(endereco2)).thenReturn(enderecoDto2);
        when(enderecoRepository.findAllByPessoaId(2)).thenReturn(Collections.singletonList(endereco2));

    }

    @Test
    public void testBuscarTodosEnderecosPorPessoa() {
        List<EnderecoDto> actualBuscarTodosEnderecosPorPessoaResult = this.enderecoService.buscarTodosEnderecosPorPessoa(1);
        assertEquals(Arrays.asList(enderecoDto1, enderecoDto2), actualBuscarTodosEnderecosPorPessoaResult);
    }


    @Test
    public void testBuscarEnderecoPrincipalPessoa() {
        EnderecoDto actualEnderecoPrincipal = enderecoService.buscarEnderecoPrincipalPessoa(1);
        assertEquals(enderecoDto1, actualEnderecoPrincipal);
    }

    @Test
    public void testBuscarEnderecoPrincipalPessoa_NotFound() {
        assertThrows(ResourceNotFoundException.class, () -> enderecoService.buscarEnderecoPrincipalPessoa(2));
    }

    @Test
    public void testCadastrarEnderecoPessoa() {
         //use the instances are defined

        when(enderecoRepository.save(endereco1)).thenReturn(endereco1);
        when(enderecoMapper.toEntity(enderecoDto1)).thenReturn(endereco1);
        when(pessoaRepository.findById(1)).thenReturn(Optional.of(endereco1.getPessoa()));

        EnderecoDto actualCadastrarEnderecoPessoaResult = enderecoService.cadastrarEnderecoPessoa(enderecoDto1, 1);
        assertEquals(enderecoDto1, actualCadastrarEnderecoPessoaResult);

    }

    @Test
    public void testTornarEnderecoPrincipalEOutrosNaoPrincipal() {

        when(enderecoRepository.findById(2)).thenReturn(Optional.of(endereco2));
        when(enderecoRepository.save(endereco2)).thenReturn(endereco2);

        EnderecoDto actualTornarEnderecoPrincipalEOutrosNaoPrincipalResult = enderecoService.tornarEnderecoPrincipalEOutrosNaoPrincipal(2);
        assertEquals(enderecoDto2, actualTornarEnderecoPrincipalEOutrosNaoPrincipalResult);

        verify(enderecoRepository, times(1)).findAllByPessoaId(1);
        verify(enderecoRepository, times(2)).save(any(Endereco.class));

        assertTrue(endereco2.isPrincipal());
        assertFalse(endereco1.isPrincipal());
    }

    @Test
    public void testDeletarEndereco() {
        when(enderecoRepository.findById(1)).thenReturn(Optional.of(endereco1));
        enderecoService.deletarEndereco(1);
        verify(enderecoRepository, times(1)).delete(endereco1);


    }

}

