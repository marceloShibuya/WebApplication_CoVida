package br.com.fiap.CoVida.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.CoVida.model.Convenio;
import br.com.fiap.CoVida.model.Doador;
import br.com.fiap.CoVida.repository.ConvenioRepository;

@Controller
@RequestMapping("/convenio")
public class ConvenioController {
	
	@Autowired
	private ConvenioRepository repository;
	
	@GetMapping()
	public ModelAndView convenios(Authentication doador) {
	List<Convenio> convenios = repository.findByDoador((Doador) doador.getPrincipal());
	ModelAndView modelAndView = new ModelAndView("convenios");
	modelAndView.addObject("convenios", convenios);
	return modelAndView;
	}
	
	@PostMapping()
	public String salvar(@Valid Convenio convenio, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) return "convenio_novo";
		repository.save(convenio);
		attribute.addFlashAttribute("message", "convênio cadastrado com sucesso!");
		return "redirect:convenio";
	}
	
	@RequestMapping("novo")
	public String formConvenio(Convenio convenio) {
		return "convenio_novo";
	}
	
	@RequestMapping("delete/{id}")
	public String deleteConvenio(@PathVariable Long id, RedirectAttributes attributes) {
		repository.deleteById(id);
		attributes.addFlashAttribute("message", "convênio apagado com sucesso");
		return "redirect:/convenio";
	}
	
	@GetMapping("{id}")
	public ModelAndView editForm(@PathVariable Long id) {
		Optional<Convenio> convenio = repository.findById(id);
		ModelAndView modelAndView = new ModelAndView("convenio_edit");
		modelAndView.addObject("convenio", convenio);
		return modelAndView;
	}
	
	@PostMapping("update")
	public String updateConvenio(@Valid Convenio convenio, BindingResult result, RedirectAttributes attributes){
		if (result.hasErrors()) return "convenio_edit";
		repository.save(convenio);
		attributes.addFlashAttribute("message", "convênio atualizado com sucesso");
		return "redirect:/convenio";
	}

}
