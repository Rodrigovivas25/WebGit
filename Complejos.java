import java.util.Scanner;


/**
*	Clase que implementa metodos para hacer operaciones entre numeros complejos.
*	@author Vivas Maldonado Rodrigo
*	@version 1.0.0
*	@since 2017-03-19
*/
public class Complejos{

	/**
	*	Metodo principal.
	*	@param args No utilizado.
	*/
	public static void main(String[] args){
		int opcion; 
		double real1, real2, comp1, comp2;
		
		do{
			System.out.println("---CALCULADORA DE NUMEROS COMPLEJOS---");
			System.out.println("1.-Suma \n2.-Resta\n3.-Multiplicación\n4.-División\n5.-Convertir a forma trigonométrica\n6.-Converir a forma exponencial\n7.-Salir");
			System.out.print("\nIngresa la opcion que quieras realizar: ");
			Scanner num = new Scanner(System.in);

			opcion = num.nextInt();
			
			
			switch(opcion){
				case 1:
					System.out.println("Ingresa 2 numeros complejos: ");
					System.out.print("Real 1: ");
					real1 = num.nextDouble();
					System.out.print("Complejo 1: ");
					comp1 = num.nextDouble();
					System.out.print("Real 2: ");
					real2 = num.nextDouble();
					System.out.print("Complejo 2: ");
					comp2 = num.nextDouble();
					suma(real1, comp1, real2, comp2);
					break;
				case 2:
					System.out.println("Ingresa 2 numeros complejos: ");
					System.out.print("Real 1: ");
					real1 = num.nextDouble();
					System.out.print("Complejo 1: ");
					comp1 = num.nextDouble();
					System.out.print("Real 2: ");
					real2 = num.nextDouble();
					System.out.print("Complejo 2: ");
					comp2 = num.nextDouble();
					resta(real1, comp1, real2, comp2);
					break;
				case 3:
					System.out.println("Ingresa 2 numeros complejos: ");
					System.out.print("Real 1: ");
					real1 = num.nextDouble();
					System.out.print("Complejo 1: ");
					comp1 = num.nextDouble();
					System.out.print("Real 2: ");
					real2 = num.nextDouble();
					System.out.print("Complejo 2: ");
					comp2 = num.nextDouble();
					multiplicacion(real1, comp1, real2, comp2);
					break;
				case 4:
					System.out.println("Ingresa 2 numeros complejos: ");
					System.out.print("Real 1: ");
					real1 = num.nextDouble();
					System.out.print("Complejo 1: ");
					comp1 = num.nextDouble();
					System.out.print("Real 2: ");
					real2 = num.nextDouble();
					System.out.print("Complejo 2: ");
					comp2 = num.nextDouble();
					division(real1, comp1, real2, comp2);
					break;
				case 5:
					System.out.println("Ingresa 1 numero complejo: ");
					System.out.print("Real: ");
					real1 = num.nextDouble();
					System.out.print("Complejo: ");
					comp1 = num.nextDouble();
					trigonometrica(real1,comp1);
					break;
				case 6:
					System.out.println("Ingresa 1 numero complejo: ");
					System.out.print("Real: ");
					real1 = num.nextDouble();
					System.out.print("Complejo: ");
					comp1 = num.nextDouble();
					exponencial(real1,comp1);
					break;
				case 7:
					System.out.println("Hasta luego");
					break;
				default:
					System.out.println("No es una opcion valida\n\n");
			}
			
		}while(opcion!=7);
	}

	/**
	* Metodo que suma los dos numeros complejos.
	* @param r1 Numero real 1
	* @param c1 Numero imaginario 1
	* @param r2 Numero real 2
	* @param c2 Numero imaginario 2
	*
	*/
	public static void suma(double r1, double c1, double r2, double c2){
		double res_r;
		double res_c;
		res_r = r1 + r2;
		res_c = c1 + c2;

		System.out.println("El resultado de la suma de complejos es: "+res_r+"+("+res_c+"i)\n\n");
	}	

	/**
	* Metodo que resta los dos numeros complejos.
	* @param r1 Numero real 1
	* @param c1 Numero imaginario 1
	* @param r2 Numero real 2
	* @param c2 Numero imaginario 2
	*
	*/
	public static void resta(double r1, double c1, double r2, double c2){
		double res_r;
		double res_c;
		res_r = r1 - r2;
		res_c = c1 - c2;

		System.out.println("El resultado de la resta de complejos es: "+res_r+"+("+res_c+"i)\n\n");
	}


	/**
	* Metodo que multiplica los dos numeros complejos.
	* @param r1 Numero real 1
	* @param c1 Numero imaginario 1
	* @param r2 Numero real 2
	* @param c2 Numero imaginario 2
	*
	*/
	public static void multiplicacion(double r1, double c1, double r2, double c2){
		double res_r;
		double res_c;
		res_r = (r1*r2) + (c1*c2*-1);
		res_c = (r1*c2) + (r2*c1);

		System.out.println("El resultado de la multiplicacion de complejos es: "+res_r+"+("+res_c+"i)\n\n");
	}

	/**
	* Metodo divide los dos numeros complejos.
	* @param r1 Numero real 1
	* @param c1 Numero imaginario 1
	* @param r2 Numero real 2
	* @param c2 Numero imaginario 2
	*
	*/
	public static void division(double r1, double c1, double r2, double c2){
		double res_r;
		double res_c;

		res_r = (r1*r2+c1*c2)/(r2*r2+c2*c2);
		res_c = (c1*r2-r1*c2)/(r2*r2+c2*c2);

		System.out.println("El resultado de la division de complejos es: "+res_r+"+("+res_c+"i)\n\n");
	}	

	/**
	* Metodo que convierte el numero complejo a su forma trigonometrica.
	* @param r1 Numero real 
	* @param c1 Numero imaginario 
	*
	*/
	public static void trigonometrica(double r1, double c1){
		double r;
		double a;

		r = Math.sqrt(r1*r1+c1*c1);
		a = Math.atan(c1/r1);

		System.out.printf("La forma trigonométrica es: %.2fcis(%.2f)\n\n",r,a);
	}

	/**
	* Metodo que convierte el numero complejo a su forma exponencial.
	* @param r1 Numero real 
	* @param c1 Numero imaginario 
	*
	*/
	public static void exponencial(double r1, double c1){
		double r;
		double a;

		r = Math.sqrt(r1*r1+c1*c1);
		a = Math.atan(c1/r1);

		System.out.printf("La forma exponencial es: %.2fe^(%.2f)i\n\n",r,a);
	}
}
