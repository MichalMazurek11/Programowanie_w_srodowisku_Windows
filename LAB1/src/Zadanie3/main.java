package Zadanie3;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class main {

	public static void main(String[] args) {
		
		Set<String> zbiorySuma = new HashSet<>();
		Set<String> zbioryRoznica= new HashSet<>();
		Set<String> zbiorTrzy = new HashSet<>();
		Set<String> zbiorCztery = new HashSet<>();
		Set<String> zbiorPiec = new HashSet<>();
		Set<String> zbiorJeden = new HashSet<>();
		Set<String> zbiorCzesc = new HashSet<>();
		Set<String> zbior = new HashSet<>();
		System.out.println("Podaj ile chcesz liczb wprowadzic:  ");
		Scanner scaner= new Scanner(System.in);
	    String liczba1 = scaner.next();
		for(int i = 0; i < Integer.decode(liczba1);i++) {
			System.out.println("Podaj elementy: ");
			Scanner element = new Scanner(System.in);
			String ele = element.next();
			zbiorJeden.add(ele);
		}
	
		
		
		
		//drugi zbior
		Set<String> zbiorDwa = new HashSet<>();
		System.out.println("Podaj ile chcesz liczb wprowadzic do zbioru 2-giego:  ");
		Scanner dwa = new Scanner(System.in);
	    String liczba2 = dwa.next();
		for(int i = 0; i < Integer.decode(liczba2);i++) {
			System.out.println("Podaj elementy dla zbioru drugiego2: ");
			Scanner elementDwa = new Scanner(System.in);
			String ele = elementDwa.next();
			zbiorDwa.add(ele);
		}
		 System.out.println("Zbiór A: " + zbiorJeden);
		 System.out.println("Zbiór B: " + zbiorDwa);
		
		   //SUMA
		        zbiorySuma.addAll(zbiorJeden);
		        zbiorySuma.addAll(zbiorDwa);

		        System.out.println("Suma zbiorów wynosi: " + zbiorySuma);
		        
		   //ODEJMOWANIE A-B
		        zbioryRoznica.addAll(zbiorJeden);
		        zbioryRoznica.removeAll(zbiorDwa);

		        System.out.println("Ró¿nica zbiorów wynosi: " + zbioryRoznica);
		        
		    
		  //Czêœc wspolna
		      	zbiorTrzy.addAll(zbiorJeden);
		        zbiorCztery.addAll(zbiorDwa);
		        zbiorTrzy.retainAll(zbiorCztery);

		        System.out.println("Czêœæ wspólna zbiorów: " + zbiorTrzy);
		
		        
		  //roznica symetryczna  
		        zbiorTrzy.addAll(zbiorJeden);
		        zbiorCztery.addAll(zbiorDwa);
		        zbiorTrzy.removeAll(zbiorDwa);
		        zbiorCzesc.addAll(zbiorTrzy);
		        zbiorPiec.addAll(zbiorTrzy);
		        zbiorCztery.removeAll(zbiorJeden);
		        zbiorPiec.addAll(zbiorCztery);
		    
		        System.out.println("Ró¿nica symetryczna zbiorów: " + zbiorPiec);
		       
	}

}
