package br.ifg.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifg.model.entities.Editora;

public interface EditoraRepository extends JpaRepository<Editora, Long> {	
	/* Editora findById(String id); */	
	List<Editora> findAllByOrderByIdDesc();	
}


