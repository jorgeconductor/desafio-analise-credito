package com.jorge.conductor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorge.conductor.ConductorServiceApp;
import com.jorge.conductor.domain.Funcionario;
import com.jorge.conductor.dto.FuncionarioDTO;
import com.jorge.conductor.repository.FuncionarioRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que oferece serviços de receber e filtrar dados da requisição via REST como deletar, adicionar ou atualizar cada campo
 */
@Service
@Transactional
public class FuncionarioService {
	
	// Invoca o repositório dos funcionários e seus respectivos comandos JPA
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private static List<Funcionario> funcionarios = new ArrayList<>();
    
    /**
     * Adiciona informações iniciais ao banco, caso o mesmo esteja vazio
     */
    static {
    	funcionarios.add(new Funcionario("jorg3.f3itosa@gmail.com", "Jorge Lima", "123456", true));
    	funcionarios.add(new Funcionario("niedja@gmail.com", "Niédja Maria", "123456", false));
    	funcionarios.add(new Funcionario("nadia@gmail.com", "Nádia Lima", "123456", true));
    	funcionarios.add(new Funcionario("ramon@gmail.com", "Ramon Garcia", "123456", false));
    }
    
    /**
     * Adiciona informações iniciais ao banco, caso o mesmo esteja vazio
     */
    public void saveInitialBatch() {
    	if(funcionarioRepository.findAll() == null || funcionarioRepository.findAll().isEmpty()) {
	    	funcionarioRepository.save(funcionarios);
	    	funcionarios.forEach((funcionarios) -> {
	            ConductorServiceApp.jmsTemplate.convertAndSend("savefuncionarioparabd", funcionarios);
	        });
    	}
    }
    
    /**
     * Salva os dados do funcionário requisitados, ao banco de dados
     * @param funcionarioDTO dados vindo da view para ser persistido
     * @return os dados do funcionário salvo
     */
	public Funcionario salvarFuncionario(FuncionarioDTO funcionarioDTO) {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(funcionarioDTO.getNome());
		funcionario.setEmail(funcionarioDTO.getEmail());
		funcionario.setSenha(funcionarioDTO.getSenha());
		funcionario.setAnalista(funcionarioDTO.getAnalista());
        return funcionarioRepository.save(funcionario);
    }
	
	/**
	 * Atualiza os dados de um único funcionário, pesquisando pelo o ID do mesmo
	 * @param funcionarioDTO o funcionário a ser editado os dados
	 * @param id o ID do funcionário a ser pesquisado
	 * @return os dados editados salvos no banco do funcionário
	 */
    public Funcionario atualizarFuncionario(FuncionarioDTO funcionarioDTO, Long id) {
    	Funcionario atualizarItem = funcionarioRepository.findOne(id);
    	atualizarItem.setNome(funcionarioDTO.getNome());
    	atualizarItem.setEmail(funcionarioDTO.getEmail());
    	atualizarItem.setSenha(funcionarioDTO.getSenha());
    	atualizarItem.setAnalista(funcionarioDTO.getAnalista());
        return funcionarioRepository.save(atualizarItem);
    }
    
    /**
     * Encontra e retorna todos os dados dos funcionários na tabela de funcionario no banco de dados
     * @return lista de funcionários encontrados
     */
    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }
    
    /**
     * Deleta um funcionário específico, baseado no ID
     * @param id o ID do funcionário a ser pesquisado e deletado
     */
    public void deletarFuncionario(Long id) {
    	funcionarioRepository.delete(id);
    }

}
