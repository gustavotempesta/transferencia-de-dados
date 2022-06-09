package br.com.vrtech;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.vrtech.controller.CidadaoController;
import br.com.vrtech.model.Cidadao;

public class Main {

	public static void main(String[] args) throws IOException {
		LeitorDeArquivo leitor = new LeitorDeArquivo();
		CidadaoController cidadaoController = new CidadaoController();

		List<Cidadao> cidadaos = new ArrayList<>(leitor.importaCidadaos(new File("src/main/resources/dados.txt")));

		cidadaos.forEach(cidadao -> cidadaoController.gravar(cidadao));

		System.out.println("Cidadãos da região sudeste:");
		cidadaoController.buscarSomenteOsDaRegiaoSudeste().forEach(System.out::println);
		
		System.out.println("Cidadãos maiores de 30:");
		cidadaoController.buscarSomenteOsMaioresDe30().forEach(System.out::println);

	}

}
