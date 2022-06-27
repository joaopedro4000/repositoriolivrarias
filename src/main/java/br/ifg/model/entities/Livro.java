package br.ifg.model.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Livro implements Serializable {		
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false, unique=true, length=20)
	private String isbn;
	
	@Column(nullable=false, length=50)
	private String titulo;	
		
	@ManyToOne
	@JoinColumn(name = "fk_categoria", nullable=false)
	private Categoria categoria;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "fk_editora") /* , nullable=false */
	private Editora editora;
		
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_resenha", nullable=false)
	private Resenha resenha;
	
	public Livro() {
		super();		
	} 
			
	public Livro(long id, String isbn, String titulo) {		
		this.id = id;
		this.isbn = isbn;
		this.titulo = titulo;
	}	
	
	public Livro(String isbn, String titulo, Categoria categoria, Resenha resenha, Editora editora) {	
		this.isbn = isbn;
		this.titulo = titulo;
		this.setCategoria(categoria);
		this.setResenha(resenha);
		this.setEditora(editora);
	}	

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}	

	
	public Resenha getResenha() {
		return resenha;
	}

	public void setResenha(Resenha resenha) {
		this.resenha = resenha;
	}
	
	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
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
		Livro other = (Livro) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", categoria=" + categoria + ", resenha="
				+ resenha + "]";
	}	

}
