package uniandes.cupi2.cupiDamas.mundo;

public class Casillas 
{
	//-----------------------------------------------------
	// Constantes
	//-----------------------------------------------------
	
	/**
	 * Constante que determina que la casilla es negra vacia
	 */
	public final static int CASILLA_NEGRA = 1;
	
	/**
	 * Constante que determina que la casilla es blanca
	 */
	public final static int CASILLA_BLANCA = 2;
	
	/**
	 * Constante que determina que la casilla posee una ficha roja
	 */
	public final static int FICHA_ROJA = 3;
	
	/**
	 * Constante que determina que la casilla posee una ficha blanca
	 */
	public final static int FICHA_BLANCA = 4;
	
	//-----------------------------------------------------
	// Atributos
	//-----------------------------------------------------

	/**
	 * Tipo de la casilla
	 */
	private int tipo;
	
	//-----------------------------------------------------
	// Constructor
	//-----------------------------------------------------
	
	/**
	 * Constructor de la clase Casillas <br>
	 * <b> post: <b> Se inicializó el atributo tipo con el valor recibido por paramentro<br>
	 * @param pTipo Tipo de la casilla. pTipo < 5 && pTipo > 0
	 * @throws Exception
	 */
	public Casillas(int pTipo)
	{
		 tipo = pTipo;
	}
	
	//-----------------------------------------------------
	// Métodos
	//-----------------------------------------------------
	
	/**
	 * Retorna el tipo de la casilla
	 * @return tipo de la casilla
	 */
	public int darTipo()
	{
		return tipo;
	}
	
	/**
	 * Cambia el tipo de la casilla
	 * @param pTipo El nuevo de tipo para la casilla
	 */
	public void cambiarTipo(int pTipo)
	{
		tipo = pTipo;
	}
	
	/**
	 * Indica si la casilla está libre
	 * @return True si la casilla está libre, false si no lo está.
	 */
	public boolean estaLibre()
	{
		if(tipo == CASILLA_NEGRA)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	/**
	 * Retorna si existe o no la casilla.
	 * @return True si existe la casilla. False si no existe la casilla.
	 */
	public boolean existe()
	{
		if(tipo < 5 && tipo > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Retorna la imagen que se le otorga a la casilla según su tipo
	 * @return La imagen de la casilla
	 */
	public String imagen()
	{
		String x = "";
		
		if(tipo == CASILLA_BLANCA)
		{
			x = "./data/imagenes/blanco.png";
		}
		else if(tipo == CASILLA_NEGRA)
		{
			x = "./data/imagenes/negro.png";
		}
		else if(tipo == FICHA_BLANCA)
		{
			x = "./data/imagenes/fichaBlanco.png";
		}
		else if(tipo == FICHA_ROJA)
		{
			x = "./data/imagenes/rojo.png";
		}
		return x;
	}
}
