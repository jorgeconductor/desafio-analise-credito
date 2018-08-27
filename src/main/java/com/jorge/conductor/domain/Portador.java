package com.jorge.conductor.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Classe JPA responsável por criar a tabela no banco de dados e suas
 * respectivas colunas de acordo com as variáveis setadas nesta classe.
 */
@Entity
@Table(name = "portador")
public class Portador {

	// Cria o ID do portador que terá como nome do campo id no formato bigint(20)
	// e será auto incremental
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

	// Cria o campo do endereco no formato varchar com largura 255 e não pode ser nulo
	@Column(name = "endereco")
	@NotNull
	private String endereco;

	// Cria o campo do rg no formato varchar com largura 255 e não pode ser nulo
	@Column(name = "rg")
	@NotNull
	private String rg;

	// Cria o campo do cpf no formato varchar com largura 255 e não pode ser nulo
	@Column(name = "cpf")
	@NotNull
	private String cpf;

	// Cria o campo do telefone no formato varchar com largura 255 e não pode ser nulo
	@Column(name = "telefone")
	@NotNull
	private String telefone;

	// Cria o campo da dataNascimento no formato date e não pode ser nulo
	@Column(name = "dataNascimento")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dataNascimento;

	// Cria o campo da profissao no formato varchar com largura 255 e não pode ser nulo
	@Column(name = "profissao")
	@NotNull
	private String profissao;

	// Cria o campo do salario no formato double e não pode ser nulo
	@Column(name = "salario")
	@NotNull
	private Double salario;

	// Cria o campo do spc no formato bit(1) e não pode ser nulo
	@Column(name = "spc")
	private Boolean spc;

	// Construtor básico
	public Portador() {
	}

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
	public Portador(String nome, String email, String endereco, String rg, String cpf, String telefone, Date dataNasicamento,
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
	 * @return o id a ser inserido
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
	
	@Override
	public String toString() {
		return String.format("Portador{nome=%s, email=%s, rg=%s}", getNome(), getEmail(), getRg());
	}

}
