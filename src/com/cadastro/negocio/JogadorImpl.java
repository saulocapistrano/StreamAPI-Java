package com.cadastro.negocio;

import com.cadastro.model.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JogadorImpl {

	public boolean verificarArquivoExiste(Path caminho) {
		boolean ret = false;
		
		try {
			Stream<Path> stream = Files.list(caminho);
			Optional<Path> arq = stream.filter(p -> p.toString().endsWith("jogadores.txt")).findAny();
			ret = arq.isPresent();// verificando se o aquivo está presente
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return ret;
	}

	public List<Jogador> getListaDeJogadores(Path caminho) throws IOException {
		Stream<String> linhas = Files.lines(caminho, StandardCharsets.ISO_8859_1);
		List<String> listaDeLinhas = linhas.collect(Collectors.toList());
		
		List<Jogador> listaDeJogadores = new ArrayList<>();
		Jogador jogador;
		
		Iterator it = listaDeLinhas.listIterator();
		String str = null;
		
		while (it.hasNext()) {
			str = (String) it.next();
			String info[] = str.split(",");
			
			jogador = new Jogador();
			jogador.setNome(info[0]);
			jogador.setPosicao(info[1]);
			jogador.setIdade(Integer.parseInt(info[2]));
			jogador.setTimeAtual(info[3]);
			jogador.setGolsMarcados(Integer.parseInt(info[4]));

			listaDeJogadores.add(jogador);
		}
		return listaDeJogadores;

	}

	// métodos
	public void imprimirJogadores(List<Jogador> jogadores)/*cria a lista de jogadores*/ {
		jogadores.stream()./*cria a stream chamando a lista*/forEach(System.out::println)/*imprime todos os elementos*/;
	}

	public void imprimirJogadoresTime(List<Jogador> jogadores, String time)/*recebendo como parâmetro a lista de jogadores e o nome do time que o jogador está ligado*/ {
		jogadores.stream().filter(jogador ->jogador.getTimeAtual().equals(time) && jogador.getGolsMarcados() > 10)/*isso é uma expressão lambda jogador -> ...) 
																									é a condição que indica o que está sendo pedido como filtro, o filtro aqui 
																									é só para jogadores que fizeram mais de 10 gols */ 
				.forEach(System.out::println);
	}
	
	public void agruparJogadoresPorTime(List<Jogador> jogadores) {
		jogadores.stream().sorted(Comparator.comparing(Jogador::getTimeAtual)).forEach(System.out::println);
	}
	
	public double calcularMediaIdade(List<Jogador>jogadores) {
		return jogadores.stream().mapToInt/*mapToInt produz uma stream de inteiros com a lista das idades dos jogadores*/(Jogador::getIdade).average()/* average pega a lista de jogadores 
																						e faz uma média da idade dos jogadores da lista */.getAsDouble();
	}
	
	public void imprimirJogadorMaisVelho(List<Jogador> jogadores) {
		Jogador jogador = jogadores.stream().max/*função que busca o maior valor da lista de idades*/(Comparator.comparingInt(Jogador::getIdade)).get();
		System.out.println("Jogador mais velho: "+jogador.getNome());
	}
	
	public void imprimirJogadorMaisNovo(List<Jogador> jogadores) {
		Jogador jogador = jogadores.stream().max(Comparator.comparingInt(Jogador::getIdade)).get();
		 // .min((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
		System.out.println("Jogador mais novo" +jogador.getNome());
	}

	public void imprimirJogadorArtilheiro(List<Jogador> jogadores) {
		Jogador jogador = jogadores.stream().max(Comparator.comparingInt(Jogador::getGolsMarcados)).get();
		System.out.println("Jogador artilheiro "+jogador.getNome());
	}
	
	public int imprimirSomatoriosGols(List<Jogador> jogadores ) {
		int soma  = jogadores.stream().mapToInt(jogador-> jogador.getGolsMarcados()).sum();
		return soma;
	}

	public void agruparJogadoresPeloTime(List<Jogador> jogadores) {
			Map<String, List<Jogador>>  groupByTime = jogadores.stream().collect(Collectors.groupingBy(Jogador::getTimeAtual));
			System.out.println(groupByTime);
	}
	
	public void ordenarJogadoresGols(List<Jogador>jogadores) {
		jogadores.stream().sorted(Comparator.comparingInt(Jogador::getGolsMarcados).reversed()).forEach(System.out::println);
	}
}
