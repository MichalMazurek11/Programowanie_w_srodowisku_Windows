package Zadanie1;

	import java.util.HashSet;
	import java.util.Scanner;
	import java.util.Set;

	public class zadanie1 {

		public static void main(String[] args) {
			Set<Integer> Lista = new HashSet<>();
		
			while(1!=0) {
			System.out.println("\r1.Podaj warto��");
			System.out.println("2.Wy�wietl liczby");
			System.out.println("3.Wy�wietl liczb� unikatowych warto�ci");
			System.out.println("0.Wyjd�\n");
		//	Set<Integer> Test = new HashSet<>();
			Scanner input = new Scanner(System.in);
			int wybor = input.nextInt();
			switch (wybor) {
            case 1:
                System.out.print("Podaj liczb�: ");
                int number = input.nextInt();
                Lista.add(number);
            break;
            case 2:
            	for(Object element : Lista ) {
            		System.out.print(element+" ");
            	}
            	break;
            case 3:
                System.out.println("Liczba unikatowych element�w: "+Lista.size());
                System.out.println("--------------");
                
            break;
            case 0:
                System.exit(0);
            break;
            default:
                System.out.println("Podales zla wybor");
            break;
		

			}
		}

	}
}