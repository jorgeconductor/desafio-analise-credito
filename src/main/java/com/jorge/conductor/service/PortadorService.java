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
import com.jorge.conductor.dto.PortadorDTO;
import com.jorge.conductor.repository.AnaliseRepository;
import com.jorge.conductor.repository.PortadorRepository;

/**
 * Classe que oferece serviços de receber e filtrar dados da requisição via REST
 * como deletar, adicionar ou atualizar cada campo
 */
@Service
@Transactional
public class PortadorService {

	// Invoca o repositório dos portadores e seus respectivos comandos JPA
	@Autowired
	private PortadorRepository portadorRepository;

	// Invoca o repositório das análises e seus respectivos comandos JPA
	@Autowired
	private AnaliseRepository analiseRepository;
	
	// Invoca o serviço da análise para ser efetuado execuções geralmente para o banco de dados
	@Autowired
    private AnaliseService analiseService;

	private static List<PortadorDTO> portadores = new ArrayList<>();

	static {
		portadores.add(new PortadorDTO("Jorge", "jorge@gmail.com", "Rua José Branco Ribeiro 840", "3047605", "06067517426",
				"08330656112", new Date(), "engenheiro de software", 5500.0, false));
		portadores.add(new PortadorDTO("Niédja", "niedja@gmail.com", "Rua José Branco Ribeiro 840", "111222",
				"48844411155", "08330656112", new Date(), "professora", 10000.0, false));
		portadores.add(new PortadorDTO("Nádia", "nadia@gmail.com", "Rua José Branco Ribeiro 840", "222333", "788555222",
				"08330656112", new Date(), "desempregada", 0.0, true));

	}

	/**
	 * Adiciona informações iniciais ao banco, caso o mesmo esteja vazio
	 */
	public void saveInitialBatch() {
		if (portadorRepository.findAll() == null || portadorRepository.findAll().isEmpty()) {
			portadores.forEach((portador) -> {
				this.salvarPortador(portador);
			});
		}
	}

	/**
	 * Encontra e retorna todos os dados dos portadores na tabela dos portadores no banco de dados
	 * 
	 * @return lista de portadores encontrados
	 */
	public List<Portador> findAll() {
		return portadorRepository.findAll();
	}

	/**
	 * Salva os dados do portador requisitado, ao banco de dados, assim como os dados iniciais para análise do mesmo.
	 * 
	 * @param portadorDTO
	 *            dados vindo da view para ser persistido
	 * @return os dados do portador salvo
	 */
	public Portador salvarPortador(PortadorDTO portadorDTO) {
		Portador portador = new Portador();
		portador.setNome(portadorDTO.getNome());
		portador.setEmail(portadorDTO.getEmail());
		portador.setCpf(portadorDTO.getCpf());
		portador.setRg(portadorDTO.getRg());
		portador.setEndereco(portadorDTO.getEndereco());
		portador.setTelefone(portadorDTO.getTelefone());
		portador.setProfissao(portadorDTO.getProfissao());
		portador.setSalario(portadorDTO.getSalario());
		portador.setDataNascimento(portadorDTO.getDataNascimento());
		portador.setSpc(portadorDTO.getSpc());
		Portador portadorSalvo = portadorRepository.save(portador);
		ConductorServiceApp.jmsTemplate.convertAndSend("salvarportador", portador);
		this.analiseService.criarAnaliseInicial(portador);
		return portadorSalvo;
	}

	/**
	 * Atualiza os dados de um único portador, pesquisando pelo o ID do
	 * mesmo
	 * 
	 * @param portadorDTO
	 *            o portador a ser editado os dados
	 * @param id
	 *            o ID do portador a ser pesquisado
	 * @return os dados editados salvos no banco do portador
	 */
	public Portador atualizarPortador(PortadorDTO portadorDTO, Long id) {
		Portador atualizarPortador = portadorRepository.findOne(id);
		atualizarPortador.setNome(portadorDTO.getNome());
		atualizarPortador.setEmail(portadorDTO.getEmail());
		atualizarPortador.setCpf(portadorDTO.getCpf());
		atualizarPortador.setRg(portadorDTO.getRg());
		atualizarPortador.setEndereco(portadorDTO.getEndereco());
		atualizarPortador.setTelefone(portadorDTO.getTelefone());
		atualizarPortador.setDataNascimento(portadorDTO.getDataNascimento());
		atualizarPortador.setProfissao(portadorDTO.getProfissao());
		atualizarPortador.setSalario(portadorDTO.getSalario());
		atualizarPortador.setSpc(portadorDTO.getSpc());
		ConductorServiceApp.jmsTemplate.convertAndSend("atualizarportador", atualizarPortador);
		return portadorRepository.save(atualizarPortador);
	}

	/**
	 * Deleta o portador específico, baseado no ID
	 * 
	 * @param id
	 *            o ID do portador a ser pesquisado e deletado
	 */
	public void deletarPortador(Long id) {
		List<Analise> analiseLista = analiseRepository.findByPortador(id);
		for (Analise analise : analiseLista) {
			analiseRepository.delete(analise.getId());
			ConductorServiceApp.jmsTemplate.convertAndSend("deletaranalise", analise.getId());
		}

		ConductorServiceApp.jmsTemplate.convertAndSend("deletarportador", id);
		portadorRepository.delete(id);
	}

	/**
	 * Apaga todos os dados dos portadores da tabela
	 * 
	 * @param object
	 */
	public void limparPortadores(Object object) {
		ConductorServiceApp.jmsTemplate.convertAndSend("limparportadores", new Date());
		portadorRepository.delete(findAll());
	}

}
