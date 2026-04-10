package main;

import com.projeto1.Projeto1Arvores;
import com.projeto2.Projeto2Busca;
import com.projeto3.Projeto3Ordenacao;

/**
 * Ponto de entrada principal.
 *
 * Executa sequencialmente: Projeto 1 – Arvores (BST, AVL, Rubro-Negra,
 * Caixeiro-Viajante) Projeto 2 – Busca (Sequencial, Binaria, BST) Projeto 3 –
 * Ordenacao (Merge Sort, Quick Sort)
 *
 * Como compilar e rodar: javac -encoding UTF-8 -d out src/*.java java -cp out
 * Main
 *
 * Ou simplesmente execute o arquivo executar.bat no Windows.
 */
public class Main {

	public static void main(String[] args) {
		System.out.println();
		System.out.println("=".repeat(76));
		System.out.println("  ESTRUTURAS DE DADOS II - ANALISE EXPERIMENTAL");
		System.out.println("  Algoritmos: Arvores | Busca | Ordenacao");
		System.out.println("  Metodologia:");
		System.out.println("    - 30 execucoes por tamanho de entrada");
		System.out.println("    - Tempo medido com System.nanoTime()");
		System.out.println("    - Resultados: media e desvio padrao em nanosegundos");
		System.out.println();

		Projeto1Arvores.executar();
		Projeto2Busca.executar();
		Projeto3Ordenacao.executar();

		System.out.println("=".repeat(76));
		System.out.println("  Experimentos concluidos.");
		System.out.println("=".repeat(76));
	}
}
