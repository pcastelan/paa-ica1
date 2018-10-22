

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;


public class HeapSort {
	

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
        ordena(vetEnt);
        crono = System.currentTimeMillis() - crono;
        System.out.println("Heap ordenou a "+arqEntrada+" em: \t" + crono  + "\t milissegundo(s)");

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

    public static void heap(int[] vetor, int tamanho, int index){
		
		int pai = index;
		int esquerda = 2*index + 1;
		int direita = 2*index + 2;
		
		int aux;
		
		if(esquerda	< tamanho && vetor[esquerda] > vetor[pai]){
			pai = esquerda;
		}
		
		if(direita < tamanho && vetor[direita] > vetor[pai]){
			pai = direita;
		}
		
		if(pai != index){
			aux = vetor[index];
			vetor[index] = vetor[pai];
			vetor[pai] = aux;
			
			heap(vetor, tamanho, pai);
			
		}

	}

 

	public static void ordena(int[] vetor) {

		int tamanho = vetor.length;
		int aux;
		
		for (int i = tamanho/2 - 1; i >= 0; i--) {
			heap(vetor,	tamanho, i);
		}
		
		for(int i = tamanho-1 ; i>=0; i--){
			aux = vetor[0];
			vetor[0] = vetor[i];
			vetor[i] = aux;
			
			heap(vetor, i, 0);
		}

	}

    public static void main(String[] args) {
		String [] tipoS = {"aleatorio", "crescente", "descrescente"};
		String [] rptS = {"distintos", "repetidos"};
		String arqEntrada; 
		for (int i = 100000; i <= 600000; i+=50000) {
			int tamVetor = i;
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 2; k++) {
					arqEntrada = Integer.toString(i) + tipoS[j]+ "_"+ rptS[k] +".txt";
			
					HeapSort.recebeVetorEntrada(arqEntrada, "saida/saida_"+arqEntrada, tamVetor);
				}
			}
		}
		
		
//        Scanner scan = new Scanner(System.in);
//        System.out.print("Informe a quantidade de números a ser ordenada: ");
//        int tamVetor = scan.nextInt();
//        System.out.print("Informe o nome do Arquivo que contém os números a serem ordenados: ");
//        String arqEntrada = scan.next();
//        System.out.print("Informe o nome do Arquivo que irá conter os números depois de ordenados: ");
//        String arqSaida = scan.next();
//        HeapSort.recebeVetorEntrada(arqEntrada, arqSaida, tamVetor);
    }
}
