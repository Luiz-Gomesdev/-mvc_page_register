package br.com.gft.projeto.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.gft.projeto.entities.Linguagem;
import br.com.gft.projeto.services.LinguagemService;

@Controller
@RequestMapping("linguagem")
public class LinguagemController {
	
	@Autowired
	private LinguagemService linguagemService;

	@RequestMapping(path = "novo") // http://localhost:8080/linguagem/novo
	public ModelAndView novaLinguagem() {

		ModelAndView mv = new ModelAndView("linguagem/form.html");
		mv.addObject("linguagem", new Linguagem());

		return mv;

	}

	@RequestMapping(method = RequestMethod.POST, path = "novo")
	public ModelAndView salvarLinguagem(@Valid Linguagem linguagem, BindingResult bindingResult) {

		ModelAndView mv = new ModelAndView("linguagem/form.html");
		
		if(bindingResult.hasErrors()) {
			mv.addObject("linguagem", linguagem);
			return mv;
		}
		
		mv.addObject("linguagem", new Linguagem());
		linguagemService.salvarLinguagem(linguagem);
		mv.addObject("mensagem", "Linguagem salva com sucesso!");

		return mv;

	}
	
	@RequestMapping //http://localhost:8080/linguagem
	public ModelAndView listarLinguagens() {
		
		ModelAndView mv = new ModelAndView("linguagem/listar.html");
		mv.addObject("lista", linguagemService.listarLinguagem());
		
		return mv;
	}
	
	@RequestMapping("/editar")
	public ModelAndView editarLinguagens(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("linguagem/form.html");
		Linguagem linguagem;
		
		try {
			linguagem = linguagemService.obterLinguagem(id);
		} catch (Exception e) {
			linguagem = new Linguagem();
			mv.addObject("mensagem", e.getMessage());
		}
		
		mv.addObject("linguagem", linguagem);
		
		return mv;
	}
	
	
	

}
