package com.projeto1;

/**
 * Árvore Binária de Busca (BST)
 *
 * Complexidades: Inserção, busca, remoção: O(log n) caso médio, O(n) pior caso
 * (degenerada) Altura esperada (aleatória): ~1.39 * log2(n)
 */
public class BSTTree {

	private static class No {
		int valor;
		No esq, dir;

		No(int v) {
			valor = v;
		}
	}

	private No raiz;

	public void inserir(int valor) {
		raiz = inserir(raiz, valor);
	}

	private No inserir(No no, int valor) {
		if (no == null)
			return new No(valor);
		if (valor < no.valor)
			no.esq = inserir(no.esq, valor);
		else if (valor > no.valor)
			no.dir = inserir(no.dir, valor);
		return no;
	}

	public boolean buscar(int valor) {
		No atual = raiz;
		while (atual != null) {
			if (valor == atual.valor)
				return true;
			atual = valor < atual.valor ? atual.esq : atual.dir;
		}
		return false;
	}

	public void remover(int valor) {
		raiz = remover(raiz, valor);
	}

	private No remover(No no, int valor) {
		if (no == null)
			return null;
		if (valor < no.valor) {
			no.esq = remover(no.esq, valor);
		} else if (valor > no.valor) {
			no.dir = remover(no.dir, valor);
		} else {
			if (no.esq == null)
				return no.dir;
			if (no.dir == null)
				return no.esq;
			No suc = minimo(no.dir);
			no.valor = suc.valor;
			no.dir = remover(no.dir, suc.valor);
		}
		return no;
	}

	private No minimo(No no) {
		while (no.esq != null)
			no = no.esq;
		return no;
	}

	public int altura() {
		return altura(raiz);
	}

	private int altura(No no) {
		if (no == null)
			return 0;
		return 1 + Math.max(altura(no.esq), altura(no.dir));
	}

	public void limpar() {
		raiz = null;
	}
}
