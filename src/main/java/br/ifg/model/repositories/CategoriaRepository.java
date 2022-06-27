package br.ifg.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifg.model.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	List<Categoria> findAllByOrderByNome(); 
}

