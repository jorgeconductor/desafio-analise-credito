package com.jorge.conductor.dto;


public class AnaliseDTO {
	
	// O ID do portador relacionado a análise
	private Long portadorId;

	// Verifica a situação da análise de crédito do portador
	private Boolean aprovado;
	
	// Construtor sem argumentos
	public AnaliseDTO() {}

	/**
	 * @return o portadorId a ser retornado
	 */
	public Long getPortadorId() {
		return portadorId;
	}

	/**
	 * @param portadorId o portadorId a ser inserido
	 */
	public void setPortadorId(Long portadorId) {
		this.portadorId = portadorId;
	}

	/**
	 * @return o aprovado a ser retornado
	 */
	public Boolean getAprovado() {
		return aprovado;
	}

	/**
	 * @param aprovado o aprovado a ser inserido
	 */
	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}

}
