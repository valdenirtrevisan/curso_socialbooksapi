package com.algaworks.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.socialbooks.domain.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
