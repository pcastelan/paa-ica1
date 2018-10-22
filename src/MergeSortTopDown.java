

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;


public class MergeSortTopDown {
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
        ordena(vetEnt, 0, vetEnt.length - 1);
        crono = System.currentTimeMillis() - crono;
        System.out.println("MergeTopDown ordenou a "+arqEntrada+" em: \t" + crono  + "\t milissegundo(s)");

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


	public static void ordena (int[] vetor, int inicio, int fim){
		if(inicio >= fim)
			return;
		
		int meio = (inicio+fim)/2;
		
		ordena(vetor, inicio, meio);
		ordena(vetor, meio+1, fim);
		merge(vetor, inicio, meio, fim);
		
		
	}
	
	public static void merge(int[] vetor, int inicio, int meio, int fim){
		
		int[] aux1 = new int[meio-inicio+1];
		int[] aux2 = new int[fim - meio];
		
		for (int i=0; i<aux1.length; ++i) 
            aux1[i] = vetor[inicio + i]; 
        for (int j=0; j<aux2.length; ++j) 
            aux2[j] = vetor[meio + 1+ j]; 
		
		int i=0, j=0, k = inicio;
		
		while(i	< aux1.length && j < aux2.length){
			if(aux1[i] <= aux2[j]){
				vetor[k] = aux1[i];
				i++;
			} 
			else {
				vetor[k] = aux2[j];
				j++;
			}
			
			k++;
		}
		
		while (i < aux1.length) 
        { 
            vetor[k] = aux1[i]; 
            i++; 
            k++; 
        } 
        while (j < aux2.length) 
        { 
            vetor[k] = aux2[j]; 
            j++; 
            k++; 
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
        MergeSortTopDown.recebeVetorEntrada(arqEntrada, arqSaida, tamVetor);
    }
}
