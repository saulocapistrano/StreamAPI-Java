package com.cadastro.main;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import com.cadastro.model.Jogador;
import com.cadastro.negocio.JogadorImpl;

public class Principal {

	public static void main(String[] args) throws IOException {

		Principal p = new Principal();
		JogadorImpl jogImpl = new JogadorImpl();
		String enderecoDir = "C:\\Users\\compaq\\Desktop\\WorkStation-Saulo\\TestandoStream\\src";
		String nomeArquivo = "jogadores.txt";
		List<Jogador> listaDeJogadores = jogImpl.getListaDeJogadores(Paths.get(enderecoDir + "\\" + nomeArquivo));
		if (!jogImpl.verificarArquivoExiste(Paths.get(enderecoDir))) {
			System.out.println("Arquivo inexistente");
		}else {
			jogImpl.imprimirJogadorArtilheiro(listaDeJogadores);
			jogImpl.imprimirJogadorMaisVelho(listaDeJogadores);
			jogImpl.imprimirJogadorMaisNovo(listaDeJogadores);
		}
	}

}
