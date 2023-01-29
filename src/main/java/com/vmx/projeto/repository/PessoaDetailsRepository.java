package com.vmx.projeto.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vmx.projeto.model.PessoaDetails;

@Repository
@Transactional
public interface PessoaDetailsRepository extends CrudRepository<PessoaDetails, Long> {
	
	@Query("select d from PessoaDetails d where d.pessoa.id = ?1")
	public List<PessoaDetails> getPessoaDetails(Long pessoaid);
}
