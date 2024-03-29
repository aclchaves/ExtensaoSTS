package br.edu.iftm.extensaoSTS;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.iftm.extensaoSTS.domain.Atividade;
import br.edu.iftm.extensaoSTS.repositories.AtividadeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EstensaoStsApplicationTests {
	
	@Autowired
	private AtividadeRepository repo;
	
	@Test
	public void verificaQuantidadeAtividadesCadastradas() {
		Page<Atividade> atividades = this.repo.findAll(PageRequest.of(0, 10));
		assertThat(atividades.getTotalElements()).isGreaterThan(1L);
	}
	
	@Test
	public void findByNome() {
		List<Atividade> atividades = this.repo.findByNomeContaining("GDG");
		assertThat(atividades.size()).isEqualTo(1);
	}
	
	@Test
	public void find() {
		List<Atividade> atividades = this.repo.findByNomeContaining("carlos");
		assertThat(atividades.size()).isEqualTo(0);
	}

}
