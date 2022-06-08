package br.com.vrtech.model;

public class Cidadao {

	private String nome;
	private int idade;
	private String municipio;
	private String estado; 
	
	public Cidadao(String nome, int idade, String municipio, String estado) {
		this.nome = nome;
		this.idade = idade;
		this.municipio = municipio;
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return String.format(
				"[nome=%s, idade=%s, municipio=%s, estado=%s]", 
				this.nome, this.idade, this.municipio, this.estado);
	}
	
}
