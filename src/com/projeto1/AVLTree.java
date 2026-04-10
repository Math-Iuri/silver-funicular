package com.projeto1;

/**
 * Árvore AVL (auto-balanceada por rotações)
 *
 * Complexidades: Inserção, busca, remoção: O(log n) Altura máxima: ~1.44 *
 * log2(n)
 */
public class AVLTree {

	private static class No {
		int valor;
		int altura;
		No esq, dir;

		No(int v) {
			valor = v;
			altura = 1;
		}
	}

	private No raiz;

	private int h(No no) {
		return no == null ? 0 : no.altura;
	}

	private void atualizarAltura(No no) {
		no.altura = 1 + Math.max(h(no.esq), h(no.dir));
	}

	private int fb(No no) {
		return no == null ? 0 : h(no.esq) - h(no.dir);
	}

	private No rotDir(No y) {
		No x = y.esq;
		No T2 = x.dir;
		x.dir = y;
		y.esq = T2;
		atualizarAltura(y);
		atualizarAltura(x);
		return x;
	}

	private No rotEsq(No x) {
		No y = x.dir;
		No T2 = y.esq;
		y.esq = x;
		x.dir = T2;
		atualizarAltura(x);
		atualizarAltura(y);
		return y;
	}

	private No balancear(No no) {
		atualizarAltura(no);
		int f = fb(no);

		if (f > 1) {
			if (fb(no.esq) < 0)
				no.esq = rotEsq(no.esq);
			return rotDir(no);
		}
		if (f < -1) {
			if (fb(no.dir) > 0)
				no.dir = rotDir(no.dir);
			return rotEsq(no);
		}
		return no;
	}

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
		else
			return no;
		return balancear(no);
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
		return balancear(no);
	}

	private No minimo(No no) {
		while (no.esq != null)
			no = no.esq;
		return no;
	}

	public int altura() {
		return h(raiz);
	}

	public void limpar() {
		raiz = null;
	}
}
