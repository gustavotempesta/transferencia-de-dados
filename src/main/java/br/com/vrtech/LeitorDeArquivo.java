package br.com.vrtech;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.vrtech.model.Cidadao;

public class LeitorDeArquivo {

	public List<Cidadao> importaCidadaos(File arquivo) throws IOException{
		List<Cidadao> cidadaos = new ArrayList<>();
		try (Scanner scanner = new Scanner(arquivo, "windows-1252")) {
			if (scanner.hasNext()) {
				scanner.nextLine(); //ignorar o cabecalho
				
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					
					List<String> dados = new ArrayList<>();
					int indiceInicio = 0;
					int indiceFim = 0;
					int indiceDivisor = line.indexOf("|");
					
					while (indiceDivisor != -1) {
						indiceFim = indiceInicio + indiceDivisor;
						dados.add(line.substring(indiceInicio,indiceFim).trim());
						indiceInicio = indiceFim + 1;
						indiceDivisor = line.substring(indiceInicio).indexOf("|");
					}
					dados.add(line.substring(indiceFim + 1).trim());
					Cidadao cidadao = new Cidadao(dados.get(0), Integer.valueOf(dados.get(1)), dados.get(2), dados.get(3));
					cidadaos.add(cidadao);
				}
			}	
		} catch (FileNotFoundException ex) {
			throw new IOException("Exceção: O arquivo não existe.");
		}
		return cidadaos;
	}
	
}
