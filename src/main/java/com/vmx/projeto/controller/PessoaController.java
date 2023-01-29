package com.vmx.projeto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vmx.projeto.model.Pessoa;
import com.vmx.projeto.model.PessoaDetails;
import com.vmx.projeto.repository.PessoaDetailsRepository;
import com.vmx.projeto.repository.PessoaRepository;


@Controller
public class PessoaController {
	
	@Autowired
	public PessoaRepository pessoaRepository;
	
	@Autowired
	public PessoaDetailsRepository pessoadetailsRepository;

	@RequestMapping(method=RequestMethod.GET, value="/cadastropessoa")
	public ModelAndView inicio() {
		ModelAndView modelAndView = new ModelAndView("/cadastro/cadastropessoa");
		modelAndView.addObject("pessoaobj", new Pessoa());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="**/salvarpessoa")
	public ModelAndView  salvar(Pessoa pessoa) {
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
	
	@GetMapping("/editarpessoa/{idpessoa}")
	public ModelAndView editar(@PathVariable("idpessoa")Long idpessoa) {
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoaobj", pessoa.get());
		return modelAndView;
	}
	
	@GetMapping("/removerpessoa/{idpessoa}")
	public ModelAndView excluir(@PathVariable("idpessoa")Long idpessoa) {
		
		pessoaRepository.deleteById(idpessoa);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoas", pessoaRepository.findAll());
		modelAndView.addObject("pessoaobj", new Pessoa());
		return modelAndView;
	}
	
	@PostMapping("**/pesquisarpessoa")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa")String nomepesquisa) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
		modelAndView.addObject("pessoas", pessoaRepository.findPessoaByName(nomepesquisa));
		modelAndView.addObject("pessoaobj", new Pessoa());
		return modelAndView;
	}
	
	@GetMapping("/pessoadetails/{idpessoa}")
	public ModelAndView pessoaDetails(@PathVariable("idpessoa")Long idpessoa) {
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/pessoadetails");
		modelAndView.addObject("pessoaobj", pessoa.get());
		modelAndView.addObject("pessoaDetails", pessoadetailsRepository.getPessoaDetails(idpessoa));
		return modelAndView;
	}
	
	@PostMapping("**/addpessoadetails/{pessoaid}")
	public ModelAndView addPessoaDetail(PessoaDetails pessoaDetails, @PathVariable("pessoaid")Long pessoaid) {
		
		Pessoa pessoa = pessoaRepository.findById(pessoaid).get();
		pessoaDetails.setPessoa(pessoa);
		pessoadetailsRepository.save(pessoaDetails);
		
		ModelAndView mva = new ModelAndView("cadastro/pessoadetails");
		mva.addObject("pessoaobj", pessoa);
		mva.addObject("pessoaDetails", pessoadetailsRepository.getPessoaDetails(pessoaid));

		
		return mva;
	}
	
}
