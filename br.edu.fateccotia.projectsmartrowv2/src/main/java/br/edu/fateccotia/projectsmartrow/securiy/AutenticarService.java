package br.edu.fateccotia.projectsmartrow.securiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.fateccotia.projectsmartrow.model.Cliente;
import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import br.edu.fateccotia.projectsmartrow.model.Usuario;
import br.edu.fateccotia.projectsmartrow.services.ClienteService;
import br.edu.fateccotia.projectsmartrow.services.EstabelecimentoService;
import br.edu.fateccotia.projectsmartrow.services.UsuarioService;

@Service
public class AutenticarService {

	@Autowired
	UsuarioService usuarioService;
	@Autowired
	ClienteService clienteService;
	@Autowired
	EstabelecimentoService estabelecimentoService;
	@Autowired
	PasswordEncoder passwordEncoder;

	public String encriptar(String pwd) {
		String newPwd = passwordEncoder.encode(pwd);
		return newPwd;
	}

	public Boolean validar(String email, String senha) {
		Boolean isValid = null;
		Usuario user = usuarioService.findByEmail(email);
		if (user == null) {
			return null;
		}
		Usuario userValidation = user;
		isValid = passwordEncoder.matches(senha, userValidation.getSenha());
		return isValid;
	}

	public Boolean validarCliente(String email, String senha) {
		Boolean isValid = null;
		Cliente cliente = clienteService.findByEmail(email);
		if (cliente == null) {
			return null;
		}
		Cliente clienteValidation = cliente;
		isValid = passwordEncoder.matches(senha, clienteValidation.getSenha());
		return isValid;
	}

	public Boolean validarLogin(String email, String senha) {
		Boolean isValid = null;
		Cliente cliente = clienteService.findByEmail(email);
		if (cliente == null) {
			Estabelecimento estabelecimento = estabelecimentoService.findByEmail(email);
			if (estabelecimento == null) {
				return null;
			}
			isValid = passwordEncoder.matches(senha, estabelecimento.getSenha());
			return isValid;
		}
		Cliente clienteValidation = cliente;
		isValid = passwordEncoder.matches(senha, clienteValidation.getSenha());
		return isValid;
	}

}
