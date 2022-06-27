package br.ifg.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifg.model.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {	
	Livro findByIsbn(String isbn);		
	List<Livro> findAllByOrderByIdDesc();	
}


