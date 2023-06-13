package br.edu.fateccotia.projectsmartrow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.fateccotia.projectsmartrow.model.Mesas;

public interface MesasRepository extends JpaRepository<Mesas, Integer>{
	
	//@Query
	
	//List<Mesas> findByIdEstabelecimento(Integer id) ;
	

}
