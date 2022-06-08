package br.com.vrtech;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.vrtech.model.Cidadao;

public class Main {

	public static void main(String[] args) throws IOException {
		LeitorDeArquivo leitor = new LeitorDeArquivo();
		List<Cidadao> cidadaos = new ArrayList<>(
				leitor.importaCidadaos(new File("src/main/resources/dados.txt")));
		System.out.println(cidadaos);		
	}
	
}
