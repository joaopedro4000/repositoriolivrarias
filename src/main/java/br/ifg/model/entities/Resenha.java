package br.ifg.model.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Resenha implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false, length=255)
	private String descricao;
	
	@OneToOne(mappedBy = "resenha")
	Livro livro;

	public Resenha() {
		super();		
	}
	
	public Resenha(String descricao) {		
		this.descricao = descricao;
	}
	
	public long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resenha other = (Resenha) obj;
		return id == other.id;
	}
	
	@Override
	public String toString() {
		return "Resenha [id=" + id + ", descricao=" + descricao + "]";
	}

}
