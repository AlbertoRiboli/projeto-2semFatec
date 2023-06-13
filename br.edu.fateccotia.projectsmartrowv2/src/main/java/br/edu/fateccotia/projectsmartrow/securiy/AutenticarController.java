package br.edu.fateccotia.projectsmartrow.securiy;

import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticar")
public class AutenticarController {

	@Autowired
	AutenticarService autenticarService;

	public String encriptar(String pwd) {
		String newPwd = autenticarService.encriptar(pwd);
		return newPwd;
	}

	@PostMapping(value = "/validarlogin")
	public ResponseEntity<?> validarLogin(@RequestBody Map<String, String> auth) {
		String emailString = auth.get("email");
		String senhaString = auth.get("senha");
		Boolean isValid = autenticarService.validarLogin(emailString, senhaString);
		if (isValid == null) {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Usuário não Existe na Base de Dados");
		} else if (isValid == false) {
			return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body("Senha Incorreta");
		} else {
			return ResponseEntity.ok().body(isValid + ": Usuário está Autorizado!");
		}
	}

	@PostMapping
	public ResponseEntity<?> validar(@RequestBody Map<String, String> auth) {
		String emailString = auth.get("email");
		String senhaString = auth.get("senha");
		Boolean isValid = autenticarService.validar(emailString, senhaString);
		if (isValid == null) {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Usuário não Existe na Base de Dados");
		} else if (isValid == false) {
			return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body("Senha Incorreta");
		} else {
			return ResponseEntity.ok().body(isValid + ": Usuário está Autorizado!");
		}
	}

	@PostMapping(value = "/validarcliente")
	public ResponseEntity<?> validarCliente(@RequestBody Map<String, String> auth) {
		String emailString = auth.get("email");
		String senhaString = auth.get("senha");
		Boolean isValid = autenticarService.validarCliente(emailString, senhaString);
		if (isValid == null) {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Cliente não Existe na Base de Dados");
		} else if (isValid == false) {
			return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body("Senha Incorreta");
		} else {
			return ResponseEntity.ok().body(isValid + ": Cliente está Autorizado!");
		}
	}
}
