package br.com.vrtech;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.vrtech.model.Cidadao;

class LeitorDeArquivoTest {

	private LeitorDeArquivo leitor;
	private List<Cidadao> cidadaos = new ArrayList<>();

	@Test
	public void deveriaRetornarListaVaziaQuandoLerArquivoVazio() {
		leitor = new LeitorDeArquivo();
		try {
			File f = File.createTempFile("tmp", ".txt", new File("src/test/resources/"));
			
			try (FileWriter fw = new FileWriter(f)) {
				fw.write("");
			}

			cidadaos = leitor.importaCidadaos(f);
			Assertions.assertEquals(true, cidadaos.isEmpty());

			f.deleteOnExit();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deveriaLancarExcecaoQuandoArquivoNaoExistir() {
		leitor = new LeitorDeArquivo();
		
		Assertions.assertThrows(
				IOException.class, 
				() -> leitor.importaCidadaos(new File("")));		
	}

	@Test
	public void deveriaRetornarListaComUmCidadaoQuandoLerArquivoComUmaLinha() {
		leitor = new LeitorDeArquivo();
		try {
			File f = File.createTempFile("tmp", ".txt", new File("src/test/resources/"));
			
		 	try(FileWriter fw = new FileWriter(f)){
		 		fw.write("Nome            | Idade | Município onde mora | Estado \n");
		 		fw.write("Cidadao Teste   | 20    | Cidade Teste        | NA     \n");
		 	}

			cidadaos = leitor.importaCidadaos(f);
			Assertions.assertEquals(1, cidadaos.size());
			Assertions.assertEquals("Cidadao Teste", cidadaos.get(0).getNome());
			Assertions.assertEquals(20, cidadaos.get(0).getIdade());
			Assertions.assertEquals("Cidade Teste", cidadaos.get(0).getMunicipio());
			Assertions.assertEquals("NA", cidadaos.get(0).getEstado());

			f.deleteOnExit();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deveriaRetornarListaComNCidadaosQuandoLerArquivoComNLinhas() {
		leitor = new LeitorDeArquivo();
		try {
			File f = File.createTempFile("tmp", ".txt", new File("src/test/resources/"));
			final int NUMERO_LINHAS = 4; 

		 	try(FileWriter fw = new FileWriter(f)){
		 		fw.write("Nome            | Idade | Município onde mora | Estado \n");
		 		for (int i=0; i < NUMERO_LINHAS; i++ ) {
		 			fw.write("Cidadao Teste   | 20    | Cidade Teste        | NA     \n");
		 		}
		 	}

			cidadaos = leitor.importaCidadaos(f);
			Assertions.assertEquals(NUMERO_LINHAS, cidadaos.size());

			f.deleteOnExit();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
