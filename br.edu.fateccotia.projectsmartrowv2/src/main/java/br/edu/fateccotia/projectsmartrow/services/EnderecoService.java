package br.edu.fateccotia.projectsmartrow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fateccotia.projectsmartrow.model.Endereco;
import br.edu.fateccotia.projectsmartrow.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public List<Endereco> findAll() {
		return enderecoRepository.findAll();
	}
	
	public Endereco criarEndereco(String cep, String num, String comp, String ref) {
		Endereco endereco = BuscaCepAPI.buscaCep(cep);
		endereco.setNumero(num);
		endereco.setComplemento(comp);
		endereco.setReferencia(ref);
		enderecoRepository.save(endereco);
		return endereco;
	}
	
	public Endereco findById(Integer id) {
		Optional<Endereco> obj = enderecoRepository.findById(id);
		return obj.orElse(null);
	}

	public Endereco atualizarEndereco(Integer id, Endereco endereco) {
		Endereco newEnd = findById(id);
		if (endereco.getCep() != null) {
			newEnd.setCep(endereco.getCep());
		}
		if (endereco.getLogradouro() != null) {
			newEnd.setLogradouro(endereco.getLogradouro());
		}
		if (endereco.getNumero() != null) {
			newEnd.setNumero(endereco.getNumero());
		}
		if (endereco.getComplemento() != null) {
			newEnd.setComplemento(endereco.getComplemento());
		}
		if (endereco.getReferencia() != null) {
			newEnd.setReferencia(endereco.getReferencia());
		}
		if (endereco.getBairro() != null) {
			newEnd.setBairro(endereco.getBairro());
		}
		if (endereco.getLocalidade() != null) {
			newEnd.setLocalidade(endereco.getLocalidade());
		}
		if (endereco.getUf() != null) {
			newEnd.setUf(endereco.getUf());
		}
		if (endereco.getPais() != null) {
			newEnd.setPais(endereco.getPais());
		}
		return enderecoRepository.save(newEnd);
	}

	public void deleteById(Integer id) {
		enderecoRepository.deleteById(id);
	}

}
