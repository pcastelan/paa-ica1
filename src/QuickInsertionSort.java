


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;


public class QuickInsertionSort {

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
        quickSort(vetEnt, 0, vetEnt.length - 1);
        crono = System.currentTimeMillis() - crono;
        System.out.println("Quick Sort ordenou a sequência em: \t" + crono  + "\t milissegundo(s)");

        try {
            PrintStream ps;
            ps = new PrintStream(arqSaida);
            for (int i = 0; i < vetEnt.length; i++) {
                ps.println(Arrays.toString(vetEnt));
                break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return crono;
    }

    public static int particiona(int[] vetor, int esquerda, int direita)

{

      int cont1 = esquerda;
	  int cont2 = direita;
      int aux;
      int pivo = vetor[(esquerda + direita) / 2];

     

      while (cont1 <= cont2) {
            while (vetor[cont1] < pivo){
                  cont1++;
			}
            while (vetor[cont2] > pivo){
                  cont2--;
			}
            if (cont1 <= cont2) {
                  aux = vetor[cont1];
                  vetor[cont1] = vetor[cont2];
                  vetor[cont2] = aux;
                  cont1++;
                  cont2--;
            }
      }
  

      return cont1;

}

	
public static void insertionSort(int[] vetor, int inicio, int fim){
	for(int i = inicio+1; i<=fim; i++){
		int aux = vetor[i];
		int j=i-1;
		for(;j>=0 && vetor[j]>=aux; j--){
			vetor[j+1] = vetor[j];
		}
		vetor[j+1] = aux;
	}

	
}
 

public static void quickSort(int[] vetor, int esquerda, int direita) {
		

	if(direita-esquerda<=100){
		insertionSort(vetor, esquerda, direita);
	} else {
		int i = particiona(vetor, esquerda, direita);

		if (esquerda < i - 1){
			quickSort(vetor, esquerda, i - 1);
		}
		if (i < direita){
			quickSort(vetor, i, direita);
		}
	}

}

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Informe a quantidade de números a ser ordenada: ");
        int tamVetor = scan.nextInt();
        System.out.print("Informe o nome do Arquivo que contém os números a serem ordenados: ");
        String arqEntrada = scan.next();
        System.out.print("Informe o nome do Arquivo que irá conter os números depois de ordenados: ");
        String arqSaida = scan.next();
        QuickInsertionSort.recebeVetorEntrada(arqEntrada, arqSaida, tamVetor);
    }
}
