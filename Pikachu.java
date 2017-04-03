import java.util.Random;

/**
*	Clase que implementa metodos para las acciones del Pokemon Pikachu.
*	@author Vivas Maldonado Rodrigo
*	@version 1.0.0
*	@since 2017-03-19
*/
public class Pikachu{
	String nombre;
	int vida;
	String nom_ataque;
	int ataque;

	/**
	* Constructor de la clase Pikachu.
	* @param nombre El nombre asignado al Pokemon.
	* @param vida La cantidad de vida que tiene el Pokemon.
	* @param nom_ataque El nombre del ataque del Pokemon.
	* @param ataque La cantidad de ataque del Pokemon.
	*
	*/
	public Pikachu(String nombre, int vida, String nom_ataque, int ataque)
	{
		this.nombre = nombre;
		this.vida = vida;
		this.nom_ataque = nom_ataque;
		this.ataque = ataque;
	}

	/**
	* Metodo que devuelve la vida del Pokemon.
	* @return int La vida del Pokemon.
	*
	*/
	public int getVida(){
		return this.vida;
	}

	/**
	* Metodo que asigna la cantidad de vida del Pokemon.
	* @param vida La cantidad de vida que le queda al Pokemon.
	*
	*/
	public void setVida(int vida){
		this.vida = vida;
	}

	/**
	* Metodo que devuelve el nombre del Pokemon.
	* @return String El nombre del Pokemon.
	*
	*/
	public String getNombre(){
		return this.nombre;
	}

	/**
	* Metodo que devuelve el nombre del ataque del Pokemon.
	* @return String El nombre del ataque.
	*
	*/
	public String getNomAtaque(){
		return this.nom_ataque;
	}

	/**
	* Metodo que devuelve la cantidad de ataque del Pokemon.
	* @return int La cantidad de ataque del Pokemon.
	*
	*/
	public int getAtaque(){
		return this.ataque;
	}

	/**
	* Metodo que interactua con un objeto de la clase Totodile.
	* @param t El objeto de la clase Totodile.
	*
	*/
	public void ataque(Totodile t){
		Random r = new Random();
		int prob = r.nextInt(101);
		if(prob>=20){
			System.out.println("Pikachu usó "+getNomAtaque());
			if((t.getVida()-getAtaque())>=0)
				t.setVida(t.getVida()-getAtaque());
			else
				t.setVida(0);
		} else{
			System.out.println("¡Pikachu falló en el ataque!");
		}

	}
}