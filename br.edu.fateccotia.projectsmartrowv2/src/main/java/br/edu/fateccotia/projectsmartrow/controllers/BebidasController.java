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
import br.edu.fateccotia.projectsmartrow.model.Bebidas;
import br.edu.fateccotia.projectsmartrow.services.CardapioService;
import br.edu.fateccotia.projectsmartrow.util.ValidadorDadosEntrada;
import br.edu.fateccotia.projectsmartrow.services.BebidasService;

@RestController
@RequestMapping(value = "/bebidas")
public class BebidasController {

	@Autowired
	BebidasService bebidasService;
	@Autowired
	CardapioService cardapioService;
	@Autowired
	EstabelecimentoController estabelecimentoController;

	@GetMapping
	public ResponseEntity<List<Bebidas>> findAll() {
		List<Bebidas> list = bebidasService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Bebidas bebidas = bebidasService.findById(id);
		if (bebidas == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Bebida Não Encontrada! \nId: " + id + " não corresponde a nenhuma bebida cadastrada!");
		}
		return ResponseEntity.ok().body(bebidas);
	}

	@PostMapping
	public ResponseEntity<?> criarBebidas(@RequestBody Bebidas bebidas) {
		if(ValidadorDadosEntrada.validaNome(bebidas.getNome()) == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome da Bebida não pode ser vazio ou nulo");
		}
		if(ValidadorDadosEntrada.validaValor(bebidas.getValor()) == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor inválido! Digite um valor em formato válido");
		}
		if(ValidadorDadosEntrada.validaNome(bebidas.getTipoBebida()) == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo de Bebida Inválido! Digite o tipo de bebida");
		}
		Bebidas newBebida = bebidasService.criarBebidas(bebidas);
		return ResponseEntity.ok().body(newBebida);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizarBebidas(@PathVariable Integer id, @RequestBody Bebidas bebidas) {
		bebidas.setIDBebida(id);
		if (bebidas.getIDBebida() == -1) {
			return new ResponseEntity<>("Bebida não Encontrada", HttpStatus.NOT_FOUND);
		}
		Bebidas newBebida = bebidasService.atualizarBebidas(id, bebidas);
		return ResponseEntity.ok().body(newBebida);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		Bebidas bebidasVerif = bebidasService.findById(id);
		if (bebidasVerif == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Bebida Não Encontrada! \nId: " + id + " não corresponde a nenhuma bebida cadastrada!");
		}
		bebidasService.deleteById(id);
		return new ResponseEntity<>("Bebida Deletada", HttpStatus.ACCEPTED);
	}

	
	@PostMapping(value = "/cadastrarbebida/{id}")
	public ResponseEntity<?> cadastrarListaDeBebidas(@PathVariable Integer id, @RequestBody Bebidas bebida) {
		Estabelecimento est1 = estabelecimentoController.instanciarEstabelecimento(id);
		if(est1 == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento Não Encontrado!");
		}
		Cardapio cardapio = new Cardapio();
		if (est1.getCardapio() != null) {
			if(ValidadorDadosEntrada.validaNome(bebida.getNome()) == false) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome da Bebida não pode ser vazio ou nulo");
			}
			if(ValidadorDadosEntrada.validaValor(bebida.getValor()) == false) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor inválido! Digite um valor em formato válido");
			}
			if(ValidadorDadosEntrada.validaNome(bebida.getTipoBebida()) == false) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo de Bebida Inválido! Digite o tipo de bebida");
			}
			bebidasService.criarBebidas(bebida);
			cardapio = est1.getCardapio();
			cardapio.setBebidas(bebida);
			cardapioService.atualizarCardapio(est1.getCardapio().getIDCardapio(), cardapio);
			estabelecimentoController.atualizarEstabelecimento(id, est1);
		} else {
			if(ValidadorDadosEntrada.validaNome(bebida.getNome()) == false) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome da Bebida não pode ser vazio ou nulo");
			}
			if(ValidadorDadosEntrada.validaValor(bebida.getValor()) == false) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor inválido! Digite um valor em formato válido");
			}
			if(ValidadorDadosEntrada.validaNome(bebida.getTipoBebida()) == false) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo de Bebida Inválido! Digite o tipo de bebida");
			}
			bebidasService.criarBebidas(bebida);
			cardapio.setBebidas(bebida);
			cardapioService.criarCardapio(cardapio);
			est1.setCardapio(cardapio);
			estabelecimentoController.atualizarEstabelecimento(id, est1);
		}
		return ResponseEntity.ok().body(bebida);
	}

	@GetMapping(value = "/listarbebidas/{id}")
	public ResponseEntity<?> listarBebidasPorEstabelecimento(@PathVariable Integer id) {
		Estabelecimento est1 = estabelecimentoController.instanciarEstabelecimento(id);
		if(est1 == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento Não Encontrado!");
		}
		List<Bebidas> bebidas = est1.getCardapio().getBebidas();
		return ResponseEntity.ok().body(bebidas);
	}
}
