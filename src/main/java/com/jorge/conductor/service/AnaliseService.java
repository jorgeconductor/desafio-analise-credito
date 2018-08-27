package com.jorge.conductor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorge.conductor.ConductorServiceApp;
import com.jorge.conductor.domain.Analise;
import com.jorge.conductor.domain.Portador;
import com.jorge.conductor.dto.AnaliseDTO;
import com.jorge.conductor.repository.AnaliseRepository;
import com.jorge.conductor.repository.PortadorRepository;

/**
 * Classe que oferece serviços de receber e filtrar dados da requisição via REST
 * como deletar, adicionar ou atualizar cada campo
 */
@Service
@Transactional
public class AnaliseService {

	// Invoca o repositório das análises e seus respectivos comandos JPA
	@Autowired
	private AnaliseRepository analiseRepository;

	// Invoca o repositório do portador e seus respectivos comandos JPA
	@Autowired
	private PortadorRepository portadorRepository;

	private static List<Analise> analises = new ArrayList<>();

	static {
	}

	/**
	 * Adiciona informações iniciais ao banco, caso o mesmo esteja vazio
	 */
	public void saveInitialBatch() {
		analiseRepository.save(analises);
		analises.forEach((analise) -> {
			ConductorServiceApp.jmsTemplate.convertAndSend("salvaranalise", analise);
		});
	}

	/**
	 * Encontra e retorna todos os dados da análise na tabela da análise no banco de
	 * dados
	 * 
	 * @return lista de análises de créditos encontrados
	 */
	public List<Analise> findAll() {
		return analiseRepository.findAll();
	}
	
	/**
	 * Retorna os dados de análise que estão pendentes
	 * @return lista de análise pendentes
	 */
	public List<Analise> findPendentes() {
		return analiseRepository.findByAprovado(null);
	}

	/**
	 * Salva os dados da análise requisitados, ao banco de dados
	 * 
	 * @param analiseDTO
	 *            dados vindo da view para ser persistido
	 * @return os dados da análise salvo
	 */
	public Analise salvarAnalise(AnaliseDTO analiseDTO) {
		Analise analise = new Analise();
		Portador portador = portadorRepository.findOne(analiseDTO.getPortadorId());
		analise.setPortador(portador);
		analise.setAprovado(analiseDTO.getAprovado());
		analise.setDataAnalise(new Date());
		ConductorServiceApp.jmsTemplate.convertAndSend("salvaranalise", analise);
		return analiseRepository.save(analise);
	}

	/**
	 * Salva os dados da análise requisitados, ao banco de dados
	 * 
	 * @param portador
	 *            dados do portador que está sendo inserido a análise inicial
	 * @return os dados da análise salvo
	 */
	public Analise criarAnaliseInicial(Portador portador) {
		Analise analise = new Analise();
		analise.setPortador(portador);
		analise.setAprovado(null);
		analise.setDataAnalise(null);
		ConductorServiceApp.jmsTemplate.convertAndSend("salvaranalise", analise);
		return analiseRepository.save(analise);
	}

	/**
	 * Atualiza os dados de uma única análise, pesquisando pelo o ID do
	 * mesmo
	 * 
	 * @param analiseDTO
	 *            a análise a ser editado os dados
	 * @param id
	 *            o ID da análise a ser pesquisado
	 * @return os dados editados salvos no banco da análise
	 */
	public Analise atualizarAnalise(AnaliseDTO analiseDTO, Long id) {
		Analise atualizarAnalise = analiseRepository.findOne(id);
		Portador portador = portadorRepository.findOne(analiseDTO.getPortadorId());
		atualizarAnalise.setPortador(portador);
		atualizarAnalise.setAprovado(analiseDTO.getAprovado());
		atualizarAnalise.setDataAnalise(new Date());
		ConductorServiceApp.jmsTemplate.convertAndSend("atualizaranalise", atualizarAnalise);
		return analiseRepository.save(atualizarAnalise);
	}

	/**
	 * Deleta a análise específica, baseado no ID
	 * 
	 * @param id
	 *            o ID da análise a ser pesquisado e deletado
	 */
	public void deletarAnalise(Long id) {
		ConductorServiceApp.jmsTemplate.convertAndSend("deletaranalise", id);
		analiseRepository.delete(id);
	}

	/**
	 * Apaga todos os dados da análise da tabela
	 * 
	 * @param object
	 */
	public void limparAnalises(Object object) {
		ConductorServiceApp.jmsTemplate.convertAndSend("limparanalises", new Date());
		analiseRepository.delete(findAll());
	}

}
