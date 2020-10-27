package br.com.fiap.CoVida.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.CoVida.model.Contato;
import br.com.fiap.CoVida.repository.ContatoRepository;

@Controller
@RequestMapping("contato")
public class ContatoController {
	
	@Autowired
	private ContatoRepository repository;

	@GetMapping()
	public ModelAndView contatos(@PageableDefault(page =0, size=5)Pageable pageable) {
	
		Page<Contato> contatos = repository.findAll(pageable);
		ModelAndView modelAndView = new ModelAndView("contatos");
		modelAndView.addObject("contatos", contatos);
		return modelAndView;
	}
	
	
	@PostMapping()
	public String salvar(@Valid Contato contato, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) return "contato_novo";
		repository.save(contato);
		attribute.addFlashAttribute("message", "contato cadastrado com sucesso!");
		return "redirect:contato";
	}
	
	@RequestMapping("novo")
	public String formContato(Contato contato) {
		return "contato_novo";
	}
	
	@RequestMapping("delete/{id}")
	public String deleteContato(@PathVariable Long id, RedirectAttributes attributes) {
		repository.deleteById(id);
		attributes.addFlashAttribute("message", "contato apagado com sucesso");
		return "redirect:/contato";
	}
	
	@GetMapping("{id}")
	public ModelAndView editForm(@PathVariable Long id) {
		Optional<Contato> contato = repository.findById(id);
		ModelAndView modelAndView = new ModelAndView("contato_edit");
		modelAndView.addObject("contato", contato);
		return modelAndView;
	}
	
	@PostMapping("update")
	public String updateContato(@Valid Contato contato, BindingResult result, RedirectAttributes attributes){
		if (result.hasErrors()) return "contato_edit";
		repository.save(contato);
		attributes.addFlashAttribute("message", "contato editado com sucesso");
		return "redirect:/contato";
	}
	
	

}
