import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class GeraNumeros {

	private static void imprimeVetor(int[] vetor, int tam, BufferedWriter bufferedWriter)
	{
	    for(int i = 0; i < tam; i++)
	    {
	    	try
	    	{
	            bufferedWriter.write(String.valueOf(vetor[i]));
	            bufferedWriter.newLine();
	        }
	        catch(IOException ex)
	        {
	        	ex.printStackTrace();
	        }
	    }
	}

	private static void geraNumerosDistintos(int[] vetor, int tam, int lim, Random rdGen)
	{
	    int[] aux = new int[lim];
	    int i, j, idx;

	    for(i = 0; i < lim; i++)
	        aux[i] = i;

	    for(i = 0; i < tam; i++)
	    {
	        idx = rdGen.nextInt(lim);
	        vetor[i] = aux[idx];

	        for(j = idx; j < lim - 1; j++)
	            aux[j] = aux[j+1];

	        aux[j] = -1;
	        lim--;
	    }
	}

	private static void geraNumerosRepetidos(int[] vetor, int tam, int lim, Random rdGen)
	{
	    int i;
	    for(i = 0; i < tam; i++)
			vetor[i] = rdGen.nextInt(lim);
	}

	private static void ordena(int[] vet, int tam, int tipo)
	{
	    int i, j, h, aux;

		h = 1;
		do
		{
			h = 3*h + 1;
		} while(h <= tam);

		do
		{
			h = h / 3;
			for(i=h; i<tam; i++)
			{
				aux = vet[i];
				j = i;

	            if(tipo == 1)
	            {
	                while(vet[j-h] > aux)
	                {
	                    vet[j] = vet[j-h];
	                    j = j - h;
	                    if(j <= h)
	                        break;
	                }
	            }
	            else
	            {
	                while(vet[j-h] < aux)
	                {
	                    vet[j] = vet[j-h];
	                    j = j - h;
	                    if(j <= h)
	                        break;
	                }
	            }
				vet[j] = aux;
			}
		} while(h != 1);
	}
	
	public static void main(String[] args)
	{
		int tam;    // tamanho do vetor
		int lim;    // limite do random;
		int tipo;   // organização dos elementos (0 - aleatório, 1 - crescente, 2 - decrescente)
		int rpt;    // se os elementos são distintos ou não (0 - distintos, 1 - com repetição)
		String nomeArq;
		Random randomGenerator = null;
	    FileWriter fileWriter = null;
	    BufferedWriter bufferedWriter = null;
		
		Scanner keyboard = new Scanner(System.in);

	    System.out.println("Como estao organizados os numeros?\n(0 - aleatorios; 1 - crescente; 2 - decrescente)");
	    tipo = keyboard.nextInt();
	    while(tipo < 0 || tipo > 2)
	    {
	        System.out.println("Como estao organizados os numeros?\n(0 - aleatorios; 1 - crescente; 2 - decrescente)");
	        tipo = keyboard.nextInt();
	    }
	    System.out.println("Os elementos sao distintos ou pode haver repeticao?\n(0 - distintos; 1 - com repeticao");
	    rpt = keyboard.nextInt();
	    while(rpt != 0 && rpt != 1)
	    {
	    	System.out.println("Os elementos sao distintos ou pode haver repeticao?\n(0 - distintos; 1 - com repeticao");
	    	rpt = keyboard.nextInt();
	    }
	    System.out.print("Informe o tamanho do vetor: ");
	    tam = keyboard.nextInt();
	    System.out.print("Informe o limite dos numeros: ");
	    lim = keyboard.nextInt();
	    while(rpt == 0 && lim < tam)
	    {
	    	System.out.println("ERRO: O limite deve ser maior ou igual ao tamanho do vetor!");
	    	System.out.print("Informe o limite dos numeros: ");
	    	lim = keyboard.nextInt();
	    }
	    while(rpt == 1 && lim >= tam)
	    {
	    	System.out.println("ERRO: O limite deve ser menor que o tamanho do vetor!");
	    	System.out.print("Informe o limite dos numeros: ");
	    	lim = keyboard.nextInt();
	    }
	    System.out.print("Informe o nome do arquivo de saida: ");
	    nomeArq = keyboard.next();

	    try
	    {
	    	fileWriter = new FileWriter(nomeArq);
	    	bufferedWriter = new BufferedWriter(fileWriter);
	    }
	    catch(IOException ex)
	    {
            System.out.println("Erro ao criar o arquivo '" + nomeArq + "'");
        }

	    int[] vetor = new int[tam];
	    randomGenerator = new Random();

	    if(rpt == 0)
	        geraNumerosDistintos(vetor, tam, lim, randomGenerator);
	    else
	        geraNumerosRepetidos(vetor, tam, lim, randomGenerator);

	    if(tipo == 1)
	        ordena(vetor, tam, 1);
	    else if(tipo == 2)
	        ordena(vetor, tam, 2);

	    imprimeVetor(vetor, tam, bufferedWriter);

	    try
	    {
	    	bufferedWriter.close();
		    fileWriter.close();
		    keyboard.close();
	    }
	    catch(IOException ex)
	    {
            System.out.println("Erro ao fechar o arquivo '" + nomeArq + "'");
        }
	}
}
