package br.edu.iftm.extensaoSTS.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iftm.extensaoSTS.domain.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade,Integer>{
	
	//@Query("Select a from Atividade a where a.nome like %:nome%")
	public List<Atividade> findByNomeContaining(@Param("nome") String nome);

	public List<Atividade> findByNomeContainingIgnoreCase(String nome);

}
