

/**
*	Clase que permite la interaccion de operandos con operadores para formar una calculadora basica.
*	@author Vivas Maldonado Rodrigo
*	@version 1.0.0
*	@since 2017-03-19
*/
public class Calarreglos{

	/**
	*	Metodo principal.
	*	@param args Utilizado para obtener un operador al inicio y n operandos.
	*/
	public static void main(String[] args)
	{

		double res = 0;
		if(args.length >= 1){
			double a[] = new double[args.length-1];

			for(int i = 1; i < args.length; i++){

				a[i-1] = Double.parseDouble(args[i]);
				System.out.println("Numero ["+i+"]: "+a[i-1]);
			}

			switch(args[0]){
				//Se hace la suma
				case "+":
					for(int i = 0; i<a.length; i++)
						res += a[i];
					System.out.println("El resultado de la suma es: "+res);
					break;
				//Se hace la resta
				case "-":
					for(int i = 0; i<a.length; i++)
						res -= a[i];
					System.out.println("El resultado de la resta es: "+res);
					break;
				//Se hace la multiplicación
				case "x":
					res = 1;
					for(int i = 0; i<a.length; i++)
						res *= a[i];
					System.out.println("El resultado de la multiplicación es: "+res);
					break;
				//Se hace la división
				case "/":
					for(int i = 0; i<a.length; i++){
						if(i < (a.length-1)) 
							res = a[i] + res;
						else
							res = res/a[i];
					}
					System.out.println("El resultado de la división es: "+res);
					break;
				default:
					System.out.println("El operador ingresado no es valido.");

			}
		}
	}
}