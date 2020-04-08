package zadanie1;

import java.util.Scanner;

import zadanie1.RGB;

public class RGBController {


	private Scanner scan = new Scanner(System.in);
	private RGB kolorTrzy = new RGB(0, 0, 0);
	
	 public void setColors(RGB kolor1, RGB kolor2) {

	        System.out.println("Podaj R_value dla  koloru 1: ");
	        kolor1.setR_value(scan.nextInt());
	      

	        System.out.println("Podaj G_value dla koloru 1: ");
	        kolor1.setG_value(scan.nextInt());
	       

	        System.out.println("Podaj B_value dla koloru 1: ");
	        kolor1.setB_value(scan.nextInt());
	

	        System.out.println("Podaj R_value dla  koloru 2: ");
	        kolor2.setR_value(scan.nextInt());
	    

	        System.out.println("Podaj G_value dla koloru 2: ");
	        kolor2.setG_value(scan.nextInt());
	     

	        System.out.println("Podaj B_value dla koloru 2:");
	        kolor2.setB_value(scan.nextInt());
	     

	        System.out.println();
	    }
	 public void writelnKolor(RGB kolor1) {
		 System.out.println("["+kolor1.getR_value() + ", " + kolor1.getG_value()+
	     ", " + kolor1.getB_value()+ "]");
	     
	    }

	 public void mix(RGB kolor1, RGB kolor2)            
	    {
	        kolorTrzy.setR_value((kolor1.getR_value() + kolor2.getR_value())/2);
	        kolorTrzy.setG_value((kolor1.getG_value() + kolor2.getG_value())/2);
	        kolorTrzy.setB_value((kolor1.getB_value() + kolor2.getB_value())/2);
	        writelnKolor(kolorTrzy);
	    }
	
	
	
}
