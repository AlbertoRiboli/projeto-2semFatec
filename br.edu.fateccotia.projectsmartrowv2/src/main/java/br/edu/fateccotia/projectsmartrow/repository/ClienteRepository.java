package br.edu.fateccotia.projectsmartrow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fateccotia.projectsmartrow.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	Optional<Cliente> findByCpf (String cpf);
	Optional<Cliente> findByEmail (String email);
}
