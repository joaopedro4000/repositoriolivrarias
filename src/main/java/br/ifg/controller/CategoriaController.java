package br.ifg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifg.model.entities.Categoria;
import br.ifg.model.repositories.CategoriaRepository;
import br.ifg.model.repositories.LivroRepository;

@Controller
@RequestMapping("categorias")
public class CategoriaController {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	LivroRepository livroRepository;
	
	@GetMapping("/listar")
	public ModelAndView listar() {		
		ModelAndView mv = new ModelAndView("/categorias/listar");			
		mv.addObject("listaCategorias", categoriaRepository.findAll());		
		return mv;
	}
		
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Categoria categoria) {
		ModelAndView mv = new ModelAndView("/categorias/cadastrar");	
		//mv.addObject("listaLivro", livroRepository.findAllByOrderByIdDesc());			
		return mv;		
	}
	
	@PostMapping("/salvar")
	public String salvar(Categoria categoria, RedirectAttributes attr) {		
		if (categoria.getNome().isEmpty()) {
			attr.addFlashAttribute("fail", "Não foi possível inserir");			
		}				
		try {			
			categoriaRepository.save(categoria);
			attr.addFlashAttribute("success", "Registro inserido com sucesso");
		} catch (Exception e) {
			attr.addFlashAttribute("fail", "Não foi possível inserir");
		}  		
		return "redirect:/categorias/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView preEditar(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("/categorias/cadastrar");		
		mv.addObject("categoria", categoriaRepository.findById(id).get());
		//mv.addObject("listaLivro", livroRepository.findAllByOrderByIdDesc());
		return mv;
	}
	
	@PostMapping("/editar")
	public String editar(Categoria categoria, RedirectAttributes attr) {		
		
		try {	
			categoriaRepository.save(categoria);
			attr.addFlashAttribute("success", "Registro editado com sucesso");
		} catch (Exception e) {
			attr.addFlashAttribute("fail", "Não foi possível editar");
		}  
		
		return "redirect:/categorias/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {		
		
		try {			
			categoriaRepository.deleteById(id);
			attr.addFlashAttribute("success", "Registro deletado com sucesso");
		} catch (Exception e) {
			attr.addFlashAttribute("fail", "Não foi possível deletar");
		}		
		
		return "redirect:/categorias/listar";
	}
	
}
