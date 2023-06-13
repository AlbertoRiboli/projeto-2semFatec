package br.edu.fateccotia.projectsmartrow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fateccotia.projectsmartrow.model.Cliente;
import br.edu.fateccotia.projectsmartrow.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElse(null);
	}
	
	public Cliente findByEmail(String email) {
		Optional<Cliente> cliente = clienteRepository.findByEmail(email);
		return cliente.orElse(null);
	}
	
	public Cliente findByCpf(String cpf) {
		Optional<Cliente> cliente = clienteRepository.findByCpf(cpf);
		return cliente.orElse(null);
	}

	public Cliente criarCliente(Cliente cliente) {
		clienteRepository.save(cliente);
		return cliente;
	}

	public Cliente atualizarCliente(Integer id, Cliente cliente) {
		Cliente newCliente = findById(id);
		if (cliente.getNome() != null) {
			newCliente.setNome(cliente.getNome());
		}
		if (cliente.getCpf() != null) {
			newCliente.setCpf(cliente.getCpf());
		}
		if (cliente.getTelefone() != null) {
			newCliente.setTelefone(cliente.getTelefone());
		}
		if (cliente.getRg() != null) {
			newCliente.setRg(cliente.getRg());
		}
		if (cliente.getEmail() != null) {
			newCliente.setEmail(cliente.getEmail());
		}
		if (cliente.getEndereco() != null) {
			newCliente.setEndereco(cliente.getEndereco());
		}
		if (cliente.isEmailIsValid() != false) {
			newCliente.setEmailIsValid(cliente.isEmailIsValid());
		}
		
		
		return clienteRepository.save(newCliente);
	}
	
	public Cliente atualizarClientePorCpf(String cpf, Cliente cliente) {
		Cliente newCliente = findByCpf(cpf);
		if (cliente.getNome() != null) {
			newCliente.setNome(cliente.getNome());
		}
		if (cliente.getCpf() != null) {
			newCliente.setCpf(cliente.getCpf());
		}
		if (cliente.getTelefone() != null) {
			newCliente.setTelefone(cliente.getTelefone());
		}
		if (cliente.getRg() != null) {
			newCliente.setRg(cliente.getRg());
		}
		if (cliente.getEmail() != null) {
			newCliente.setEmail(cliente.getEmail());
		}
		if (cliente.getEndereco() != null) {
			newCliente.setEndereco(cliente.getEndereco());
		}
		if (cliente.isEmailIsValid() != false) {
			newCliente.setEmailIsValid(cliente.isEmailIsValid());
		}
		
		
		return clienteRepository.save(newCliente);
	}

	public void deleteById(Integer id) {
		clienteRepository.deleteById(id);
	}

}
