

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;


public class MergeSortBottomUp {
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
        System.out.println("Merge Sort Bottom Up ordenou a "+arqEntrada+" em: \t" + crono  + "\t milissegundo(s)");

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


	public static int[] ordena (int[] vetor){
		mergesort(vetor);
		return vetor;
		
		
	}
	
	public static void merge(int[] vetor, int[] aux, int inicio, int meio, int fim){
		
		int i = inicio;
		int j = meio+1; 
		int k = inicio;
		
		while (i <= meio && j <= fim) {
            if (vetor[i] < vetor[j]) {
                aux[k++] = vetor[i++];
            } else {
                aux[k++] = vetor[j++];
            }
        }

        // copia o que sobrou
        while (i < vetor.length && i <= meio) {
            aux[k++] = vetor[i++];
        }

        for (i = inicio; i <= fim; i++) {
            vetor[i] = aux[i];
        }
		
			
	}
	
	public static void mergesort(int[] vetor)
    {
        int menor = 0;
        int maior = vetor.length - 1;

        int[] temp = Arrays.copyOf(vetor, vetor.length);


        for (int m = 1; m <= maior - menor; m = 2*m)
        {
            for (int i = menor; i < maior; i += 2*m)
            {
                int de = i;
                int meio = i + m - 1;
                int para = Integer.min(i + 2 * m - 1, maior);

                merge(vetor, temp, de, meio, para);
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
        MergeSortBottomUp.recebeVetorEntrada(arqEntrada, arqSaida, tamVetor);
    }
}
