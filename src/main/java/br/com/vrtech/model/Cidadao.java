package br.com.vrtech.model;

public class Cidadao {

	private Integer id;
	private String nome;
	private Integer idade;
	private String municipio;
	private String estado; 
	
	public Cidadao(String nome, Integer idade, String municipio, String estado) {
		this.nome = nome;
		this.idade = idade;
		this.municipio = municipio;
		this.estado = estado;
	}
	
	public Cidadao(Integer id, String nome, Integer idade, String municipio, String estado) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.municipio = municipio;
		this.estado = estado;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public int getIdade() {
		return idade;
	}

	public String getMunicipio() {
		return municipio;
	}

	public String getEstado() {
		return estado;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Cidadao [nome=%s, idade=%s, municipio=%s, estado=%s]\n", 
				this.nome, this.idade, this.municipio, this.estado);
	}
	
}
