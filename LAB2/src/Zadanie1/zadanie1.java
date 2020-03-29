package Zadanie1;

import java.util.Random;
import java.util.Scanner;

public class zadanie1 {
		public static void srednia(double [] tablica) {
			
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random losowanie = new Random(); 
		while(1!=0) {
		System.out.println("Podaj d³ugoœæ tablicy: ");
		Scanner dane = new Scanner(System.in);
		int dlugoscTablicy = dane.nextInt();
		if(dlugoscTablicy <=0) {
			System.out.println("Dlugosc talbicy musi byc > 0 ");
		}else {
			
		int licznik=0;
		double min=0;
		double max = 0 ;
		int k = 1 ;// zmienna do wyswietlania numeru losowania
		double srednia = 0 ;
		double[] naszaTablica = new double[dlugoscTablicy];
		double[] liczby = {2,3,3.5,4,4.5,5};
		double[] minTablica = new double[dlugoscTablicy];
		double[] wieksze = new double[dlugoscTablicy];
	//	double[] mniejsze = new double[dlugoscTablicy];
		double wynikSrednia = 0;
		double odchylenieStandardowe = 0 ;
		//LOSOWANIE
		for(int i=0; i<naszaTablica.length; i++) {
			naszaTablica[i]= liczby[losowanie.nextInt(liczby.length)];
		}	
		
		 min = naszaTablica[0] ;//jesli bedzie to 1 element to bedzie to min 
		for(int i=0; i<naszaTablica.length; i++) {
			
			System.out.println("Losowanie nr " + k + " : "+ naszaTablica[i]);
			k++;
			
		//MAX
			if(max < naszaTablica[i]) {
				max = naszaTablica[i];//wartosc max
			}
		//MIN	
			 if( naszaTablica[i] < min) {
				min = naszaTablica[i];//wartosc min
			 }

			
		//srednia
			srednia = (srednia + naszaTablica[i]);
			if( i < naszaTablica.length) wynikSrednia = srednia/dlugoscTablicy;

		
		}
		System.out.println("Œrednia: "+wynikSrednia);
		System.out.println("Min: "+min);	 
		System.out.println("Max: "+max);	
		System.out.println("Wartoœci > od œredniej: ");
		
		//WARTOSCI WIEKSZE OD SREDNIEJ
		for(int i = 0 ; i < naszaTablica.length; i++) {
			if(naszaTablica[i]>=wynikSrednia) {
				wieksze[licznik]=naszaTablica[i];
				System.out.println(wieksze[licznik]);
				licznik++;
			}
		}
		double wynikStandardowa = 0 ;
		double sumaStandardowa  = 0 ;
		double wynikWariancji = 0 ;
		//ODCHYLENIE STANDARDOWE 
		for(int i = 0 ; i <naszaTablica.length ; i++) {
			wynikWariancji  = wynikWariancji + Math.pow((naszaTablica[i] - wynikSrednia), 2);
			if(i < naszaTablica.length) {
				wynikWariancji = Math.sqrt((wynikWariancji/dlugoscTablicy));
				wynikStandardowa = Math.sqrt(wynikWariancji);
			}
		}
		System.out.println("Odchylenie standardowe: " + wynikStandardowa);

		
			}
		} 
	}


}
