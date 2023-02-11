package com.vmx.projeto.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vmx.projeto.model.PessoaDetail;

@Repository
@Transactional
public interface PessoaDetailRepository extends CrudRepository<PessoaDetail, Long> {
	
	@Query("select d from PessoaDetail d where d.pessoa.id = ?1")
	public List<PessoaDetail> getPessoaDetails(Long pessoaid);
}
