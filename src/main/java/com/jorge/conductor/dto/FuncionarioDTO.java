package com.jorge.conductor.dto;

public class FuncionarioDTO {

	// Nome do funcionario
	private String nome;

	// Email do funcionario
	private String email;
	
	// Senha do funcionario
	private String senha;
	
	// Verificar se funcionário é um analista ou não
	private Boolean analista;

	/**
	 * @return o nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome o nome a inserir
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return o email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email o email a inserir
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return a senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha a senha a configurar
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return o nivel de acesso a ser verificado
	 */
	public Boolean getAnalista() {
		return analista;
	}

	/**
	 * @param analista verifica se o funcionário é um analista ou não
	 */
	public void setAnalista(Boolean analista) {
		this.analista = analista;
	}

	
}
