package com.util;

import java.util.Random;

/**
 * Classe auxiliar com utilitarios de estatistica e geracao de dados.
 *
 * Formulas usadas: Media: soma / n Desvio padrao: raiz( soma((xi - media)^2) /
 * (n-1) ) -- desvio amostral
 */
public class Stats {

	private static final Random rand = new Random();

	public static double media(double[] valores) {
		double soma = 0;
		for (double v : valores)
			soma += v;
		return soma / valores.length;
	}

	public static double desvioPadrao(double[] valores) {
		double m = media(valores);
		double soma = 0;
		for (double v : valores)
			soma += (v - m) * (v - m);
		return Math.sqrt(soma / (valores.length - 1));
	}

	public static double mediaNs(long[] valores) {
		long soma = 0;
		for (long v : valores)
			soma += v;
		return (double) soma / valores.length;
	}

	public static double desvioPadraoNs(long[] valores) {
		double m = mediaNs(valores);
		double soma = 0;
		for (long v : valores)
			soma += (v - m) * (v - m);
		return Math.sqrt(soma / (valores.length - 1));
	}

	public static void imprimirResultadoNs(String algoritmo, int n, long[] tempos) {
		double media = mediaNs(tempos);
		double dp = desvioPadraoNs(tempos);
		System.out.printf("  Algoritmo: %-22s  Tamanho: %-7d%n", algoritmo, n);
		System.out.printf("  Media: %.0f ns   Desvio padrao: %.0f ns%n%n", media, dp);
	}

	public static void imprimirResultado(String algoritmo, int n, double[] tempos) {
		double m = media(tempos);
		double dp = desvioPadrao(tempos);
		System.out.printf("  Algoritmo: %-22s  Tamanho: %-7d%n", algoritmo, n);
		System.out.printf("  Media: %.6f s   Desvio padrao: %.6f s%n%n", m, dp);
	}

	public static int[] gerarAleatorios(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = i + 1;
		for (int i = n - 1; i > 0; i--) {
			int j = rand.nextInt(i + 1);
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}
		return arr;
	}

	public static int[] gerarOrdenado(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = i + 1;
		return arr;
	}

	public static int[] gerarInvertido(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = n - i;
		return arr;
	}

	public static int[] copiar(int[] arr) {
		int[] copia = new int[arr.length];
		System.arraycopy(arr, 0, copia, 0, arr.length);
		return copia;
	}

	public static void separador() {
		System.out.println("  " + "-".repeat(72));
	}
}
