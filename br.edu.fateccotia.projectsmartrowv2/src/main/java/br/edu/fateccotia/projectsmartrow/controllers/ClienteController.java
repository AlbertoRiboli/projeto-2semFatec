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

import br.edu.fateccotia.projectsmartrow.model.Cliente;
import br.edu.fateccotia.projectsmartrow.securiy.AutenticarController;
import br.edu.fateccotia.projectsmartrow.services.ClienteService;
import br.edu.fateccotia.projectsmartrow.services.EmailService;
import br.edu.fateccotia.projectsmartrow.util.ValidadorDadosEntrada;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	AutenticarController autenticarController;
	
	@Autowired
	EmailService emailService;
	
	@GetMapping
	public ResponseEntity <List<Cliente>> findAll(){
		List<Cliente> list = clienteService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		Cliente cliente = clienteService.findById(id);
		if(cliente == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente Não Encontrado! \nId: " + id + " não corresponde a nenhum cliente cadastrado!");
		}
		return ResponseEntity.ok().body(cliente);
	}
	
	@GetMapping(value = "/cpf/{cpf}")
	public ResponseEntity<?> findByCpf(@PathVariable String cpf){
		Cliente cliente = clienteService.findByCpf(cpf);
		if(cliente == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente Não Encontrado! \nCPF: " + cpf + " não corresponde a nenhum cliente cadastrado!");
		}
		return ResponseEntity.ok().body(cliente);
	}
	
	@GetMapping(value = "/email/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable String email){
		Cliente cliente = clienteService.findByEmail(email);
		if(cliente == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente Não Encontrado! \nEmail: " + email + " não corresponde a nenhum cliente cadastrado!");
		}
		return ResponseEntity.ok().body(cliente);
	}
	
	
	
	@PostMapping
	public ResponseEntity<?> criarCliente(@RequestBody Cliente cliente) throws jakarta.mail.internet.AddressException, jakarta.mail.MessagingException {
		Cliente newCliente = clienteService.findByCpf(cliente.getCpf());
		if(newCliente != null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Já existe um cadastro com o CPF informado!");
		}
		newCliente = clienteService.findByEmail(cliente.getEmail());
		if(newCliente != null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Já existe um cadastro com o email informado!");
		}
		if(ValidadorDadosEntrada.validaNome(cliente.getNome()) == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome do Cliente não pode ser vazio ou nulo");
		}
		if(ValidadorDadosEntrada.validaCpf(cliente.getCpf()) == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF Inválido! O CPF deve conter 11 digitos numéricos!");
		}
		if(ValidadorDadosEntrada.validaTelefone(cliente.getTelefone()) == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Telefone Inválido! Informe telefone com DDD (Exemplo: 11000000000 ou 1100000000)");
		}
		if(ValidadorDadosEntrada.validaEmail(cliente.getEmail()) == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email inválido! Digite um email válido");
		}
		if(cliente.getSenha() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A senha não pode ser nula!");
		}
		cliente.setSenha(autenticarController.encriptar(cliente.getSenha()));
		newCliente =  clienteService.criarCliente(cliente);
		emailService.enviarEmailConfirmacao(
				newCliente.getEmail(), 
				emailService.titulo(newCliente.getNome()), 
				emailService.conteudoEmailValidacao(newCliente));
		return ResponseEntity.ok().body(newCliente);
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente){
		cliente.setIDCliente(id);
		if (cliente.getIDCliente() == -1) {
			return new ResponseEntity<>("Cliente não Encontrado", HttpStatus.NOT_FOUND);
		}
		Cliente newCliente = clienteService.atualizarCliente(id, cliente);
		return ResponseEntity.ok().body(newCliente);
		
	}
	
	@PutMapping(value = "/cpf/{cpf}")
	public ResponseEntity<?> atualizarCliente(@PathVariable String cpf, @RequestBody Cliente cliente){
		Cliente newCliente = clienteService.findByCpf(cpf);
		if (newCliente == null) {
			return new ResponseEntity<>("Cliente não Encontrado", HttpStatus.NOT_FOUND);
		}
		newCliente = clienteService.atualizarClientePorCpf(cpf, cliente);
		return ResponseEntity.ok().body(newCliente);
		
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id){
		Cliente clienteVerif = clienteService.findById(id);
		if (clienteVerif == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente Não Encontrado! \nId: " + id + " não corresponde a nenhum cliente cadastrado!");
		}
		clienteService.deleteById(id);
		return new ResponseEntity<>("Cliente Deletado", HttpStatus.ACCEPTED);
		}
	
	public Cliente instanciarCliente(Integer id) {
		Cliente cliente = clienteService.findById(id);
		return cliente;
	}
	
	public Cliente instanciarCliente(String cpf) {
		Cliente cliente = clienteService.findByCpf(cpf);
		return cliente;
	}
	
	
	
	

}
