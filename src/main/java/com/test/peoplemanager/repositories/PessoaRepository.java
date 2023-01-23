package com.test.peoplemanager.repositories;

import com.test.peoplemanager.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
