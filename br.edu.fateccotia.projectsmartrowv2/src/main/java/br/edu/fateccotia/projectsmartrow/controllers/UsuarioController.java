package br.edu.fateccotia.projectsmartrow.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import br.edu.fateccotia.projectsmartrow.model.Usuario;
import br.edu.fateccotia.projectsmartrow.securiy.AutenticarController;
import br.edu.fateccotia.projectsmartrow.services.EmailService;
import br.edu.fateccotia.projectsmartrow.services.UsuarioService;
import br.edu.fateccotia.projectsmartrow.util.ValidadorDadosEntrada;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	@Autowired
	EstabelecimentoController estabelecimentoController;
	@Autowired
	AutenticarController autenticarController;
	@Autowired
	EmailService emailService;

	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> list = usuarioService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Usuario usuario = usuarioService.findById(id);
		if (usuario == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Usuario Não Encontrado! \nId: " + id + " não corresponde a nenhum usuario cadastrado!");
		}
		return ResponseEntity.ok().body(usuario);
	}

	@PostMapping
	public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario, @RequestParam(name = "cnpj") String cnpj) throws AddressException, MessagingException {
		Estabelecimento newEst = estabelecimentoController.instanciarEstabelecimento(cnpj);
		if (newEst == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Estabelecimento Não Encontrado! Realize primeiro o cadastro do Estabelecimento.");
		}
		Usuario newUsuario = usuarioService.findByEmail(usuario.getEmail());
		if (newUsuario != null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Já existe um cadastro com o email informado!");
		}
		newUsuario = usuarioService.findByCpf(usuario.getCpf());
		if (newUsuario != null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Já existe um cadastro com o cpf informado!");
		}
		if (ValidadorDadosEntrada.validaNome(usuario.getNome()) == false) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("O nome não pode ser vazio");
		}
		if (ValidadorDadosEntrada.validaEmail(usuario.getEmail()) == false) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email Invalido! Digite um email válido!");
		}
		if (usuario.getSenha() == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("A Senha não pode ser vazia! Cadastre a senha!");
		}
		if (ValidadorDadosEntrada.validaCpf(usuario.getCpf()) == false) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("CPF Invalido! CPF deve ter 11 dígitos numéricos!");
		}

		usuario.setNomeEstabelecimento(newEst.getNome());
		usuario.setSenha(autenticarController.encriptar(usuario.getSenha()));
		newUsuario = usuarioService.criarUsuario(usuario);
		emailService.enviarEmailConfirmacao(newUsuario.getEmail(), 
				emailService.titulo(usuario.getNome()), 
				emailService.conteudoEmailValidacaoUsuario(newUsuario));
		return ResponseEntity.ok().body(newUsuario);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> atualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
		usuario.setIDUsuario(id);
		if (usuario.getIDUsuario() == -1) {
			return new ResponseEntity<>("Usuario não Encontrado", HttpStatus.NOT_FOUND);
		}
		Usuario newUsuario = usuarioService.atualizarUsuario(id, usuario);
		return ResponseEntity.ok().body(newUsuario);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		Usuario estVerif = usuarioService.findById(id);
		if (estVerif == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Usuario Não Encontrado! \nId: " + id + " não corresponde a nenhum usuario cadastrado!");
		}
		usuarioService.deleteById(id);
		return new ResponseEntity<>("Usuario Deletado", HttpStatus.ACCEPTED);
	}

	public Usuario instanciarUsuario(Integer id) {
		Usuario usuario = usuarioService.findById(id);
		return usuario;
	}

}
