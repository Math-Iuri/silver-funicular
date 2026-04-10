package com.projeto1;

import com.util.Stats;

/**
 * PROJETO 1 – Experimento com Arvores (BST, AVL, Rubro-Negra) + Caixeiro
 * Viajante (heuristica vizinho mais proximo)
 *
 * Metodologia: - 3 tamanhos de entrada: 100, 1000, 10000 - 30 execucoes por
 * tamanho - Arvores: mede insercao em massa + 1 busca + 1 remocao - TSP: mede o
 * tempo de resolver() para matrizes de tamanho 10, 50, 100 - Calcula media e
 * desvio padrao
 */
public class Projeto1Arvores {

	private static final int[] TAMANHOS = { 100, 1000, 10000 };
	private static final int REPETICOES = 30;

	public static void executar() {
		System.out.println("=".repeat(76));
		System.out.println("  PROJETO 1 - ARVORES DE BUSCA");
		System.out.println("  Complexidades teoricas:");
		System.out.println("    BST:          O(log n) caso medio  |  O(n) pior caso");
		System.out.println("    AVL:          O(log n)  |  altura <= 1.44 * log2(n)");
		System.out.println("    Rubro-Negra:  O(log n)  |  altura <= 2 * log2(n)");
		System.out.println("=".repeat(76));

		demonstrarFuncionamento();

		for (int n : TAMANHOS) {
			System.out.println("=".repeat(76));
			System.out.println("\n  Tamanho n = " + n);
			Stats.separador();
			testarBST(n);
			testarAVL(n);
			testarRubroNegra(n);
		}

		testarCaixeiroViajante();

		System.out.println();
	}

	private static void demonstrarFuncionamento() {

		System.out.println("  VALIDAÇÃO: INSERÇÃO, BUSCA E REMOÇÃO");
		System.out.println("=".repeat(76));

		// demo: AVL
		System.out.println(" ÁRVORE AVL: ");
		AVLTree avl = new AVLTree();
		System.out.println("1. Inserindo nós 50, 30, 70, 20, 40 na AVL...");
		avl.inserir(50);
		avl.inserir(30);
		avl.inserir(70);
		avl.inserir(20);
		avl.inserir(40);

		System.out.println("2. Buscando o nó 30 ANTES da remoção: " + avl.buscar(30));
		System.out.println("   Altura da AVL ANTES da remoção: " + avl.altura());

		System.out.println("\n3. Executando avl.remover(30)...");
		avl.remover(30);

		System.out.println("4. Buscando o nó 30 APÓS a remoção: " + avl.buscar(30));
		System.out.println("   Altura da AVL APÓS a remoção: " + avl.altura());
		System.out.println("   Buscando um filho do nó removido (ex: 20): " + avl.buscar(20));
		System.out.println("-".repeat(76));

		// demo:BST
		System.out.println("\n ÁRVORE BINÁRIA DE BUSCA (BST):");
		BSTTree bst = new BSTTree();
		System.out.println("1. Inserindo os mesmos nós 50, 30, 70, 20, 40 na BST...");
		bst.inserir(50);
		bst.inserir(30);
		bst.inserir(70);
		bst.inserir(20);
		bst.inserir(40);

		System.out.println("2. Buscando o nó 30 ANTES da remoção: " + bst.buscar(30));
		System.out.println("   Altura da BST ANTES da remoção: " + bst.altura());

		System.out.println("\n3. Executando bst.remover(30)...");
		bst.remover(30);

		System.out.println("4. Buscando o nó 30 APÓS a remoção: " + bst.buscar(30));
		System.out.println("   Altura da BST APÓS a remoção: " + bst.altura());
		System.out.println("   Buscando um filho do nó removido (ex: 20): " + bst.buscar(20));
		System.out.println("-".repeat(76));

		//demo: arvore flamenguista
		System.out.println("\n ÁRVORE RUBRO-NEGRA (LLRB):");
		RedBlackTree rb = new RedBlackTree();
		System.out.println("1. Inserindo os mesmos nós 50, 30, 70, 20, 40 na Rubro-Negra...");
		rb.inserir(50);
		rb.inserir(30);
		rb.inserir(70);
		rb.inserir(20);
		rb.inserir(40);

		System.out.println("2. Buscando o nó 30 ANTES da remoção: " + rb.buscar(30));
		System.out.println("   Altura da Rubro-Negra ANTES da remoção: " + rb.altura());

		System.out.println("\n3. Executando rb.remover(30)...");
		rb.remover(30);

		System.out.println("4. Buscando o nó 30 APÓS a remoção: " + rb.buscar(30));
		System.out.println("   Altura da Rubro-Negra APÓS a remoção: " + rb.altura());
		System.out.println("   Buscando um filho do nó removido (ex: 20): " + rb.buscar(20));

	}

	private static void testarBST(int n) {
		BSTTree bst = new BSTTree();
		long[] tempos = new long[REPETICOES];

		for (int r = 0; r < REPETICOES; r++) {
			int[] dados = Stats.gerarAleatorios(n);
			bst.limpar();

			long inicio = System.nanoTime();
			for (int v : dados)
				bst.inserir(v);
			bst.buscar(dados[0]);
			bst.remover(dados[0]);
			tempos[r] = System.nanoTime() - inicio;
		}
		Stats.imprimirResultadoNs("BST", n, tempos);
	}

	private static void testarAVL(int n) {
		AVLTree avl = new AVLTree();
		long[] tempos = new long[REPETICOES];

		for (int r = 0; r < REPETICOES; r++) {
			int[] dados = Stats.gerarAleatorios(n);
			avl.limpar();

			long inicio = System.nanoTime();
			for (int v : dados)
				avl.inserir(v);
			avl.buscar(dados[0]);
			avl.remover(dados[0]);
			tempos[r] = System.nanoTime() - inicio;
		}
		Stats.imprimirResultadoNs("AVL", n, tempos);
	}

	private static void testarRubroNegra(int n) {
		RedBlackTree rb = new RedBlackTree();
		long[] tempos = new long[REPETICOES];

		for (int r = 0; r < REPETICOES; r++) {
			int[] dados = Stats.gerarAleatorios(n);
			rb.limpar();

			long inicio = System.nanoTime();
			for (int v : dados)
				rb.inserir(v);
			rb.buscar(dados[0]);
			rb.remover(dados[0]);
			tempos[r] = System.nanoTime() - inicio;
		}
		Stats.imprimirResultadoNs("Rubro-Negra (LLRB)", n, tempos);
	}

	private static void testarCaixeiroViajante() {
		System.out.println("\n" + "=".repeat(76));
		System.out.println("  CAIXEIRO VIAJANTE - Vizinho Mais Proximo");
		System.out.println("  Complexidade: O(n^2)  -- heuristica, nao garante solucao otima");
		System.out.println("=".repeat(76));

		int[] cidades = { 10, 50, 100 };

		for (int n : cidades) {
			System.out.println("\n  Numero de cidades = " + n);
			Stats.separador();

			long[] tempos = new long[REPETICOES];
			int custoAmostra = 0;

			for (int r = 0; r < REPETICOES; r++) {
				int[][] matriz = CaixeiroViajante.gerarMatriz(n, 100);
				CaixeiroViajante tsv = new CaixeiroViajante();

				long inicio = System.nanoTime();
				tsv.resolver(matriz);
				tempos[r] = System.nanoTime() - inicio;

				if (r == 0)
					custoAmostra = tsv.getCustoTotal();
			}

			Stats.imprimirResultadoNs("Caixeiro Viajante", n, tempos);
			System.out.println("    (custo da 1a execucao: " + custoAmostra + ")");
		}
	}
}
