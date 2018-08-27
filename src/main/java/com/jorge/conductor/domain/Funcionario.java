package com.jorge.conductor.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * Classe JPA responsável por criar a tabela no banco de dados e suas respectivas colunas de acordo com as variáveis setadas nesta classe.
 */
@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {
	
	// Cria o ID do funcionário que terá como nome do campo id no formato bigint(20) e será auto incremental
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	// Cria o campo do nome no formato varchar com largura 255 e não pode ser nulo
	@Column(name = "nome")
	@NotNull
	private String nome;
	
	// Cria o campo do email no formato varchar com largura 255 e não pode ser nulo
	@Column(name = "email")
	@NotNull
	private String email;
	
	// Cria o campo do email no formato varchar com largura 255 e não pode ser nulo
	@Column(name = "senha")
	@NotNull
	private String senha;
	
	// Cria o campo do analista no formato smallint e não pode ser nulo
	@Column(name = "analista")
	@NotNull
	private Boolean analista;
	
	/**
	 * Construtor que passa o email e o nome como parâmetro
	 * @param email do funcionário a ser inserido
	 * @param nome do funcionário a ser inserido
	 * @param senha do funcionário
	 * @param analista se o funcionário é um analista ou não
	 */
	public Funcionario(String email, String nome, String senha, Boolean analista) {
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.analista = analista;
	}
	
	/**
	 * Construtor básico
	 */
	public Funcionario() {
	}
	
	/**
	 * @return o ID a ser retornado
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id
	 * 			o ID a ser inserido
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return o nome a ser retornado
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param nome
	 * 			o nome a ser inserido
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return o email a ser retornado
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email
	 * 			o email a ser inserido
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return a senha a ser retornada
	 */
	public String getSenha() {
		return senha;
	}
	
	/**
	 * @param senha
	 * 			a senha a ser inserida
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return o nivel de acesso do funcionário
	 */
	public Boolean getAnalista() {
		return analista;
	}

	/**
	 * @param analista 
	 * 			o nível de acesso a ser verificado
	 */
	public void setAnalista(Boolean analista) {
		this.analista = analista;
	}
	
	@Override
	public String toString() {
		return String.format("User{nome=%s, email=%s, analista=%b}", getNome(), getEmail(), getAnalista());
	}
}
