package com.vmx.projeto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vmx.projeto.model.Pessoa;
import com.vmx.projeto.model.PessoaDetail;
import com.vmx.projeto.repository.PessoaDetailRepository;
import com.vmx.projeto.repository.PessoaRepository;


@Controller
public class PessoaController {
	
	@Autowired
	public PessoaRepository pessoaRepository;
	
	@Autowired
	public PessoaDetailRepository pessoadetailsRepository;

	@RequestMapping(method=RequestMethod.GET, value="/cadastropessoa")
	public ModelAndView inicio() {
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastropessoa");
		modelAndView.addObject("pessoaobj", new Pessoa());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="**/salvarpessoa")
	public ModelAndView  salvar(@Valid Pessoa pessoa, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("/cadastro/cadastropessoa");
			Iterable<Pessoa> pessoaIt = pessoaRepository.findAll();
			modelAndView.addObject("pessoas", pessoaIt);
			modelAndView.addObject("pessoaobj", pessoa);
			
			List<String> msg = new ArrayList<String>();
			for (ObjectError objectError : bindingResult.getAllErrors() ) {
				msg.add(objectError.getDefaultMessage());
			}
			
			modelAndView.addObject("msg", msg);
			return modelAndView;
		}
		
		pessoaRepository.save(pessoa);
		ModelAndView andView = new ModelAndView("/cadastro/cadastropessoa");
		Iterable<Pessoa> pessoaIt = pessoaRepository.findAll();
		andView.addObject("pessoas", pessoaIt);
		andView.addObject("pessoaobj", new Pessoa());
		return andView;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="**/listapessoas")
	public ModelAndView pessoas() {
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<Pessoa> pessoaIt = pessoaRepository.findAll();
		andView.addObject("pessoas", pessoaIt);
		andView.addObject("pessoaobj", new Pessoa());
		return andView;
	}
	
	@GetMapping("**/editarpessoa/{idpessoa}")
	public ModelAndView editar(@PathVariable("idpessoa")Long idpessoa) {
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastropessoa");
		modelAndView.addObject("pessoaobj", pessoa.get());
		return modelAndView;
	}
	
	@GetMapping("**/removerpessoa/{idpessoa}")
	public ModelAndView excluir(@PathVariable("idpessoa")Long idpessoa) {
		
		pessoaRepository.deleteById(idpessoa);
		
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastropessoa");
		modelAndView.addObject("pessoas", pessoaRepository.findAll());
		modelAndView.addObject("pessoaobj", new Pessoa());
		return modelAndView;
	}
	
	@PostMapping("**/pesquisarpessoa")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa")String nomepesquisa) {
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastropessoa");
		modelAndView.addObject("pessoas", pessoaRepository.findPessoaByName(nomepesquisa));
		modelAndView.addObject("pessoaobj", new Pessoa());
		return modelAndView;
	}
	
	@GetMapping("**/adddetailpessoa/{idpessoa}")
	public ModelAndView cadstroPessoaDetails(@PathVariable("idpessoa")Long idpessoa) {
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		
		ModelAndView modelAndView = new ModelAndView("/cadastro/pessoadetails");
		modelAndView.addObject("pessoaobj", pessoa.get());
		modelAndView.addObject("pessoaDetails", pessoadetailsRepository.getPessoaDetails(idpessoa));
		return modelAndView;
	}
	
	@PostMapping("**/adddetailpessoa/{pessoaid}")
	public ModelAndView addPessoaDetail(PessoaDetail pessoaDetail, @PathVariable("pessoaid")Long pessoaid) {
		
		Pessoa pessoa = pessoaRepository.findById(pessoaid).get();
		pessoaDetail.setPessoa(pessoa);
		pessoadetailsRepository.save(pessoaDetail);
		
		ModelAndView modelAndView = new ModelAndView("/cadastro/pessoadetails");
		modelAndView.addObject("pessoaobj", pessoa);
		modelAndView.addObject("pessoaDetail", pessoadetailsRepository.getPessoaDetails(pessoaid));
	
		return modelAndView;
	}
	
	@GetMapping("**/pessoadetails/{idpessoa}")
	public ModelAndView pessoaDetails(@PathVariable("idpessoa")Long idpessoa) {
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		
		ModelAndView modelAndView = new ModelAndView("/cadastro/pessoadetails");
		modelAndView.addObject("pessoaobj", pessoa.get());
		modelAndView.addObject("pessoaDetails", pessoadetailsRepository.getPessoaDetails(idpessoa));
		return modelAndView;
	}
	
	@GetMapping("**/removerpessoadetails/{id_pessoa_details}")
	public ModelAndView removerPessoaDetails(@PathVariable("id_pessoa_details")Long id_pessoa_details) {
		
		pessoadetailsRepository.deleteById(id_pessoa_details);
		
		ModelAndView modelAndView = new ModelAndView("/cadastro/pessoadetails");
		modelAndView.addObject("pessoas", pessoaRepository.findAll());
		modelAndView.addObject("pessoaobj", new Pessoa());
		return modelAndView;
	}
	
}
