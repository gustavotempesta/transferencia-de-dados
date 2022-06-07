package br.com.vrtech;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		LeitorDeArquivo leitor = new LeitorDeArquivo();
		List<Cidadao> cidadaos = new ArrayList<>(
				leitor.importaCidadaos("src/main/resources/dados.txt"));
		System.out.println(cidadaos);
	}

}
