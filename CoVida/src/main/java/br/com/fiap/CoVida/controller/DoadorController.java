package br.com.fiap.CoVida.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.CoVida.model.Doador;
import br.com.fiap.CoVida.repository.DoadorRepository;

@Controller
@RequestMapping("/doador")
public class DoadorController {
	
	@Autowired
	private DoadorRepository repository;

	@GetMapping()
	public ModelAndView doadores() {
		List<Doador> doadores = repository.findAll();
		ModelAndView modelAndView = new ModelAndView("doadores");
		modelAndView.addObject("doadores", doadores);
		return modelAndView;
	}
	
	
	@PostMapping()
	public String salvar(@Valid Doador doador, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) return "doador_novo";
		doador.setSenha(new BCryptPasswordEncoder().encode(doador.getPassword()));
		repository.save(doador);
		attribute.addFlashAttribute("message", "cadastrado realizado com sucesso!");
		return "redirect:login";
	}
	
	@RequestMapping("novo")
	public String formDoador(Doador doador) {
		return "doador_novo";
	}
	
	@RequestMapping("delete/{id}")
	public String deleteDoador(@PathVariable Long id, RedirectAttributes attributes) {
		repository.deleteById(id);
		attributes.addFlashAttribute("message", "doador apagado com sucesso");
		return "redirect:/doador";
	}
	
	@GetMapping("{id}")
	public ModelAndView editForm(@PathVariable Long id) {
		Optional<Doador> doador = repository.findById(id);
		ModelAndView modelAndView = new ModelAndView("doador_edit");
		modelAndView.addObject("doador", doador);
		return modelAndView;
	}
	
	@PostMapping("update")
	public String updateDoador(@Valid Doador doador, BindingResult result, RedirectAttributes attributes){
		if (result.hasErrors()) return "doador_edit";
		repository.save(doador);
		attributes.addFlashAttribute("message", "doador atualizado com sucesso");
		return "redirect:/doador";
	}
	

}
