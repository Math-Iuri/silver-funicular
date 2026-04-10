package main;

import java.util.Scanner;

import com.projeto1.Projeto1Arvores;
import com.projeto2.Projeto2Busca;
import com.projeto3.Projeto3Ordenacao;

public class TestesIndividuais {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		System.out.println("1 - Projeto 1, 2 - Projeto 2, 3 - Projeto 3: ");
		int opcao = sc.nextInt();
		
		if (opcao == 1) {
			Projeto1Arvores.executar();
		}
		if (opcao == 2) {
			Projeto2Busca.executar();
		}
		if (opcao == 3) {
			Projeto3Ordenacao.executar();
		}
	}
}