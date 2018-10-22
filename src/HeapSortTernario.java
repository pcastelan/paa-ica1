
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class HeapSortTernario<E extends Comparable<E>> {

	private E[] heap;
	private int size = 0;

	public static long recebeVetorEntrada(String arqEntrada, String arqSaida, int tamVetor) {

		FileReader fr;
		BufferedReader br;
		int vetEnt[] = new int[tamVetor];
		try {
			fr = new FileReader(arqEntrada);
			br = new BufferedReader(fr);

			for (int i = 0; i < vetEnt.length; i++) {
				vetEnt[i] = Integer.parseInt(br.readLine());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		long crono = System.currentTimeMillis();
		int[] sorted = sort(vetEnt);
		crono = System.currentTimeMillis() - crono;
		System.out.println("Heap ordenou a " + arqEntrada + " em: \t" + crono + "\t milissegundo(s)");

		try {
			PrintStream ps;
			ps = new PrintStream(arqSaida);
			for (int i = 0; i < vetEnt.length; i++) {
//				ps.println(Arrays.toString(vetEnt));
				ps.println(Arrays.toString(sorted));
				break;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return crono;
	}

	HeapSortTernario(E[] init) {
		heap = init;
	}

	public void insert(E item) {
		heap[size] = item;
		size++;

		heapifyUp();
	}

	/**
	 * Remove e retorna o item na raiz do heap
	 */
	public E remove() {
		if (size == 0) {
			System.exit(0);
		}

		E oldRoot = heap[0];
		heap[0] = heap[size - 1]; //move o ultimo no para a raiz
		heap[size - 1] = null; // e remove
		size--;

		heapifyDown();
		return oldRoot;
	}

	public int size() {
		return size;
	}

	/**
	 * ordena o heap para que todos os pais sejam menores que os filhos começa
	 * na raiz e vai descendo
	 */
	private void heapifyDown() {
		int current = 0; //começa na raiz

		while (hasLeftChild(current)) {
			int smallestChild = getLeftChild(current);

			if (hasMiddleChild(current)
					&& heap[getMiddleChild(current)].compareTo(heap[smallestChild]) < 0) {
				smallestChild = getMiddleChild(current);
			}

			if (hasRightChild(current)
					&& heap[getRightChild(current)].compareTo(heap[smallestChild]) < 0) {
				smallestChild = getRightChild(current);
			}

			if (heap[current].compareTo(heap[smallestChild]) > 0) {
				swap(current, smallestChild);
			} else {
				break; // nó atual é maior que o filho
			}

			current = smallestChild;
		}
	}

	/**
	 * ordena o array para que os pais sejam menores que os filhos começa no
	 * ultimo nó inserido e sobe
	 */
	private void heapifyUp() {
		int current = size - 1; //

		while (current >= 1
				&& heap[getParent(current)].compareTo(heap[current]) > 0) { //and the parent is greater than the child
			swap(current, getParent(current));
			current = getParent(current);
		}
	}

	private void swap(int i, int j) {
		E swap = heap[i];
		heap[i] = heap[j];
		heap[j] = swap;		
	}

	/**
	 * pega o index de qualquer nó pai no array
	 */
	private int getParent(int index) {
		if (index % 3 == 0) {
			return (index / 3 - 1);
		}
		return (int) (index / 3);
	}

	/**
	 * chekca se um pai tem um filho na esquerda
	 */
	private boolean hasLeftChild(int index) {
		return getLeftChild(index) < size;
	}

	/**
	 * pega o index do nó filho esquerdo
	 */
	private int getLeftChild(int index) {
		return index * 3 + 1;
	}

	/**
	 * checa se um pai tem um nó filho no meio
	 */
	private boolean hasMiddleChild(int index) {
		return getMiddleChild(index) < size;
	}

	/**
	 * pega o index do nó filho do meio
	 */
	private int getMiddleChild(int index) {
		return index * 3 + 2;
	}

	/**
	 * checa se um pai tem um nó filho na direita
	 */
	private boolean hasRightChild(int index) {
		return getRightChild(index) < size;
	}

	/**
	 * pega o index de um nó filho da direita
	 */
	private int getRightChild(int index) {
		return index * 3 + 3;
	}

	public static int[] sort(int[] input) {

		Integer[] array = new Integer[input.length];
		int[] result = new int[input.length];
		HeapSortTernario<Integer> heap = new HeapSortTernario<>(array);

		for (int i = 0; i < array.length; i++) {
			array[i] = input[i];
			heap.insert(array[i]);
		}

		for (int i = 0; i < array.length; i++) {
			result[i] = heap.remove();

		}

		return result;
	}

	public static void main(String[] args) {
		String[] tipoS = {"aleatorio", "crescente", "descrescente"};
		String[] rptS = {"distintos", "repetidos"};
		String arqEntrada;
		for (int i = 100000; i <= 600000; i += 50000) {
			int tamVetor = i;
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 2; k++) {
					arqEntrada = Integer.toString(i) + tipoS[j] + "_" + rptS[k] + ".txt";

					HeapSortTernario.recebeVetorEntrada(arqEntrada, "saida/saida_" + arqEntrada, tamVetor);
				}
			}
		}
//
//        Scanner scan = new Scanner(System.in);
//        System.out.print("Informe a quantidade de números a ser ordenada: ");
//        int tamVetor = scan.nextInt();
//        System.out.print("Informe o nome do Arquivo que contém os números a serem ordenados: ");
//        String arqEntrada = scan.next();
//        System.out.print("Informe o nome do Arquivo que irá conter os números depois de ordenados: ");
//        String arqSaida = scan.next();
//        HeapSortTernario.recebeVetorEntrada(arqEntrada, arqSaida, tamVetor);
	}
}
