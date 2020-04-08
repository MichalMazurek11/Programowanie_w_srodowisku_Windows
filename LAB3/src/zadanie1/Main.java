package zadanie1;

import zadanie1.RGBController;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RGB kolorJeden = new RGB(0, 0, 0);
		RGB kolorDwa = new RGB(0, 0, 0);
		RGBController RGB= new RGBController();
		RGB.setColors(kolorJeden, kolorDwa);
		RGB.writelnKolor(kolorJeden);
		RGB.writelnKolor(kolorDwa);
		RGB.mix(kolorJeden, kolorDwa);
		

	}
}
