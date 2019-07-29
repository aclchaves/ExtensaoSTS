package br.edu.iftm.extensaoSTS;

import java.util.List;

import javax.management.openmbean.InvalidKeyException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.extensaoSTS.domain.Atividade;
import br.edu.iftm.extensaoSTS.repositories.AtividadeRepository;
import javassist.NotFoundException;

@Service
public class AtividadeService {
	
	@Autowired
	private AtividadeRepository repo;
	
	public List<Atividade> findByNomeContainingIgnoreCase(String nome) {
		return repo.findByNomeContainingIgnoreCase(nome);
	}	

	
	public Atividade buscar(Integer id) {
		Atividade atividade = repo.findById(id).get();
		return atividade;
	}
	
	public Atividade create(Atividade atividade) {
		return repo.save(atividade);
	}
	
	public void update(Atividade atividade) throws InvalidKeyException, NotFoundException {
		if(atividade.getId() == 0) {
			throw new InvalidKeyException("Id não pode ser nulo.");
		}
		
		buscar(atividade.getId());
		
		repo.save(atividade);
	}
	
	public void delete(Integer id) throws NotFoundException {
		buscar(id);
		
		repo.deleteById(id);
	}


}
