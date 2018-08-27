package com.jorge.conductor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jorge.conductor.domain.Portador;

/**
 * Respositório dos portadores contendo funções básicas para executar no banco de dados como CRUD
 */
public interface PortadorRepository extends JpaRepository<Portador, Long> {

}
