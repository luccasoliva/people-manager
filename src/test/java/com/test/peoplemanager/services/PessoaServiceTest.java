package com.test.peoplemanager.services;

import com.test.peoplemanager.dtos.PessoaDto;
import com.test.peoplemanager.mappers.PessoaMapper;
import com.test.peoplemanager.models.Pessoa;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PessoaService.class})
@ExtendWith(SpringExtension.class)
class PessoaServiceTest {
    @MockBean
    private PessoaMapper pessoaMapper;

    @MockBean
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;


    private Pessoa pessoa1;
    private Pessoa pessoa2;
    private PessoaDto pessoaDto1;
    private PessoaDto pessoaDto2;

    @BeforeEach
    public void setUp() {
        pessoa1 = Pessoa.builder()
                .id(1)
                .nome("John Doe")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .build();

        pessoa2 = Pessoa.builder()
                .id(2)
                .nome("Jane Smith")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .build();

        pessoaDto1 = PessoaDto.builder()
                .id(1)
                .nome("John Doe")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .build();

        pessoaDto2 = PessoaDto.builder()
                .id(2)
                .nome("Jane Smith")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .build();

        when(pessoaRepository.findAll()).thenReturn(Arrays.asList(pessoa1, pessoa2));
        when(pessoaMapper.toDto(pessoa1)).thenReturn(pessoaDto1);
        when(pessoaMapper.toDto(pessoa2)).thenReturn(pessoaDto2);
    }

    @Test
    public void testListarPessoas() {
        List<PessoaDto> expected = Arrays.asList(pessoaDto1, pessoaDto2);
        List<PessoaDto> result =  pessoaService.listarPessoas();

        assertEquals(expected, result);
    }

    @Test
    public void testBuscarPessoaPeloIdSuccess() {
        when(pessoaRepository.findById(1)).thenReturn(Optional.of(pessoa1));

        PessoaDto expected = pessoaDto1;
        PessoaDto result = pessoaService.buscarPessoaPeloId(1);

        assertEquals(expected, result);
    }

    @Test
    public void testBuscarPessoaPeloIdNotFound() {
        when(pessoaRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> pessoaService.buscarPessoaPeloId(1));
    }

    @Test
    public void testCadastrarPessoa() {
        when(pessoaMapper.toEntity(pessoaDto1)).thenReturn(pessoa1);
        when(pessoaRepository.save(pessoa1)).thenReturn(pessoa1);

        PessoaDto expected = pessoaDto1;
        PessoaDto result = pessoaService.cadastrarPessoa(pessoaDto1);

        assertEquals(expected, result);
    }

    @Test
    public void testAtualizarPessoa() {
        when(pessoaRepository.findById(1)).thenReturn(Optional.of(pessoa1));
        when(pessoaMapper.toEntity(pessoaDto1)).thenReturn(pessoa1);
        when(pessoaRepository.save(pessoa1)).thenReturn(pessoa1);

        PessoaDto expected = pessoaDto1;
        PessoaDto result = pessoaService.atualizarPessoa(1, pessoaDto1);

        assertEquals(expected, result);
    }

    @Test
    public void testDeletarPessoa() {
        when(pessoaRepository.findById(1)).thenReturn(Optional.of(pessoa1));

        pessoaService.deletarPessoa(1);
        verify(pessoaRepository).delete(pessoa1);
    }

}

