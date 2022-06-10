package br.com.gft.projeto.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.projeto.entities.Desenvolvedor;
import br.com.gft.projeto.services.DesenvolvedorService;
import br.com.gft.projeto.services.LinguagemService;

@Controller
@RequestMapping("desenvolvedor")
public class DesenvolvedorController {

	@Autowired
	private DesenvolvedorService desenvolvedorService;

	@Autowired
	private LinguagemService linguagemService;

// Metodo para editar Desenvolvedor
	@RequestMapping(path = "editar")
	public ModelAndView editarDesenvolvedor(@RequestParam(required = false) Long id) {

		ModelAndView mv = new ModelAndView("desenvolvedor/form.html");

		Desenvolvedor desenvolvedor;

		if (id == null) {
			desenvolvedor = new Desenvolvedor();
		} else {
			try {
				desenvolvedor = desenvolvedorService.obterDesenvolvedor(id);
			} catch (Exception e) {
				desenvolvedor = new Desenvolvedor();
				mv.addObject("mensagem", e.getMessage());
			}
		}

		mv.addObject("desenvolvedor", desenvolvedor);
		mv.addObject("listaLinguagens", linguagemService.listarLinguagem());

		return mv;

	}

//	Metodo para salvar Desenvolvedor
	@RequestMapping(method = RequestMethod.POST, path = "editar")
	public ModelAndView salvarDesenvolvedor(@Valid Desenvolvedor desenvolvedor, BindingResult bindingResult) {

		ModelAndView mv = new ModelAndView("desenvolvedor/form.html");

		boolean novo = true;

		if (desenvolvedor.getId() != null) {
			novo = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("desenvolvedor", desenvolvedor);
			return mv;
		}
		
		desenvolvedorService.salvarDesenvolvedor(desenvolvedor);

		if (novo) {
			mv.addObject("desenvolvedor", desenvolvedor);

		}

		mv.addObject("mensagem", "Desenvolvedor salvo com sucesso!");
		mv.addObject("listaLinguagens", linguagemService.listarLinguagem());

		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarDesenvolvedor() {
		
		ModelAndView mv = new ModelAndView("desenvolvedor/listar.html");
		
		mv.addObject("lista", desenvolvedorService.listarDesenvolvedores());
		
		return mv;
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirDesenvolvedor(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/desenvolvedor");
		
		try {
			desenvolvedorService.excluirDesenvolvedor(id);
			redirectAttributes.addFlashAttribute("mensagem", "Desenvolvedor excluído com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir desenvolvedor! " + e.getMessage());
		}
		
		
		return mv;
	}
	
	
	
	
	
	
	

}
