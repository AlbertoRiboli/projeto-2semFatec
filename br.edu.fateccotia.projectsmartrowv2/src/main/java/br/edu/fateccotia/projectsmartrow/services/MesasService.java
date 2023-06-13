package br.edu.fateccotia.projectsmartrow.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fateccotia.projectsmartrow.model.Mesas;
import br.edu.fateccotia.projectsmartrow.model.Mesas;
import br.edu.fateccotia.projectsmartrow.repository.MesasRepository;

@Service
public class MesasService {

	@Autowired
	MesasRepository mesasRepository;
	
	public List<Mesas> findAll() {
		return mesasRepository.findAll();
	}
	
	public Mesas findById(Integer id) {
		Optional<Mesas> mesa = mesasRepository.findById(id);
		return mesa.orElse(null);
	}

	public Mesas criarMesas(Mesas mesa) {
		mesasRepository.save(mesa);
		return mesa;
	}

	public Mesas atualizarMesas(Integer id, Mesas mesa) {
		Mesas newMesa = findById(id);
		if (mesa.getNumMesa() != null) {
			newMesa.setNumMesa(mesa.getNumMesa());
		}
		if (mesa.getCapacidadePessoas() != null) {
			newMesa.setCapacidadePessoas(mesa.getCapacidadePessoas());
		}
		return mesasRepository.save(newMesa);
	}

	public void deleteById(Integer id) {
		mesasRepository.deleteById(id);
	}

	

}
