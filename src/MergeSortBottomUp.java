/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author wfbr
 */
public class MergeSort {
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
        mergeSort(vetEnt, 0, vetEnt.length - 1);
        crono = System.currentTimeMillis() - crono;
        System.out.println("Merge Sort ordenou a sequência em: \t" + crono  + "\t milissegundo(s)");

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

    public static void mergeSort(int vetEnt[], int ini, int fim) {

        if (ini < fim) {
            int meio = (ini + fim) / 2;
            mergeSort(vetEnt, ini, meio);
            mergeSort(vetEnt, meio + 1, fim);
            intercala(vetEnt, ini, meio, fim);
        }
    }

    public static void intercala(int vetEnt[], int ini, int meio, int fim) {

        int[] vetAux = new int[vetEnt.length];
        for (int i = ini; i <= fim; i++) {
            vetAux[i] = vetEnt[i];
        }

        int esq = ini;
        int dir = meio + 1;
        //int atual = ini;
        
        for(int k = ini; k <= fim; k++){
            if(esq > meio) 
                vetEnt[k] = vetAux[dir++];
            else if(dir>fim)
                vetEnt[k] = vetAux[esq++];
            else if(vetAux[esq] < vetAux[dir])
                vetEnt[k] = vetAux[esq++];
            else
                vetEnt[k] = vetAux[dir++];           
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
        MergeSort.recebeVetorEntrada(arqEntrada, arqSaida, tamVetor);
    }
}
