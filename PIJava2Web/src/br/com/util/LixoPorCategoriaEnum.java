package br.com.util;

public enum LixoPorCategoriaEnum {

	COMPUTADOR("Computador"), NOTEBOOK("Notebook"), TELEFONE("Telefone"), BATERIAS(
			"Baterias"), PERIFERICOS("Perifericos"), OUTROS("Outros");

	private String valor;

	private LixoPorCategoriaEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
