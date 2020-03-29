package zadanie2;

import java.util.Random;
import java.util.Scanner;

public class zadanie2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj rozmiar macierzy: ");
        
        int rozmiar = scanner.nextInt();

        int[][] macierzA = new int[rozmiar][rozmiar];
        int[][] macierzB = new int[rozmiar][rozmiar];
        int[][] suma = new int[rozmiar][rozmiar];
        int[][] roznica = new int[rozmiar][rozmiar];
        int[][] iloczyn = new int[rozmiar][rozmiar];
        Random rand = new Random();

        for(int i = 0; i < rozmiar; i++) {
            for(int j = 0; j < rozmiar; j++)
                macierzA[i][j] = rand.nextInt(21) - 10;
        }
            for(int i = 0; i < rozmiar; i++) {
                for(int j = 0; j < rozmiar; j++)
                    macierzB[i][j] = rand.nextInt(21) - 10;

            }


        System.out.println("Macierz A: ");
    	
        for (int i = 0; i < rozmiar; i++) {
            for (int j = 0; j < rozmiar; j++)
                System.out.print(macierzA[i][j] + " ");
            System.out.println();
        }
        System.out.println("Macierz B: ");
    	
        for (int i = 0; i < rozmiar; i++) {
            for (int j = 0; j < rozmiar; j++)
                System.out.print(macierzB[i][j] + " ");
                System.out.println();
        }
        
        System.out.println("Suma A+B: ");
        
        for(int i = 0; i < rozmiar; i++) {
            for(int j = 0; j < rozmiar; j++) {
                suma[i][j] = macierzA[i][j] + macierzB[i][j];
                System.out.print(suma[i][j] + " ");
            }
            
            System.out.println();
        }
    
        
        	System.out.println("Roznica A-B: ");
        
        for(int i = 0; i < rozmiar; i++) {
            for(int j = 0; j < rozmiar; j++) {
                roznica[i][j] = macierzA[i][j] - macierzB[i][j];
                System.out.print(roznica[i][j] + " ");
            }
            
            System.out.println();
        }
        
        
        
  System.out.println("\nMacierz iloczynu (A x B):");
        
        for(int i = 0; i < rozmiar; i++) {
            for(int j = 0; j < rozmiar; j++) {
                int sumaIloczynu = 0;

                for(int k = 0; k < rozmiar; k++) {
                    sumaIloczynu =sumaIloczynu + (macierzA[i][k] * macierzB[k][j]);
                }

                iloczyn[i][j] = sumaIloczynu;
                System.out.print(iloczyn[i][j] + " ");
            }

            System.out.println();
        }
        
        
        
        
    }
}