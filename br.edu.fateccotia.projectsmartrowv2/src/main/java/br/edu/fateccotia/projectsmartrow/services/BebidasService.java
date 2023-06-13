package br.edu.fateccotia.projectsmartrow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fateccotia.projectsmartrow.model.Bebidas;
import br.edu.fateccotia.projectsmartrow.repository.BebidasRepository;

@Service
public class BebidasService {

	@Autowired
	BebidasRepository bebidasRepository;
	
	public List<Bebidas> findAll() {
		return bebidasRepository.findAll();
	}
	
	public Bebidas findById(Integer id) {
		Optional<Bebidas> bebidas = bebidasRepository.findById(id);
		return bebidas.orElse(null);
	}

	public Bebidas criarBebidas(Bebidas bebida) {
		bebidasRepository.save(bebida);
		return bebida;
	}

	public Bebidas atualizarBebidas(Integer id, Bebidas bebida) {
		Bebidas newBebidas = findById(id);
		if (bebida.getNome() != null) {
			newBebidas.setNome(bebida.getNome());
		}
		if (bebida.getImagem() != null) {
			newBebidas.setImagem(bebida.getImagem());
		}
		if (bebida.getTipoBebida() != null) {
			newBebidas.setTipoBebida(bebida.getTipoBebida());
		}
		if (bebida.getValor() != null) {
			newBebidas.setValor(bebida.getValor());
		}
		return bebidasRepository.save(newBebidas);
	}

	public void deleteById(Integer id) {
		bebidasRepository.deleteById(id);
	}



}
