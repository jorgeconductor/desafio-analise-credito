package com.jorge.conductor.dto;

import java.util.Date;

public class PortadorDTO {
	
	// Nome do portador
	private String nome;
	
	// Email do portador
	private String email;
	
	// Endereço do portador
	private String endereco;
	
	// RG do portador
	private String rg;
	
	// CPF do portador
	private String cpf;
	
	// Telefone do portador
	private String telefone;
	
	// Data de nascimento do portador
	private Date dataNascimento;
	
	// Profissão do portador
	private String profissao;
	
	// Salario médio da profissão do portador
	private Double salario;
	
	// Se o portador está com dívidas no SPC
	private Boolean spc;
	
	// Construtor sem argumentos
	public PortadorDTO() {}
	
	
	/**
	 * Construtor com argumentos
	 * @param nome Nome do portador
	 * @param email Email do portador
	 * @param endereco Endereço do portador
	 * @param rg RG do portador
	 * @param cpf CPF do portador
	 * @param telefone Telefone do portador
	 * @param dataNasicamento Data de nascimento do portador
	 * @param profissao Profissão do portador
	 * @param salario Salario médio da profissão do portador
	 * @param spc Se o portador está com dívidas no SPC
	 */
	public PortadorDTO(String nome, String email, String endereco, String rg, String cpf, String telefone, Date dataNasicamento,
			String profissao, Double salario, Boolean spc) {
		this.setNome(nome);
		this.setEmail(email);
		this.setEndereco(endereco);
		this.setRg(rg);
		this.setCpf(cpf);
		this.setTelefone(telefone);
		this.setDataNascimento(dataNasicamento);
		this.setProfissao(profissao);
		this.setSalario(salario);
		this.setSpc(spc);
	}

	/**
	 * @return o nome a ser retornado
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            o nome a ser inserido
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
	 *            o email a ser inserido
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return o endereco a ser retornado
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco
	 *            o endereco a ser inserido
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return o rg a ser retornado
	 */
	public String getRg() {
		return rg;
	}

	/**
	 * @param rg
	 *            o rg a ser inserido
	 */
	public void setRg(String rg) {
		this.rg = rg;
	}

	/**
	 * @return o cpf a ser retornado
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf
	 *            o cpf a ser inserido
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return o spc a ser retornado
	 */
	public Boolean getSpc() {
		return spc;
	}

	/**
	 * @param spc
	 *            o spc a ser inserido
	 */
	public void setSpc(Boolean spc) {
		this.spc = spc;
	}

	/**
	 * @return a dataNascimento a ser retornada
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @param dataNascimento
	 *            a dataNascimento a ser inserida
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * @return a profissao a ser retornada
	 */
	public String getProfissao() {
		return profissao;
	}

	/**
	 * @param profissao
	 *            a profissao a ser inserida
	 */
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	/**
	 * @return o salario a ser retornado
	 */
	public Double getSalario() {
		return salario;
	}

	/**
	 * @param salario
	 *            o salario a ser inserido
	 */
	public void setSalario(Double salario) {
		this.salario = salario;
	}

	/**
	 * @return o telefone a ser retornado
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone o telefone a ser inserido
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
