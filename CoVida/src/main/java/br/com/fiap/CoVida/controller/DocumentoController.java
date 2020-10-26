package br.com.fiap.CoVida.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.CoVida.model.Documento;
import br.com.fiap.CoVida.repository.DocumentoRepository;

@Controller
@RequestMapping("/documento")
public class DocumentoController {
	
	@Autowired
	private DocumentoRepository repository;
	
	@GetMapping()
	public ModelAndView documentos() {
		List<Documento> documentos = repository.findAll();
		ModelAndView modelAndView = new ModelAndView("documentos");
		modelAndView.addObject("documentos", documentos);
		return modelAndView;
	}
	
	@PostMapping()
	public String salvar(@Valid Documento documento, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) return "documento_novo";
		repository.save(documento);
		attribute.addFlashAttribute("message", "documento cadastrado com sucesso!");
		return "redirect:documento";
	}

	@RequestMapping("novo")
	public String formDocumento(Documento documento) {
		return "documento_novo";
	}
	
	@RequestMapping("delete/{id}")
	public String deleteDocumento(@PathVariable Long id, RedirectAttributes attributes) {
		repository.deleteById(id);
		attributes.addFlashAttribute("message", "documento apagado com sucesso");
		return "redirect:/documento";
	}
	
	@GetMapping("{id}")
	public ModelAndView editForm(@PathVariable Long id) {
		Optional<Documento> documento = repository.findById(id);
		ModelAndView modelAndView = new ModelAndView("documento_edit");
		modelAndView.addObject("documento", documento);
		return modelAndView;
	}
	
	@PostMapping("update")
	public String updateDoador(@Valid Documento documento, BindingResult result, RedirectAttributes attributes){
		if (result.hasErrors()) return "documento_edit";
		repository.save(documento);
		attributes.addFlashAttribute("message", "documento atualizado com sucesso");
		return "redirect:/documento";
	}

}
