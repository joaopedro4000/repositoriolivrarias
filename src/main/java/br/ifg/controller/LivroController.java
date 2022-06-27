package br.ifg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifg.model.entities.Livro;
import br.ifg.model.repositories.CategoriaRepository;
import br.ifg.model.repositories.EditoraRepository;
import br.ifg.model.repositories.LivroRepository;

@Controller
@RequestMapping("livros")
public class LivroController {
	
	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	EditoraRepository editoraRepository;
	
	@GetMapping("/listar")
	public ModelAndView listar() {		
		ModelAndView mv = new ModelAndView("/livros/listar");			
		mv.addObject("listaLivros", livroRepository.findAll());		
		return mv;
	}
		
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Livro livro) {
		ModelAndView mv = new ModelAndView("/livros/cadastrar");	
		mv.addObject("listaCategoria", categoriaRepository.findAllByOrderByNome());
		mv.addObject("listaEditora", editoraRepository.findAllByOrderByIdDesc());
		return mv;		
	}
	
	@PostMapping("/salvar")
	public String salvar(Livro livro, RedirectAttributes attr) {		
		if (livro.getIsbn().isEmpty() || livro.getTitulo().isEmpty()) {
			attr.addFlashAttribute("fail", "Não foi possível inserir");			
		}				
		try {			
			livroRepository.save(livro);
			attr.addFlashAttribute("success", "Registro inserido com sucesso");
		} catch (Exception e) {
			attr.addFlashAttribute("fail", "Não foi possível inserir");
		}  		
		return "redirect:/livros/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView preEditar(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("/livros/cadastrar");		
		mv.addObject("livro", livroRepository.findById(id).get());
		mv.addObject("listaCategoria", categoriaRepository.findAllByOrderByNome());
		mv.addObject("listaEditora", editoraRepository.findAllByOrderByIdDesc());
		return mv;
	}
	
	@PostMapping("/editar")
	public String editar(Livro livro, RedirectAttributes attr) {		
		
		try {	
			livroRepository.save(livro);
			attr.addFlashAttribute("success", "Registro editado com sucesso");
		} catch (Exception e) {
			attr.addFlashAttribute("fail", "Não foi possível editar");
		}  
		
		return "redirect:/livros/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {		
		
		try {			
			livroRepository.deleteById(id);
			attr.addFlashAttribute("success", "Registro deletado com sucesso");
		} catch (Exception e) {
			attr.addFlashAttribute("fail", "Não foi possível deletar");
		}		
		
		return "redirect:/livros/listar";
	}
	
}
