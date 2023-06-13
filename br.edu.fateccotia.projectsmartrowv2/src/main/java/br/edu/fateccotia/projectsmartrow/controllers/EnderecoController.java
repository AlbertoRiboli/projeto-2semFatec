package br.edu.fateccotia.projectsmartrow.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fateccotia.projectsmartrow.model.Endereco;
import br.edu.fateccotia.projectsmartrow.services.EnderecoService;
import br.edu.fateccotia.projectsmartrow.util.ValidadorDadosEntrada;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping
	public ResponseEntity<List<Endereco>> findAll() {
		List<Endereco> list = enderecoService.findAll();
		return ResponseEntity.ok().body(list);	
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		Endereco newEnd = enderecoService.findById(id);
		if (newEnd == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco Não Encontrado! \nId: " + id + " não corresponde a nenhum endereço cadastrado!");
		}
		return ResponseEntity.ok().body(newEnd);
	}
	
	@PostMapping
	public ResponseEntity<?> criarEndereco(@RequestParam(value="cep", required = true ) String cep, 
			@RequestParam(value="num", defaultValue = "Não Informado") String num, @RequestParam(value="comp", defaultValue = "Não Informado") String comp, 
			@RequestParam(value="ref", defaultValue = "Não Informado") String ref){
		if(ValidadorDadosEntrada.validaCep(cep) == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CEP é nulo ou possui formato invalido!");
		}
		Endereco endereco = enderecoService.criarEndereco(cep, num, comp, ref);
		return ResponseEntity.ok().body(endereco);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizarEndereco(@PathVariable Integer id, @RequestBody Endereco endereco) {
		endereco.setId(id);
		if(endereco.getId() == -1) {
			return new ResponseEntity<>("Endereço Não Encontrado", HttpStatus.NOT_FOUND);
		} 
			Endereco newEnd = enderecoService.atualizarEndereco(id, endereco);
			return ResponseEntity.ok().body(newEnd);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id){
		Endereco endVerif = enderecoService.findById(id);
		if (endVerif == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco Não Encontrado! \nId: " + id + " não corresponde a nenhum endereço cadastrado!");
		}
		enderecoService.deleteById(id);
		return new ResponseEntity<>("Endereco Deletado", HttpStatus.ACCEPTED);
		}

}

