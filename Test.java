
/**
*	Clase que contiene el metodo principal para el programa Pokemon.
*	@author Vivas Maldonado Rodrigo
*	@version 1.0.0
*	@since 2017-03-19
*/
public class Test{

	/**
	*	Metodo principal.
	*	@param args No utilizado.
	*/
	public static void main(String[] args){
		
		boolean turno_p = true;
		boolean turno_t = false;

		Pikachu p1 = new Pikachu("Pikachu", 150, "Trueno", 30);
		Totodile p2 = new Totodile("Totodile", 150, "HidroBomba", 30);
		

		System.out.println("¡Iniciando combate!");
		
		do{
			System.out.println("----------------------");
			System.out.println("Vida de "+p1.getNombre()+": "+p1.getVida());
			System.out.println("Vida de "+p2.getNombre()+": "+p2.getVida());
			System.out.println("----------------------\n");

			if(turno_p){
				p1.ataque(p2);
				turno_p = false;
			}else{
				p2.ataque(p1);
				turno_p = true;
			}

		}while(p1.getVida() > 0 && p2.getVida() > 0);

		if(p1.getVida() == 0)
			System.out.println("\n\nTOTODILE HA GANADO!");
		else
			System.out.println("\n\nPIKACHU HA GANADO!");
	}
}