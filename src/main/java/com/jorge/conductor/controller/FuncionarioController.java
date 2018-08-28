package com.jorge.conductor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jorge.conductor.domain.Funcionario;
import com.jorge.conductor.dto.FuncionarioDTO;
import com.jorge.conductor.dto.FuncionarioLoginDTO;
import com.jorge.conductor.service.FuncionarioService;

/**
 * Classe responsável por configurar todas as execuções via REST do Sprint Boot
 */
@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

	// Invoca o service do funcionario para ser efetuado execuções geralmente para o
	// banco de dados
	@Autowired
	private FuncionarioService funcionarioService;
	
	/**
	 * Adiciona um novo funcionário baseado nas informações passadas
	 * @param funcionarioDTO dados do funcionário passado nos parâmetros
	 * @return os dados salvos do funcionário ao banco de dados
	 */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Funcionario adicionarFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) {
        return funcionarioService.salvarFuncionario(funcionarioDTO);
    }
    
    /**
     * Edita os dados de um funcionário específico e salva no banco 
     * @param funcionarioDTO o funcionário a ser editado
     * @param ids ID do funcionário a ser encontrado e editado
     * @return os dados editados do funcionário
     */
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json", value ="/{id}")
    public Funcionario atualizarFuncionario(@RequestBody FuncionarioDTO funcionarioDTO, @PathVariable("id") Long ids) {
        return funcionarioService.atualizarFuncionario(funcionarioDTO, ids);
    }

	/**
	 * Retorna todos os itens dos funcionario presentes no banco de dados
	 * 
	 * @return todos os itens dos funcionario em um array
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Funcionario> getAll() {
		return funcionarioService.findAll();
	}
	
	/**
     * Verifica se a senha bate do funcionário
     * @return se a senha é a mesma
     */
    @RequestMapping(value = "/pass", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Funcionario getSenhaBate(@RequestBody FuncionarioLoginDTO funcionarioLoginDTO) {
    	return funcionarioService.senhaBate(funcionarioLoginDTO);
    }

	/**
	 * Item do funcionario a ser deletado
	 * 
	 * @param ids
	 *            número do ID do funcionario a ser deletado
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deletarFuncionario(@PathVariable("id") Long ids) {
		funcionarioService.deletarFuncionario(ids);
	}

}
