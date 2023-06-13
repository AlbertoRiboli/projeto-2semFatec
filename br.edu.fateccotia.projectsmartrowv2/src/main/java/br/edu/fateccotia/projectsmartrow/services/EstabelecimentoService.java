package br.edu.fateccotia.projectsmartrow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import br.edu.fateccotia.projectsmartrow.repository.EstabelecimentoRepository;
import br.edu.fateccotia.projectsmartrow.securiy.AutenticarController;

@Service
public class EstabelecimentoService {

	@Autowired
	EstabelecimentoRepository estabelecimentoRepository;
	
	@Autowired
	AutenticarController autenticarController;
	
	public List<Estabelecimento> findAll() {
		return estabelecimentoRepository.findAll();
	}
	
	public Estabelecimento findById(Integer id) {
		Optional<Estabelecimento> estabelecimento = estabelecimentoRepository.findById(id);
		return estabelecimento.orElse(null);
	}
	
	public Estabelecimento findByCnpj(String cnpj) {
		Optional<Estabelecimento> estabelecimento = estabelecimentoRepository.findByCnpj(cnpj);
		return estabelecimento.orElse(null);
	}
	
	public Estabelecimento findByEmail(String email) {
		Optional<Estabelecimento> estabelecimento = estabelecimentoRepository.findByEmail(email);
		return estabelecimento.orElse(null);
	}

	public Estabelecimento criarEstabelecimento(Estabelecimento estabelecimento) {
		estabelecimentoRepository.save(estabelecimento);
		return estabelecimento;
	}

	public Estabelecimento atualizarEstabelecimento(Integer id, Estabelecimento estabelecimento) {
		Estabelecimento newEst = findById(id);
		if (estabelecimento.getNome() != null) {
			newEst.setNome(estabelecimento.getNome());
		}
		if (estabelecimento.getCnpj() != null) {
			newEst.setCnpj(estabelecimento.getCnpj());
		}
		if (estabelecimento.getTelefone() != null) {
			newEst.setTelefone(estabelecimento.getTelefone());
		}
		if (estabelecimento.getTelefone2() != null) {
			newEst.setTelefone2(estabelecimento.getTelefone2());
		}
		if (estabelecimento.getEmail() != null) {
			newEst.setEmail(estabelecimento.getEmail());
		}
		if (estabelecimento.getHorarioFuncionamento() != null) {
			newEst.setHorarioFuncionamento(estabelecimento.getHorarioFuncionamento());
		}
		if (estabelecimento.getImagemEstabelecimento() != null) {
			newEst.setImagemEstabelecimento(estabelecimento.getImagemEstabelecimento());
		}
		if (estabelecimento.getFaturamento() != null) {
			newEst.setFaturamento(estabelecimento.getFaturamento());
		}
		if (estabelecimento.getCardapio() != null) {
			newEst.setCardapio(estabelecimento.getCardapio());
		}
		if (estabelecimento.getEndereco() != null) {
			newEst.setEndereco(estabelecimento.getEndereco());
		}
//		if (estabelecimento.getMesas() != null) {
//			newEst.setMesas(estabelecimento.getMesas());
//		}
		if (estabelecimento.getCategoriaEstabelecimento() != null) {
			newEst.setCategoriaEstabelecimento(estabelecimento.getCategoriaEstabelecimento());
		}
		if (estabelecimento.isEmailIsValid() != false) {
			newEst.setEmailIsValid(estabelecimento.isEmailIsValid());
		}
		return estabelecimentoRepository.save(newEst);
	}

	public void deleteById(Integer id) {
		estabelecimentoRepository.deleteById(id);
	}
	
	public void atualizarSenha(Integer id, String senha) {
		Optional<Estabelecimento> estabelecimento = estabelecimentoRepository.findById(id);
		estabelecimento.get().setSenha(autenticarController.encriptar(senha));
		estabelecimentoRepository.save(estabelecimento.get());
	}

	

}
