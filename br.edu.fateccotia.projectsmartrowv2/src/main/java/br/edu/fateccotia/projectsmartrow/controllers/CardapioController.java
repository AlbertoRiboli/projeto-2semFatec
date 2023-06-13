package br.edu.fateccotia.projectsmartrow.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import br.edu.fateccotia.projectsmartrow.model.Cardapio;
import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import br.edu.fateccotia.projectsmartrow.services.CardapioService;
import br.edu.fateccotia.projectsmartrow.services.EstabelecimentoService;

@RestController
@RequestMapping(value = "/cardapios")
public class CardapioController {
	
	@Autowired
	CardapioService cardapioService;
	@Autowired
	EstabelecimentoService estcont;
	
	@GetMapping
	public ResponseEntity <List<Cardapio>> findAll(){
		List<Cardapio> list = cardapioService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		Cardapio cardapio = cardapioService.findById(id);
		if(cardapio == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cardapio Não Encontrado! \nId: " + id + " não corresponde a nenhum cardapio cadastrado!");
		}
		return ResponseEntity.ok().body(cardapio);
	}
	
	@PostMapping
	public ResponseEntity<Cardapio> criarCardapio(@RequestBody Cardapio cardapio) {
		Cardapio newCardapio =  cardapioService.criarCardapio(cardapio);
		return ResponseEntity.ok().body(newCardapio);
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizarCardapio(@PathVariable Integer id, @RequestBody Cardapio cardapio){
		cardapio.setIDCardapio(id);
		if (cardapio.getIDCardapio() == -1) {
			return new ResponseEntity<>("Cardapio não Encontrado", HttpStatus.NOT_FOUND);
		}
		Cardapio newCardapio = cardapioService.atualizarCardapio(id, cardapio);
		return ResponseEntity.ok().body(newCardapio);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id){
		Cardapio cardapioVerif = cardapioService.findById(id);
		if (cardapioVerif == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cardapio Não Encontrado! \nId: " + id + " não corresponde a nenhum cardapio cadastrado!");
		}
		cardapioService.deleteById(id);
		return new ResponseEntity<>("Cardapio Deletado", HttpStatus.ACCEPTED);
		}
	
	@PostMapping(value = "/cadastrarcardapio/{id}")
	public ResponseEntity<?> cadastrarListaDeCardapio(@PathVariable Integer id, @RequestBody Cardapio cardapio){
		Estabelecimento estabelecimento = estcont.findById(id);
		if(estabelecimento == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento Não Encontrado!");
		}
		criarCardapio(cardapio);
		estabelecimento.setCardapio(cardapio);
		estcont.atualizarEstabelecimento(id, estabelecimento);
		return ResponseEntity.ok().body(cardapio);
	}
	
	@GetMapping(value = "/listarcardapio/{id}")
		public ResponseEntity<?> listarCardapioPorEstabelecimento(@PathVariable Integer id){
			Cardapio cardapio = new Cardapio();
			Estabelecimento est1 = estcont.findById(id);
			if(est1 == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento Não Encontrado!");
			}
			cardapio = est1.getCardapio();
			return ResponseEntity.ok().body(cardapio);
		}
	}
	
	
	
	


