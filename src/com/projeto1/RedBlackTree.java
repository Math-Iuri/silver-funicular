package com.projeto1;

/**
 * Árvore Rubro-Negra (Left-Leaning Red-Black Tree – Sedgewick)
 *
 * Invariantes LLRB: 1. Links vermelhos sempre inclinam para a esquerda 2.
 * Nenhum nó tem dois links vermelhos consecutivos 3. Altura negra perfeita
 * (mesma quantidade de links pretos em todos os caminhos até null)
 *
 * Complexidades: Inserção, busca, remoção: O(log n) Altura máxima: ≤ 2 *
 * log2(n)
 */
public class RedBlackTree {

	private static final boolean VERMELHO = true;
	private static final boolean PRETO = false;

	private static class No {
		int valor;
		No esq, dir;
		boolean cor; // cor do link que aponta a este nó
		int tam;

		No(int v) {
			valor = v;
			cor = VERMELHO;
			tam = 1;
		}
	}

	private No raiz;

	private boolean vermelho(No n) {
		return n != null && n.cor == VERMELHO;
	}

	private int tam(No n) {
		return n == null ? 0 : n.tam;
	}

	private void atualizarTam(No n) {
		n.tam = 1 + tam(n.esq) + tam(n.dir);
	}

	private No rotEsq(No h) {
		No x = h.dir;
		h.dir = x.esq;
		x.esq = h;
		x.cor = h.cor;
		h.cor = VERMELHO;
		x.tam = h.tam;
		atualizarTam(h);
		return x;
	}

	private No rotDir(No h) {
		No x = h.esq;
		h.esq = x.dir;
		x.dir = h;
		x.cor = h.cor;
		h.cor = VERMELHO;
		x.tam = h.tam;
		atualizarTam(h);
		return x;
	}

	private void inverterCores(No h) {
		h.cor = !h.cor;
		h.esq.cor = !h.esq.cor;
		h.dir.cor = !h.dir.cor;
	}

	public void inserir(int valor) {
		raiz = inserir(raiz, valor);
		raiz.cor = PRETO;
	}

	private No inserir(No h, int valor) {
		if (h == null)
			return new No(valor);

		if (valor < h.valor)
			h.esq = inserir(h.esq, valor);
		else if (valor > h.valor)
			h.dir = inserir(h.dir, valor);

		if (vermelho(h.dir) && !vermelho(h.esq))
			h = rotEsq(h);
		if (vermelho(h.esq) && vermelho(h.esq.esq))
			h = rotDir(h);
		if (vermelho(h.esq) && vermelho(h.dir))
			inverterCores(h);

		atualizarTam(h);
		return h;
	}

	public boolean buscar(int valor) {
		No atual = raiz;
		while (atual != null) {
			if (valor == atual.valor)
				return true;
			else if (valor < atual.valor)
				atual = atual.esq;
			else
				atual = atual.dir;
		}
		return false;
	}

	public void remover(int valor) {
		if (!buscar(valor))
			return;
		if (!vermelho(raiz.esq) && !vermelho(raiz.dir))
			raiz.cor = VERMELHO;
		raiz = remover(raiz, valor);
		if (raiz != null)
			raiz.cor = PRETO;
	}

	private No remover(No h, int valor) {
		if (valor < h.valor) {
			if (!vermelho(h.esq) && !vermelho(h.esq.esq))
				h = moverVermElhoPraEsq(h);
			h.esq = remover(h.esq, valor);
		} else {
			if (vermelho(h.esq))
				h = rotDir(h);
			if (valor == h.valor && h.dir == null)
				return null;
			if (h.dir != null && !vermelho(h.dir) && !vermelho(h.dir.esq))
				h = moverVermelhoParaDir(h);
			if (valor == h.valor) {
				No min = minimo(h.dir);
				h.valor = min.valor;
				h.dir = removerMinimo(h.dir);
			} else {
				h.dir = remover(h.dir, valor);
			}
		}
		return balancear(h);
	}

	private No moverVermElhoPraEsq(No h) {
		inverterCores(h);
		if (h.dir != null && vermelho(h.dir.esq)) {
			h.dir = rotDir(h.dir);
			h = rotEsq(h);
			inverterCores(h);
		}
		return h;
	}

	private No moverVermelhoParaDir(No h) {
		inverterCores(h);
		if (h.esq != null && vermelho(h.esq.esq)) {
			h = rotDir(h);
			inverterCores(h);
		}
		return h;
	}

	private No balancear(No h) {
		if (vermelho(h.dir) && !vermelho(h.esq))
			h = rotEsq(h);
		if (vermelho(h.esq) && vermelho(h.esq.esq))
			h = rotDir(h);
		if (vermelho(h.esq) && vermelho(h.dir))
			inverterCores(h);
		atualizarTam(h);
		return h;
	}

	private No minimo(No h) {
		while (h.esq != null)
			h = h.esq;
		return h;
	}

	private No removerMinimo(No h) {
		if (h.esq == null)
			return null;
		if (!vermelho(h.esq) && !vermelho(h.esq.esq))
			h = moverVermElhoPraEsq(h);
		h.esq = removerMinimo(h.esq);
		return balancear(h);
	}

	public int altura() {
		return altura(raiz);
	}

	private int altura(No n) {
		if (n == null)
			return 0;
		return 1 + Math.max(altura(n.esq), altura(n.dir));
	}

	public void limpar() {
		raiz = null;
	}
}
