package br.edu.fateccotia.projectsmartrow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fateccotia.projectsmartrow.model.Usuario;
import br.edu.fateccotia.projectsmartrow.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	public Usuario findById(Integer id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.orElse(null);
	}

	public Usuario criarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
		return usuario;
	}
	
	public Usuario findByEmail(String email) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		return usuario.orElse(null);
	}
	
	public Usuario findByCpf(String cpf) {
		Optional<Usuario> usuario = usuarioRepository.findByCpf(cpf);
		return usuario.orElse(null);
	}

	public Usuario atualizarUsuario(Integer id, Usuario usuario) {
		Usuario newEst = findById(id);
		if (usuario.getNome() != null) {
			newEst.setNome(usuario.getNome());
		}
		if (usuario.getCpf() != null) {
			newEst.setCpf(usuario.getCpf());
		}
		if (usuario.getSenha() != null) {
			newEst.setSenha(usuario.getSenha());
		}
		if (usuario.getEmail() != null) {
			newEst.setEmail(usuario.getEmail());
		}
		return usuarioRepository.save(newEst);
	}

	public void deleteById(Integer id) {
		usuarioRepository.deleteById(id);
	}

}
