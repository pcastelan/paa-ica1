

public class MergeSortTopDownV1 {
	
	
	public static void main(String[] args) {
		
		int[] vetor = {1,2,9,12,6,4,7,3,1,44,99,125,6,5,666,9,4,96,6,465468,63,41,52,67,89,58};

		
		auxImprime(vetor);
		ordena(vetor, 0, vetor.length-1);
		auxImprime(vetor);
		
		
	}

	
	
	public static void auxImprime (int[] a){
		for(int i = 0; i<a.length; i++){
			System.out.println(i + " - " +a[i]);
		}
			System.out.println();
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
}
