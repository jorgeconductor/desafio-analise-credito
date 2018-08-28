package com.jorge.conductor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jorge.conductor.domain.Funcionario;

/**
 * Respositório dos funcionários contendo funções básicas para executar no banco de dados como CRUD
 */
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	Funcionario findByEmail(String email);
    
}
