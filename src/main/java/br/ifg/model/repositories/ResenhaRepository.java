package br.ifg.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifg.model.entities.Resenha;

public interface ResenhaRepository extends JpaRepository<Resenha, Long> {

}

