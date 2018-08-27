package com.jorge.conductor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jorge.conductor.domain.Analise;
import com.jorge.conductor.dto.AnaliseDTO;
import com.jorge.conductor.service.AnaliseService;

/**
 * Classe responsável por configurar todas as execuções via REST do Sprint Boot
 */
@RestController
@RequestMapping("/analise")
public class AnaliseController {
	
		// Invoca o serviço da análise para ser efetuado execuções geralmente para o banco de dados
		@Autowired
	    private AnaliseService analiseService;
		
		/**
		 * Adiciona uma nova análise baseado nas informações passadas
		 * @param analiseDTO dados da análise passado nos parâmetros
		 * @return os dados salvos da análise ao banco de dados
		 */
	    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	    public Analise adicionarAnalise(@RequestBody AnaliseDTO analiseDTO) {
	        return analiseService.salvarAnalise(analiseDTO);
	    }

	    
	    /**
	     * Retorna todos os itens das análises presentes no banco de dados
	     * @return todos os itens das análises em um array
	     */
	    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
	    public List<Analise> getAll(){
	        return analiseService.findAll();
	    }
	    
	    /**
	     * Edita os dados de uma análise específica e salva no banco 
	     * @param analiseDTO a análise a ser editada
	     * @param ids ID da análise a ser encontrada e editada
	     * @return os dados editados da análise
	     */
	    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json", value ="/{id}")
	    public Analise atualizarAnalise(@RequestBody AnaliseDTO analiseDTO, @PathVariable("id") Long ids) {
	        return analiseService.atualizarAnalise(analiseDTO, ids);
	    }
	    
	    /**
	     * Item da análise a ser deletada
	     * @param ids número do ID da análise a ser deletada
	     */
	    @RequestMapping(method = RequestMethod.DELETE, value ="/{id}")
	    public void deletarAnalise(@PathVariable("id") Long ids) {
	    	analiseService.deletarAnalise(ids);
	    }
	    
	    /**
	     * Limpa todos os dados das análises presentes no banco de dados
	     * @param object 
	     */
	    @RequestMapping(method = RequestMethod.DELETE)
	    public void limparAnalises( Object object) {
	    	analiseService.limparAnalises(object);
	    }

}
