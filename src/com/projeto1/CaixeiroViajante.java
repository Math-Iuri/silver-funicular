package com.projeto1;

/**
 * Problema do Caixeiro-Viajante (TSP) Heuristica: Vizinho Mais Proximo (Nearest
 * Neighbor)
 *
 * Estrategia: - Comeca do vertice 0 - A cada passo vai para a cidade mais
 * proxima ainda nao visitada - No final retorna a cidade inicial
 *
 * Complexidade: O(n^2) -- nao e otimo, mas e rapido e simples
 */
public class CaixeiroViajante {

	// Guarda a rota encontrada e o custo total
	private int[] rota;
	private int custoTotal;

	/**
	 * Executa a heuristica do vizinho mais proximo.
	 * 
	 * @param distancias matriz n x n com as distancias entre as cidades
	 */
	public void resolver(int[][] distancias) {
		int n = distancias.length;
		boolean[] visitado = new boolean[n];
		rota = new int[n + 1];
		custoTotal = 0;

		int atual = 0;
		visitado[atual] = true;
		rota[0] = atual;

		for (int passo = 1; passo < n; passo++) {
			int proxima = -1;
			int menorDist = Integer.MAX_VALUE;

			for (int c = 0; c < n; c++) {
				if (!visitado[c] && distancias[atual][c] < menorDist) {
					menorDist = distancias[atual][c];
					proxima = c;
				}
			}

			rota[passo] = proxima;
			visitado[proxima] = true;
			custoTotal += menorDist;
			atual = proxima;
		}

		rota[n] = 0;
		custoTotal += distancias[atual][0];
	}

	public int[] getRota() {
		return rota;
	}

	public int getCustoTotal() {
		return custoTotal;
	}

	/**
	 * Gera uma matriz de distancias aleatoria (simetrica). Os valores ficam entre 1
	 * e maxDist.
	 */
	public static int[][] gerarMatriz(int n, int maxDist) {
		int[][] mat = new int[n][n];
		java.util.Random rand = new java.util.Random();
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int d = 1 + rand.nextInt(maxDist);
				mat[i][j] = d;
				mat[j][i] = d;
			}
		}
		return mat;
	}
}
