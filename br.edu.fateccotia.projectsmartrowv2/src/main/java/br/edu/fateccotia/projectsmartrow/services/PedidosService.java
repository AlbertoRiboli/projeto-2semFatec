package br.edu.fateccotia.projectsmartrow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fateccotia.projectsmartrow.model.Pedidos;
import br.edu.fateccotia.projectsmartrow.repository.PedidosRepository;

@Service
public class PedidosService {

	@Autowired
	PedidosRepository pedidosRepository;
	
	public List<Pedidos> findAll() {
		return pedidosRepository.findAll();
	}
	
	public Pedidos findById(Integer id) {
		Optional<Pedidos> pedidos = pedidosRepository.findById(id);
		return pedidos.orElse(null);
	}
	
//	public Pedidos findByCnpj(String cnpj) {
//		Optional<Pedidos> pedidos = pedidosRepository.findByCnpj(cnpj);
//		return pedidos.orElse(null);
//	}

	public Pedidos criarPedidos(Pedidos pedidos) {
		pedidosRepository.save(pedidos);
		return pedidos;
	}

	public Pedidos atualizarPedidos(Integer id, Pedidos pedidos) {
		Pedidos newPedido = findById(id);
		if (pedidos.getInstant() != null) {
			newPedido.setInstant(pedidos.getInstant());
		}
		if (pedidos.getCliente() != null) {
			newPedido.setCliente(pedidos.getCliente());
		}
		if (pedidos.getEstabelecimento() != null) {
			newPedido.setEstabelecimento(pedidos.getEstabelecimento());
		}
		if (pedidos.getMesa() != null) {
			newPedido.setMesa(pedidos.getMesa());
		}
		
		
		return pedidosRepository.save(newPedido);
	}

	public void deleteById(Integer id) {
		pedidosRepository.deleteById(id);
	}

}
