package br.ifg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifg.model.entities.Editora;
import br.ifg.model.repositories.LivroRepository;
import br.ifg.model.repositories.EditoraRepository;

@Controller
@RequestMapping("editoras")
public class EditoraController {
	
	@Autowired
	EditoraRepository editoraRepository;
	
	@Autowired
	LivroRepository livroRepository;
	
	@GetMapping("/listar")
	public ModelAndView listar() {		
		ModelAndView mv = new ModelAndView("/editoras/listar");			
		mv.addObject("listaEditoras", editoraRepository.findAll());		
		return mv;
	}
		
	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Editora editora) {
		ModelAndView mv = new ModelAndView("/editoras/cadastrar");	
		//mv.addObject("listaLivro", livroRepository.findAllByOrderByIdDesc());			
		return mv;		
	}
	
	@PostMapping("/salvar")
	public String salvar(Editora editora, RedirectAttributes attr) {		
		if (editora.getNome().isEmpty()) {
			attr.addFlashAttribute("fail", "Não foi possível inserir");			
		}				
		try {			
			editoraRepository.save(editora);
			attr.addFlashAttribute("success", "Registro inserido com sucesso");
		} catch (Exception e) {
			attr.addFlashAttribute("fail", "Não foi possível inserir");
		}  		
		return "redirect:/editoras/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView preEditar(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("/editoras/cadastrar");		
		mv.addObject("editora", editoraRepository.findById(id).get());
		//mv.addObject("listaLivro", livroRepository.findAllByOrderByIdDesc());
		return mv;
	}
	
	@PostMapping("/editar")
	public String editar(Editora editora, RedirectAttributes attr) {		
		
		try {	
			editoraRepository.save(editora);
			attr.addFlashAttribute("success", "Registro editado com sucesso");
		} catch (Exception e) {
			attr.addFlashAttribute("fail", "Não foi possível editar");
		}  
		
		return "redirect:/editoras/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {		
		
		try {			
			editoraRepository.deleteById(id);
			attr.addFlashAttribute("success", "Registro deletado com sucesso");
		} catch (Exception e) {
			attr.addFlashAttribute("fail", "Não foi possível deletar");
		}		
		
		return "redirect:/editoras/listar";
	}
	
}
