package br.edu.fateccotia.projectsmartrow.controllers;

import java.util.List;
import java.util.Map;

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

import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import br.edu.fateccotia.projectsmartrow.securiy.AutenticarController;
import br.edu.fateccotia.projectsmartrow.services.EmailService;
import br.edu.fateccotia.projectsmartrow.services.EstabelecimentoService;
import br.edu.fateccotia.projectsmartrow.util.ValidadorDadosEntrada;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

@RestController
@RequestMapping(value = "/estabelecimentos")
public class EstabelecimentoController {
	
	@Autowired
	EstabelecimentoService estabelecimentoService;
	
	@Autowired
	AutenticarController autenticarController;
	
	@Autowired
	EmailService emailService;
	
	@GetMapping
	public ResponseEntity <List<Estabelecimento>> findAll(){
		List<Estabelecimento> list = estabelecimentoService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		Estabelecimento estabelecimento = estabelecimentoService.findById(id);
		if(estabelecimento == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento Não Encontrado! \nId: " + id + " não corresponde a nenhum estabelecimento cadastrado!");
		}
		return ResponseEntity.ok().body(estabelecimento);
	}
	
	@GetMapping(value = "/email/{email}")
	public ResponseEntity<?> findById(@PathVariable String email){
		Estabelecimento estabelecimento = estabelecimentoService.findByEmail(email);
		if(estabelecimento == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento Não Encontrado! \nEmail: " + email + " não corresponde a nenhum estabelecimento cadastrado!");
		}
		return ResponseEntity.ok().body(estabelecimento);
	}
	
	@GetMapping(value = "/cnpj/{cnpj}")
	public ResponseEntity<?> findByCnpj(@PathVariable String cnpj){
		Estabelecimento estabelecimento = estabelecimentoService.findByCnpj(cnpj);
		if(estabelecimento == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento Não Encontrado! \nCnpj: " + cnpj + " não corresponde a nenhum estabelecimento cadastrado!");
		}
		return ResponseEntity.ok().body(estabelecimento);
	}
	
	
	@PostMapping
	public ResponseEntity<?> criarEstabelecimento(@RequestBody Estabelecimento estabelecimento) throws AddressException, MessagingException {
		Estabelecimento newEst = estabelecimentoService.findByCnpj(estabelecimento.getCnpj());
		if(newEst != null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Já existe um cadastro com o CNPJ informado!");
		}
		if(ValidadorDadosEntrada.validaNome(estabelecimento.getNome()) == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome do Estabelecimento não pode ser vazio ou nulo");
		}
		if(ValidadorDadosEntrada.validaCnpj(estabelecimento.getCnpj()) == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CNPJ Inválido! CNPJ deve conter 14 digitos numéricos");
		}
		if(ValidadorDadosEntrada.validaTelefone(estabelecimento.getTelefone()) == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Telefone Inválido! Informe telefone com DDD (Exemplo: 11000000000 ou 1100000000)");
		}
		if(ValidadorDadosEntrada.validaTelefone(estabelecimento.getTelefone2()) == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Telefone Inválido! Informe telefone com DDD (Exemplo: 11000000000 ou 1100000000)");
		}
		if(ValidadorDadosEntrada.validaEmail(estabelecimento.getEmail()) == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email inválido! Digite um email válido");
		}
		if(ValidadorDadosEntrada.validaHorario(estabelecimento.getHorarioFuncionamento()) == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Horario de Funcionamento não foi preenchido");
		}
		if(estabelecimento.getSenha() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A senha não pode ser nula!");
		}
		estabelecimento.setSenha(autenticarController.encriptar(estabelecimento.getSenha()));
		newEst =  estabelecimentoService.criarEstabelecimento(estabelecimento);
		emailService.enviarEmailConfirmacao(
				newEst.getEmail(), 
				emailService.titulo(newEst.getNome()), 
				emailService.conteudoEmailValidacaoEstabelecimento(newEst));
		newEst =  estabelecimentoService.criarEstabelecimento(estabelecimento);
		return ResponseEntity.ok().body(newEst);
	
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizarEstabelecimento(@PathVariable Integer id, @RequestBody Estabelecimento estabelecimento){
		estabelecimento.setIDEstabelecimento(id);
		if (estabelecimento.getIDEstabelecimento() == -1) {
			return new ResponseEntity<>("Estabelecimento não Encontrado", HttpStatus.NOT_FOUND);
		}
		Estabelecimento newEst = estabelecimentoService.atualizarEstabelecimento(id, estabelecimento);
		return ResponseEntity.ok().body(newEst);
	}
	
	@PutMapping(value = "/atualizarsenha/{id}")
	public ResponseEntity<?> atualizarSenha(@PathVariable Integer id, @RequestBody Map<String, String> senha){
		String novaSenha = senha.get("senha");
		estabelecimentoService.atualizarSenha(id, novaSenha);
		return ResponseEntity.ok().body("Senha Alterada");
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id){
		Estabelecimento estVerif = estabelecimentoService.findById(id);
		if (estVerif == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estabelecimento Não Encontrado! \nId: " + id + " não corresponde a nenhum estabelecimento cadastrado!");
		}
		estabelecimentoService.deleteById(id);
		return new ResponseEntity<>("Estabelecimento Deletado", HttpStatus.ACCEPTED);
		}
	
	public Estabelecimento instanciarEstabelecimento(Integer id) {
		Estabelecimento estabelecimento = estabelecimentoService.findById(id);
		return estabelecimento;
	}
	
	public Estabelecimento instanciarEstabelecimento(String cnpj) {
		Estabelecimento estabelecimento = estabelecimentoService.findByCnpj(cnpj);
		return estabelecimento;
	}
	
	
	
	

}
