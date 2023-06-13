package br.edu.fateccotia.projectsmartrow.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fateccotia.projectsmartrow.model.Cardapio;
import br.edu.fateccotia.projectsmartrow.repository.CardapioRepository;

@Service
public class CardapioService {

	@Autowired
	CardapioRepository cardapioRepository;
	
	public List<Cardapio> findAll() {
		return cardapioRepository.findAll();
	}
	
	public Cardapio findById(Integer id) {
		Optional<Cardapio> cardapio = cardapioRepository.findById(id);
		return cardapio.orElse(null);
	}

	public Cardapio criarCardapio(Cardapio cardapio) {
		cardapioRepository.save(cardapio);
		return cardapio;
	}

	public Cardapio atualizarCardapio(Integer id, Cardapio cardapio) {
		Cardapio newCardapio = findById(id);
		if (cardapio.getBebidas() != null) {
			newCardapio.setListBebidas(cardapio.getBebidas());
		}
		if (cardapio.getPratos() != null) {
			newCardapio.setListPratos(cardapio.getPratos());
		}
		return cardapioRepository.save(newCardapio);
	}

	public void deleteById(Integer id) {
		cardapioRepository.deleteById(id);
	}



}
