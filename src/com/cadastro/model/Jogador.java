package com.cadastro.model;

public class Jogador {

	private String nome;
	private String posicao;
	private int idade;
	private String timeAtual;
	private int golsMarcados;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPosicao() {
		return posicao;
	}
	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getTimeAtual() {
		return timeAtual;
	}
	public void setTimeAtual(String timeAtual) {
		this.timeAtual = timeAtual;
	}
	public int getGolsMarcados() {
		return golsMarcados;
	}
	public void setGolsMarcados(int golsMarcados) {
		this.golsMarcados = golsMarcados;
	}
	
	@Override
	public String toString() {
		return this.nome+" "+this.posicao+" "+ this.getTimeAtual();  
		
	}
}
