package br.edu.fateccotia.projectsmartrow.controllers;

import java.util.ArrayList;
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
import br.edu.fateccotia.projectsmartrow.model.Pratos;
import br.edu.fateccotia.projectsmartrow.services.CardapioService;
import br.edu.fateccotia.projectsmartrow.services.PratosService;
import br.edu.fateccotia.projectsmartrow.util.ValidadorDadosEntrada;

@RestController
@RequestMapping(value = "/pratos")
public class PratosController {

	@Autowired
	PratosService pratosService;
	@Autowired
	CardapioService cardapioService;
	@Autowired
	EstabelecimentoController estabelecimentoController;

	@GetMapping
	public ResponseEntity<List<Pratos>> findAll() {
		List<Pratos> list = pratosService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Pratos pratos = pratosService.findById(id);
		if (pratos == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Prato Não Encontrado! \nId: " + id + " não corresponde a nenhum prato cadastrada!");
		}
		return ResponseEntity.ok().body(pratos);
	}

	@PostMapping
	public ResponseEntity<Pratos> criarPratos(@RequestBody Pratos pratos) {
		Pratos newPrato = pratosService.criarPratos(pratos);
		return ResponseEntity.ok().body(newPrato);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizarPratos(@PathVariable Integer id, @RequestBody Pratos pratos) {
		pratos.setIDPrato(id);
		if (pratos.getIDPrato() == -1) {
			return new ResponseEntity<>("Prato não Encontrado", HttpStatus.NOT_FOUND);
		}
		Pratos newPrato = pratosService.atualizarPratos(id, pratos);
		return ResponseEntity.ok().body(newPrato);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		Pratos pratosVerif = pratosService.findById(id);
		if (pratosVerif == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Prato Não Encontrado! \nId: " + id + " não corresponde a nenhum prato cadastrado!");
		}
		pratosService.deleteById(id);
		return new ResponseEntity<>("Prato Deletado", HttpStatus.ACCEPTED);
	}

	
	@PostMapping(value = "/cadastrarprato/{id}")
	public ResponseEntity<?> cadastrarListaDePratos2(@PathVariable Integer id, @RequestBody Pratos prato) {
		Estabelecimento est1 = estabelecimentoController.instanciarEstabelecimento(id);
		if(est1 == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento Não Encontrado!");
		}
		Cardapio cardapio = new Cardapio();
		if (est1.getCardapio() != null) {
			if(ValidadorDadosEntrada.validaNome(prato.getNome()) == false) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome do Prato não pode ser vazio ou nulo");
			}
			if(ValidadorDadosEntrada.validaValor(prato.getValor()) == false) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor inválido! Digite um valor em formato válido");
			}
			if(ValidadorDadosEntrada.validaNome(prato.getTipoPrato()) == false) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo de Prato Inválido! Digite o tipo de prato");
			}
			if(ValidadorDadosEntrada.validaNome(prato.getIngredientes()) == false) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lista de ingredientes não pode ser vazia");
			}
			pratosService.criarPratos(prato);
			cardapio = est1.getCardapio();
			cardapio.setPratos(prato);
			cardapioService.atualizarCardapio(est1.getCardapio().getIDCardapio(), cardapio);
			estabelecimentoController.atualizarEstabelecimento(id, est1);
		} else {
			if(ValidadorDadosEntrada.validaNome(prato.getNome()) == false) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome do Prato não pode ser vazio ou nulo");
			}
			if(ValidadorDadosEntrada.validaValor(prato.getValor()) == false) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor inválido! Digite um valor em formato válido");
			}
			if(ValidadorDadosEntrada.validaNome(prato.getTipoPrato()) == false) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo de Prato Inválido! Digite o tipo de prato");
			}
			if(ValidadorDadosEntrada.validaNome(prato.getIngredientes()) == false) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lista de ingredientes não pode ser vazia");
			}
			pratosService.criarPratos(prato);
			cardapio.setPratos(prato);
			cardapioService.criarCardapio(cardapio);
			est1.setCardapio(cardapio);
			estabelecimentoController.atualizarEstabelecimento(id, est1);
		}
		return ResponseEntity.ok().body(prato);
	}

	@GetMapping(value = "/listarpratos/{id}")
	public ResponseEntity<?> listarPratosPorEstabelecimento(@PathVariable Integer id) {
		Estabelecimento est1 = estabelecimentoController.instanciarEstabelecimento(id);
		if(est1 == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento Não Encontrado!");
		}
		List<Pratos> pratos = est1.getCardapio().getPratos();
		return ResponseEntity.ok().body(pratos);
	}
	
//	@DeleteMapping
//	public ResponseEntity<?> deletarPratoPorIdwd 
}
