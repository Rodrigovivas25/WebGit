
/**
*	Clase que implementa metodos para imprimir los numeros factoriales de forma recursiva.
*	@author Vivas Maldonado Rodrigo
*	@version 1.0.0
*	@since 2017-03-19
*/

public class Factorial{

	/**
	*	Metodo principal.
	*	@param args No utilizado.
	*/
	public static void main(String[] args) {
		for(int i = 0; i < 5; i++){
			System.out.println("Factorial " + i + ": " + factorial(i));
		}		
	}

	/**
	* Metodo que calcula el factorial de un numero de forma recursiva.
	* @param num Un numero cualquiera para calcular su factorial.
	* @return long El factorial del numero calculado.
	*
	*/
	public static long factorial (int num)
	{

		if (num == 0 || num == 1)
			return 1;
		else
			return num*factorial(num-1);
	}
}