package br.edu.fateccotia.projectsmartrow.controllers;

import java.util.List;
import java.util.Scanner;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fateccotia.projectsmartrow.model.Bebidas;
import br.edu.fateccotia.projectsmartrow.model.Cliente;
import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import br.edu.fateccotia.projectsmartrow.model.Mesas;
import br.edu.fateccotia.projectsmartrow.model.Pedidos;
import br.edu.fateccotia.projectsmartrow.model.Pratos;
import br.edu.fateccotia.projectsmartrow.securiy.AutenticarService;
import br.edu.fateccotia.projectsmartrow.services.BebidasService;
import br.edu.fateccotia.projectsmartrow.services.ClienteService;
import br.edu.fateccotia.projectsmartrow.services.EstabelecimentoService;
import br.edu.fateccotia.projectsmartrow.services.MesasService;
import br.edu.fateccotia.projectsmartrow.services.PedidosService;
import br.edu.fateccotia.projectsmartrow.services.PratosService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidosController {
	
	@Autowired
	PedidosService pedidosService;
	@Autowired
	EstabelecimentoService estabelecimentoService;
	@Autowired
	MesasService mesaService;
	@Autowired
	ClienteService clienteService;
	@Autowired
	PratosService pratosService;
	@Autowired
	BebidasService bebidasService;
	@Autowired
	AutenticarService autenticarService;
	
	@GetMapping
	public ResponseEntity <List<Pedidos>> findAll(){
		List<Pedidos> list = pedidosService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		Pedidos pedidos = pedidosService.findById(id);
		if(pedidos == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedidos Não Encontrado! \nId: " + id + " não corresponde a nenhum pedido cadastrado!");
		}
		return ResponseEntity.ok().body(pedidos);
	}
	
	@PostMapping
	public ResponseEntity<?> criarPedidos(
			@RequestParam(value = "idestab", required = true) Integer idestab, 
			@RequestParam(value = "idcliente", required = true) Integer idcliente, 
			@RequestParam(value = "idmesa", required = true) Integer idmesa) {
		Estabelecimento estabelecimento = estabelecimentoService.findById(idestab);
		if(estabelecimento == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento Não Encontrado!");
		}
		Cliente cliente = clienteService.findById(idcliente);
		if(cliente == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente Não Encontrado!");
		}
		Mesas mesa = mesaService.findById(idmesa);
		if(mesa == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mesa não cadastrada!");
		}
		Pedidos pedido = new Pedidos(cliente, estabelecimento, mesa);
		Pedidos newPedido =  pedidosService.criarPedidos(pedido);
		return ResponseEntity.ok().body(newPedido);
		
	}
	
	@PostMapping("/criarpedidoqr")
	public ResponseEntity<?> criarPedidosQr(
			@RequestParam(value = "idestab", required = true) Integer idestab, 
						@RequestParam(value = "idmesa", required = true) Integer idmesa) {
		Estabelecimento estabelecimento = estabelecimentoService.findById(idestab);
		if(estabelecimento == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento Não Encontrado!");
		}
		Mesas mesa = mesaService.findById(idmesa);
		if(mesa == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mesa não cadastrada!");
		}
		
		
		//Método temporário em console e 
		//terminal apenas para identificar o funcionamento
		//do QRCODE - Em Breve, implementação no Frontend
		Scanner sc = new Scanner(System.in);
		System.out.println("#############################");
		System.out.println("#        AUTENTICAÇÃO       #");
		System.out.println("#############################");
		System.out.print("DIGITE O EMAIL DO CLIENTE: ");
		String emailCliente = sc.nextLine();
		Cliente cliente = clienteService.findByEmail(emailCliente);
		if(cliente == null) {
			System.out.println("Cliente não encontrado");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente Não Encontrado!");
		}
		System.out.print("DIGITE A SENHA DO CLIENTE: ");
		String senha = sc.nextLine();
		Boolean isValid = autenticarService.validarCliente(emailCliente, senha);
		if (isValid == false) {
			System.out.println("#############################");
			System.out.println("#USUÁRIO OU SENHA INVALIDOS #");
			System.out.println("#############################");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário ou Senha Invalidos!");
		}
		
		Pedidos pedido = new Pedidos(cliente, estabelecimento, mesa);
		Pedidos newPedido =  pedidosService.criarPedidos(pedido);
		return ResponseEntity.ok().body(newPedido);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizarPedidos(@PathVariable Integer id, @RequestBody Pedidos pedidos){
		pedidos.setIDPedido(id);
		if (pedidos.getIDPedido() == -1) {
			return new ResponseEntity<>("Pedido não Encontrado", HttpStatus.NOT_FOUND);
		}
		Pedidos newPedido = pedidosService.atualizarPedidos(id, pedidos);
		return ResponseEntity.ok().body(newPedido);
		
	}
	
	@PutMapping(path = "/adicionarprato")
	public ResponseEntity<?> adicionarPratosEmPedido(
			@RequestParam(value = "idpedido", required = true) Integer idPedido, 
			@RequestParam(value = "idprato", required = true) Integer idPrato){
		Pedidos pedido = pedidosService.findById(idPedido);
		pedido.setIDPedido(idPedido);
		if (pedido.getIDPedido() == -1) {
			return new ResponseEntity<>("Pedido não Encontrado", HttpStatus.NOT_FOUND);
		}
		Pratos prato = pratosService.findById(idPrato);
		pedido.addPratoEmLista(prato);
		pedido.setTotal();
		Pedidos newPedido = pedidosService.atualizarPedidos(idPedido, pedido);
		return ResponseEntity.ok().body(newPedido);
	}
	
	@PutMapping(path = "/adicionarbebida")
	public ResponseEntity<?> adicionarBebidasEmPedido(
			@RequestParam(value = "idpedido", required = true) Integer idPedido, 
			@RequestParam(value = "idbebida", required = true) Integer idBebida){
		Pedidos pedido = pedidosService.findById(idPedido);
		pedido.setIDPedido(idPedido);
		if (pedido.getIDPedido() == -1) {
			return new ResponseEntity<>("Pedido não Encontrado", HttpStatus.NOT_FOUND);
		}
		Bebidas bebida = bebidasService.findById(idBebida);
		pedido.addBebidaEmLista(bebida);
		pedido.setTotal();
		Pedidos newPedido = pedidosService.atualizarPedidos(idPedido, pedido);
		return ResponseEntity.ok().body(newPedido);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id){
		Pedidos pedidoVerif = pedidosService.findById(id);
		if (pedidoVerif == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido Não Encontrado! \nId: " + id + " não corresponde a nenhum pedido cadastrado!");
		}
		pedidosService.deleteById(id);
		return new ResponseEntity<>("Pedido Deletado", HttpStatus.ACCEPTED);
		}
	
	public Pedidos instanciarPedidos(Integer id) {
		Pedidos pedidos = pedidosService.findById(id);
		return pedidos;
	}
	

	
	
	

}
