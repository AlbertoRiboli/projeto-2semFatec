package br.edu.fateccotia.projectsmartrow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fateccotia.projectsmartrow.model.Cliente;
import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import br.edu.fateccotia.projectsmartrow.model.Usuario;
import br.edu.fateccotia.projectsmartrow.services.ClienteService;
import br.edu.fateccotia.projectsmartrow.services.EmailService;
import br.edu.fateccotia.projectsmartrow.services.EstabelecimentoService;
import br.edu.fateccotia.projectsmartrow.services.UsuarioService;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	EmailService emailService;
	@Autowired
	ClienteService clienteService;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	EstabelecimentoService estabelecimentoService;
	
	
	@GetMapping(value = "/validaremail")
	public ResponseEntity<?> validarEmailCliente(@RequestParam(value = "id") Integer id){
		Cliente cliente = clienteService.findById(id);
		if(cliente.isEmailIsValid() == true) {
			return ResponseEntity.ok().body("O Cadastro já foi validado! :)");
		} 
		cliente.setEmailIsValid(true);
		Cliente newCliente = clienteService.atualizarCliente(id, cliente);
		
		return ResponseEntity.ok().body("*****\n"
				+ "Obrigado por Confirmar seu Email! O Seu cadastro está ativo! :)\n"
				+ "*****");
		
	}
	
	@GetMapping(value = "/validaremailusuario")
	public ResponseEntity<?> validarEmailUsuario(@RequestParam(value = "id") Integer id){
		Usuario usuario = usuarioService.findById(id);
		if(usuario.isEmailIsValid() == true) {
			return ResponseEntity.ok().body("O Cadastro já foi validado! :)");
		} 
		usuario.setEmailIsValid(true);
		Usuario newUsuario = usuarioService.atualizarUsuario(id, usuario);
		
		return ResponseEntity.ok().body("*****\n"
				+ "Obrigado por Confirmar seu Email! O Seu cadastro está ativo! :)\n"
				+ "*****");
		
	}
	
	@GetMapping(value = "/validaremailestabelecimento")
	public ResponseEntity<?> validarEmailEstabelecimento(@RequestParam(value = "id") Integer id){
		Estabelecimento estabelecimento = estabelecimentoService.findById(id);
		if(estabelecimento.isEmailIsValid() == true) {
			return ResponseEntity.ok().body("O Cadastro já foi validado! :)");
		} 
		estabelecimento.setEmailIsValid(true);
		Estabelecimento newEst = estabelecimentoService.atualizarEstabelecimento(id, estabelecimento);
		
		return ResponseEntity.ok().body("*****\n"
				+ "Obrigado por Confirmar seu Email! O Seu cadastro está ativo! :)\n"
				+ "*****");
		
	}
	
	

}
