package Zadanie2;

import java.util.Scanner;


public class zadanie2 {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(1!=0) {
		System.out.println("Podaj ciag binarny: ");
		 Scanner input = new Scanner(System.in);
	        String liczba = input.next();
	        int licznik = 0;
	        // przeszukuje ciag
	        for(int i = 0; i < liczba.length()-2; i++){ // -2 aby potem sprawdzi 2 kolejne znaki
	            if(liczba.charAt(i) == 49){//porownujemy znak czy bedzie "1" jesli tak
	            	if(liczba.charAt(i+1)==48) { // to czy kolejny znak bedzie "0" jesli tak
	                for(int j = i + 1; j < liczba.length(); j++){// to sprawdzam czy natrafie na kolejna 
	                    if(liczba.charAt(j) == 49){// "1" jestli tak to zliczam
	                             licznik++;
	                        break;
	                    }
	                }
	                }
	            }
	        }
	        System.out.println("Liczba dziur: "+licznik);
	    }
	}
}