package com.jorge.conductor.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Classe JPA responsável por criar a tabela no banco de dados e suas
 * respectivas colunas de acordo com as variáveis setadas nesta classe.
 */
@Entity
@Table(name = "analise")
public class Analise {

	// Cria o ID da análise que terá como nome do campo id no formato bigint(20)
	// e será auto incremental
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	// Cria o campo do portador no formato bigint(20) e não pode ser nulo
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "portador")
	@NotNull
	private Portador portador;

	// Cria o campo aprovado no formato bit(1) e pode ser nulo
	@Column(name = "aprovado")
	private Boolean aprovado;

	// Cria o campo da data de análise no formato date e ser nulo
	@Column(name = "dataAnalise")
	@Temporal(TemporalType.DATE)
	private Date dataAnalise;

	// Construtor básico
	public Analise() {
	}

	/**
	 * Construtor com argumentos
	 * @param portador dados do portador que será feito a respectiva análise de crédito
	 * @param aprovado verifica a situação da análise de crédito do portador
	 * @param dataAnalise data que foi efetuada a análise
	 */
	public Analise(Portador portador, Boolean aprovado, Date dataAnalise) {
		this.setPortador(portador);
		this.setAprovado(aprovado);
		this.setDataAnalise(dataAnalise);
	}

	/**
	 * @return o id a ser retornado
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            o id a ser inserido
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return o portador a ser retornado
	 */
	public Portador getPortador() {
		return portador;
	}

	/**
	 * @param portador
	 *            o portador a ser inserido
	 */
	public void setPortador(Portador portador) {
		this.portador = portador;
	}

	/**
	 * @return a situação da análise a ser retornada
	 */
	public Boolean getAprovado() {
		return aprovado;
	}

	/**
	 * @param aprovado
	 *            a variável aprovado a ser inserida
	 */
	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}

	/**
	 * @return a dataAnalise a ser retornada
	 */
	public Date getDataAnalise() {
		return dataAnalise;
	}

	/**
	 * @param dataAnalise
	 *            a dataAnalise a ser inserida
	 */
	public void setDataAnalise(Date dataAnalise) {
		this.dataAnalise = dataAnalise;
	}

}
