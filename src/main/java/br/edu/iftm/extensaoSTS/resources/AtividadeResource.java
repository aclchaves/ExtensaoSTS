package br.edu.iftm.extensaoSTS.resources;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.xml.ws.Response;

import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.extensaoSTS.AtividadeService;
import br.edu.iftm.extensaoSTS.domain.Atividade;
import javassist.NotFoundException;

@RestController
@RequestMapping(value="/atividades")
public class AtividadeResource {
	
	@Autowired
	private AtividadeService service;
	
	@GetMapping
	public ResponseEntity<?> findByNomeContainingIgnoreCase(@RequestParam(required = false, defaultValue = "") String nome) {
		return ResponseEntity.ok().body(service.findByNomeContainingIgnoreCase(nome));
	}

	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		try {
			Atividade atividade = service.buscar(id);
			return ResponseEntity.ok(atividade);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Atividade atividade) {
		try {
			return ResponseEntity.ok().body(service.create(atividade));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping
	public ResponseEntity<?> update(@Valid @RequestBody Atividade atividade) {
		try {
			service.update(atividade);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			service.delete(id);
			return ResponseEntity.ok().body("");
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	
	

}
