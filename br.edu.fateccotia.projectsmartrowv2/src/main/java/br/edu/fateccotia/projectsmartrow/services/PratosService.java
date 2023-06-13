package br.edu.fateccotia.projectsmartrow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fateccotia.projectsmartrow.model.Pratos;
import br.edu.fateccotia.projectsmartrow.repository.PratosRepository;

@Service
public class PratosService {

	@Autowired
	PratosRepository pratosRepository;
	
	public List<Pratos> findAll() {
		return pratosRepository.findAll();
	}
	
	public Pratos findById(Integer id) {
		Optional<Pratos> pratos = pratosRepository.findById(id);
		return pratos.orElse(null);
	}

	public Pratos criarPratos(Pratos prato) {
		pratosRepository.save(prato);
		return prato;
	}

	public Pratos atualizarPratos(Integer id, Pratos prato) {
		Pratos newPrato = findById(id);
		if (prato.getNome() != null) {
			newPrato.setNome(prato.getNome());
		}
		if (prato.getImagem() != null) {
			newPrato.setImagem(prato.getImagem());
		}
		if (prato.getIngredientes() != null) {
			newPrato.setIngredientes(prato.getIngredientes());
		}
		if (prato.getTipoPrato() != null) {
			newPrato.setTipoPrato(prato.getTipoPrato());
		}
		if (prato.getValor() != null) {
			newPrato.setValor(prato.getValor());
		}
		return pratosRepository.save(newPrato);
	}

	public void deleteById(Integer id) {
		pratosRepository.deleteById(id);
	}



}
