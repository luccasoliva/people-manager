package com.test.peoplemanager.repositories;

import com.test.peoplemanager.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {


    List<Endereco> findAllByPessoaId(Integer pessoaId);
}

