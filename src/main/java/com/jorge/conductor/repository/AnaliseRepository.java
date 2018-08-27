package com.jorge.conductor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jorge.conductor.domain.Analise;

/**
 * Respositório das análises contendo funções básicas para executar no banco de dados como CRUD
 */
public interface AnaliseRepository extends JpaRepository<Analise, Long> {
	
	/**
	 * Encontra todas as análises de crédito usando o ID do portador como referência
	 * @param id ID do portador a ser encontrada
	 * @return a lista de todas as análises encontradas baseados no ID do portador
	 */
	@Query(value = "SELECT * FROM analise a WHERE a.portador = :portadorId", nativeQuery = true)
	public List<Analise> findByPortador(@Param("portadorId") Long id);
	
	List<Analise> findByAprovado(Boolean status);

}
