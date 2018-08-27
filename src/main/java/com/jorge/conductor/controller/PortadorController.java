package com.jorge.conductor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jorge.conductor.domain.Portador;
import com.jorge.conductor.dto.PortadorDTO;
import com.jorge.conductor.service.PortadorService;

/**
 * Classe responsável por configurar todas as execuções via REST do Sprint Boot
 */
@RestController
@RequestMapping("/portador")
public class PortadorController {

	// Invoca o service do portador para ser efetuado execuções geralmente
	// para o banco de dados
	@Autowired
	private PortadorService portadorService;

	/**
	 * Adiciona um novo portador baseado nas informações passadas
	 * 
	 * @param portadorDTO
	 *            dados do portador passado nos parâmetros
	 * @return os dados salvos do portador ao banco de dados
	 */
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public Portador adicionarPortador(@RequestBody PortadorDTO portadorDTO) {
		return portadorService.salvarPortador(portadorDTO);
	}

	/**
	 * Retorna todos os itens dos portadores presentes no banco de dados
	 * 
	 * @return todos os itens dos portadores em um array
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Portador> getAll() {
		return portadorService.findAll();
	}

	/**
	 * Edita os dados dos portadores específico e salva no banco
	 * 
	 * @param portadorDTO
	 *            o portador a ser editado
	 * @param ids
	 *            ID do portador a ser encontrado e editado
	 * @return os dados editados do portador
	 */
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json", value = "/{id}")
	public Portador atualizarPortador(@RequestBody PortadorDTO portadorDTO, @PathVariable("id") Long ids) {
		return portadorService.atualizarPortador(portadorDTO, ids);
	}

	/**
	 * Item do portador a ser deletado
	 * 
	 * @param ids
	 *            número do ID do portador a ser deletado
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deletarPortador(@PathVariable("id") Long ids) {
		portadorService.deletarPortador(ids);
	}

	/**
	 * Limpa todos os dados dos portadores presentes no banco de dados
	 * 
	 * @param object
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public void limparPortadores(Object object) {
		portadorService.limparPortadores(object);
	}

}
