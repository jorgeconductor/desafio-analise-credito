package com.jorge.conductor.dto;

public class FuncionarioLoginDTO {

	// Email do funcionario
	private String email;

	// Senha do funcionario
	private String senha;
	
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

}
