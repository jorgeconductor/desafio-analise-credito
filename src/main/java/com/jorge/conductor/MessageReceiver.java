package com.jorge.conductor;

import java.util.Date;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.jorge.conductor.domain.Analise;
import com.jorge.conductor.domain.Funcionario;
import com.jorge.conductor.domain.Portador;

/**
 * Classe responsável por configurar o envio e recebimento de mensagens através
 * de toda a aplicação, quando informação capturada será apresentada no LOG do
 * seu terminal
 */
@Component
public class MessageReceiver {

	@JmsListener(destination = "savefuncionarioparabd", containerFactory = "myFactory")
	public void receiveFuncionarioSavedMessage(Funcionario funcionario) {
		System.out.println("Funcionario salvo <" + funcionario + ">");
	}

	@JmsListener(destination = "salvarportador", containerFactory = "myFactory")
	public void receivePortadorSavedMessage(Portador portador) {
		System.out.println("Portador salvo <" + portador + ">");
	}

	@JmsListener(destination = "atualizarportador", containerFactory = "myFactory")
	public void receivePortadorUpdatedMessage(Portador portador) {
		System.out.println("Portador atualizado <" + portador + ">");
	}

	@JmsListener(destination = "deletarportador", containerFactory = "myFactory")
	public void receivePortadorDeletedMessage(Long id) {
		System.out.println("Portador deletado <" + id + ">");
	}

	@JmsListener(destination = "limparportadores", containerFactory = "myFactory")
	public void receivePortadorClearedMessage(Date date) {
		System.out.println("Lista de portadores limpo <" + date + ">");
	}
	
	@JmsListener(destination = "salvaranalise", containerFactory = "myFactory")
	public void receiveAnaliseSavedMessage(Analise analise) {
		System.out.println("Portador salvo <" + analise + ">");
	}

	@JmsListener(destination = "atualizaranalise", containerFactory = "myFactory")
	public void receiveAnaliseUpdatedMessage(Analise analise) {
		System.out.println("Portador atualizado <" + analise + ">");
	}

	@JmsListener(destination = "deletaranalise", containerFactory = "myFactory")
	public void receiveAnaliseDeletedMessage(Long id) {
		System.out.println("Portador deletado <" + id + ">");
	}

	@JmsListener(destination = "limparanalises", containerFactory = "myFactory")
	public void receiveAnaliseClearedMessage(Date date) {
		System.out.println("Lista de portadores limpo <" + date + ">");
	}

}
