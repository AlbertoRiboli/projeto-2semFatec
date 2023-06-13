package br.edu.fateccotia.projectsmartrow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Integer>{

	Optional<Estabelecimento> findByCnpj (String cnpj);

	Optional<Estabelecimento> findByEmail(String email);
}
