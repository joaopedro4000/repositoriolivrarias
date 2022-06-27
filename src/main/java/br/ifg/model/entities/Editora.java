package br.ifg.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Editora implements Serializable {	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false, unique=true, length=50)
	private String nome;
	
	public Editora() {
		super();
	}	
		
	@OneToMany(mappedBy = "editora")
	private List<Livro> livros = new ArrayList<Livro>();

	
	public Editora(String nome) {
		this.nome = nome;
	}
	
	public Editora(long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Editora other = (Editora) obj;
		return id == other.id && Objects.equals(nome, other.nome);
	}

	public List<Livro> getLivros() {
		return livros;
	}

	@Override
	public String toString() {
		return "Editora [id=" + id + ", nome=" + nome + "]";
	}	

}
