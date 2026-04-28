package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Pessoa;

@Repository
public interface PessoaRepository extends BaseRepository<Pessoa, Long> {

    @Query("SELECT p FROM Pessoa p WHERE p.email = :email AND p.ativo = TRUE")
    Optional<Pessoa> findByEmail(String email);
}
