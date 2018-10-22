


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Arrays;


public class QuickSortTernarioInsertionSort {
	
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
        quickSort(vetEnt);
        crono = System.currentTimeMillis() - crono;
        System.out.println("Quick Sort Ternario ordenou ordenou a "+arqEntrada+" em: \t" + crono  + "\t milissegundo(s)");

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

	public static int particiona(int vetor[], int inicio, int fim, int pivo){
		int esquerda = inicio;
		int direita = fim -1;
		
		 while (true) {
            //maior
            while (vetor[++esquerda] < pivo);
            //menor
            while (vetor[--direita] > pivo);
            if (esquerda >= direita){
                break;
            } else {
                troca(vetor, esquerda, direita);
            }
        }
        troca(vetor, esquerda, fim - 1);
        return esquerda;
			
	}
	
	public static void troca(int[] vetor, int i, int j){
		int aux = vetor[i];
		vetor[i] = vetor[j];
		vetor[j] = aux;
	}
	
	public static int mediana(int [] vetor, int inicio, int fim){
		 int mediana = (inicio + fim) / 2;
        
        if (vetor[inicio] > vetor[mediana]) {
            troca(vetor, inicio, mediana);
        }
        
        if (vetor[inicio] > vetor[fim]) {
            troca(vetor, inicio, fim);
        }
        
        if (vetor[mediana] > vetor[fim]) {
            troca(vetor, mediana, fim);
        }

        troca(vetor, mediana, fim - 1); // pivo no fim
        return vetor[fim - 1]; // retorna mediana
	}
	
	public static void reQuickSort(int [] vetor, int esquerda, int direita){
		int size = direita - esquerda + 1;
        if (size <= 100) 
        {
            insertionSort(vetor, esquerda, direita);
        } else // quicksort if large
        {
            int mediana = mediana(vetor, esquerda, direita);
            int particao = particiona(vetor, esquerda, direita, mediana);
            reQuickSort(vetor, esquerda, particao - 1);
            reQuickSort(vetor, particao + 1, direita);
        }
	}
	
	public static int[] quickSort(int[] vetor) {
        reQuickSort(vetor, 0, vetor.length - 1);
        return vetor;
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
			
					QuickSortTernarioInsertionSort.recebeVetorEntrada(arqEntrada, "saida/saida_"+arqEntrada, tamVetor);
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
//        QuickSort.recebeVetorEntrada(arqEntrada, arqSaida, tamVetor);
    }
}
