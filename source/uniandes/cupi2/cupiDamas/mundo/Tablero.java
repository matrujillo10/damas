package uniandes.cupi2.cupiDamas.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Tablero
{
	//---------------------------------------------------------------
	// Constantes
	//---------------------------------------------------------------

	/**
	 * Constante que determina el turno de las fichas blancas.
	 */
	public final static int TURNO_BLANCAS = 0;

	/**
	 * Constante que determina el turno de las fichas rojas.
	 */
	public final static int TURNO_ROJAS = 1;

	//---------------------------------------------------------------
	// Atributos
	//---------------------------------------------------------------

	/**
	 * Casillas del tablero.
	 */
	private Casillas[][] casilla;

	/**
	 * Casillas iniciales del tablero.
	 */
	private Casillas[][] casillaInicial;

	/**
	 * Turno de las fichas
	 */
	private int turno;

	//---------------------------------------------------------------
	// Constructor
	//---------------------------------------------------------------

	/**
	 * Constructor de la clase Tablero.
	 * <b> post: <b> Se inicializó el atributo turno y la el tablero <br>
	 * de juego con los valores dados por paramentro. <br>
	 * @param pTurno El turno con el que se inicia el juego. pTurno >= 0 && pTurno <= 1
	 * @param ruta
	 * @throws Exception Si no se carga adecuadamente el archivo.
	 */
	public Tablero(int pTurno, File ruta)throws Exception
	{
		turno = pTurno;
		cargarJuego(ruta);
	}

	/**
	 * Consturctor de la clase Tablero.
	 */
	public Tablero()
	{}

	//---------------------------------------------------------------
	// Metodos
	//---------------------------------------------------------------

	/**
	 * Retorna la matriz de casillas.
	 * @return matriz de casillas.
	 */
	public Casillas[][] casilla()
	{
		return casilla;
	}

	/**
	 * Retorna la matriz de casillas iniciales.
	 * @return matriz de casillas iniciales.
	 */
	public Casillas[][] casillaInicial()
	{
		return casillaInicial;
	}

	/**
	 * Carga el tablero de juego.
	 * <b> post: <b> Cargó el juego.
	 * @param ruta
	 */
	public void cargarJuego(File ruta)throws Exception
	{
		FileInputStream x = new FileInputStream(ruta);
		Properties datos = new Properties();
		datos.load(x);
		String j = datos.getProperty("tablero.tamanho");
		int q = Integer.parseInt(j);
		casilla = new Casillas[q][q];
		casillaInicial = new Casillas[q][q];
		for (int i = 0; i < casilla.length; i++)
		{
			for (int k = 0; k < casilla[i].length; k++)
			{
				String h = datos.getProperty("casilla." + i + "." + k);
				int f = Integer.parseInt(h);
				casilla[i][k] = new Casillas(f);
				casillaInicial[i][k] = new Casillas(f);
			}
		}
	}

	/**
	 * Retorna si hay una ficha blanca en el tablero o más.
	 * @return True si solo hay una ficha blanca en el tablero. False si hay más de una.
	 */
	public boolean ultimaFichaBlanca()
	{
		int contador = 0;
		for(int i = 0; i < casilla.length; i++)
		{
			for (int j = 0; j < casilla[i].length; j++) 
			{
				if(casilla[i][j].darTipo() == Casillas.FICHA_BLANCA)
				{
					contador ++;
				}
			}
		}
		if(contador <= 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Retorna si hay una ficha roja en el tablero o más.
	 * @return True si solo hay una ficha roja en el tablero. False si hay más de una.
	 */
	public boolean ultimaFichaRoja()
	{
		int contador = 0;
		for (int i = 0; i < casilla.length; i++)
		{
			for (int j = 0; j < casilla[i].length; j++) 
			{
				if(casilla[i][j].darTipo() == Casillas.FICHA_ROJA)
				{
					contador ++;
				}
			}
		}
		if(contador <= 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Realizar una jugada.
	 * <b> post: <b> La ficha realizó una jugada y cambió su posición según los valores dados por paramentro. <br>
	 * @param X1 Columna inicial.
	 * @param Y1 Fila inicial.
	 * @param X2 Columna final.
	 * @param Y2 Fila final.
	 * @throws Exception Si se realiza algún movimiento invalido o no es el turno de las fichas.
	 */
	public void realizarJugada(int X1, int Y1, int X2, int Y2) throws Exception
	{
		if(casilla[Y1][X1].darTipo() == Casillas.FICHA_BLANCA)
		{
			if(turno == TURNO_BLANCAS)
			{
				if((Y1 + 1 == Y2) && (X1 + 1 == X2 || X1 - 1 == X2 ) && casilla[Y2][X2].estaLibre())
				{
					casilla[Y1][X1].cambiarTipo(Casillas.CASILLA_NEGRA);
					casilla[Y2][X2].cambiarTipo(Casillas.FICHA_BLANCA);
					turno = TURNO_ROJAS;
				}
				else if((Y1 + 2 == Y2) && X1 - 2 == X2 && casilla[Y2][X2].estaLibre())
				{
					if(!casilla[Y1+1][X1-1].estaLibre() && (X1 - 2 == X2) && casilla[Y1+1][X1-1].darTipo() == Casillas.FICHA_ROJA)
					{
						if(ultimaFichaRoja())
						{
							throw new Exception("Fin del juego.\nGanaron las blancas.");
						}
						else
						{
							casilla[Y1][X1].cambiarTipo(Casillas.CASILLA_NEGRA);
							casilla[Y1+1][X1-1].cambiarTipo(Casillas.CASILLA_NEGRA);
							casilla[Y2][X2].cambiarTipo(Casillas.FICHA_BLANCA);
							turno = TURNO_ROJAS;
						}
					}
					else
					{
						throw new Exception("El movimiento es inválido.");
					}
				}
				else if((Y1 + 2 == Y2) && X1 + 2 == X2 && casilla[Y2][X2].estaLibre())
				{
					if(!casilla[Y1+1][X1+1].estaLibre() && (X1 + 2 == X2) && casilla[Y1+1][X1+1].darTipo() == Casillas.FICHA_ROJA)
					{
						if(ultimaFichaRoja())
						{
							throw new Exception("Fin del juego.\nGanaron las blancas.");
						}
						else
						{
							casilla[Y1][X1].cambiarTipo(Casillas.CASILLA_NEGRA);
							casilla[Y1+1][X1+1].cambiarTipo(Casillas.CASILLA_NEGRA);
							casilla[Y2][X2].cambiarTipo(Casillas.FICHA_BLANCA);
							turno = TURNO_ROJAS;
						}
					}
					else
					{
						throw new Exception("El movimiento es inválido.");
					}
				}
				else
				{
					throw new Exception("El movimiento es inválido.");
				}
			}
			else
			{
				throw new Exception("Es el turno de las rojas.");
			}
		}
		else if(casilla[Y1][X1].darTipo() == Casillas.FICHA_ROJA)
		{
			if(turno == TURNO_ROJAS)
			{	
				if((Y1 - 1 == Y2) &&( X1 + 1 == X2 || X1 - 1 == X2) && casilla[Y2][X2].estaLibre())
				{
					casilla[Y1][X1].cambiarTipo(Casillas.CASILLA_NEGRA);
					casilla[Y2][X2].cambiarTipo(Casillas.FICHA_ROJA);
					turno = TURNO_BLANCAS;
				}
				else if((Y1 - 2 == Y2) && X1 + 2 == X2 && casilla[Y2][X2].estaLibre())
				{
					if(!casilla[Y1-1][X1+1].estaLibre() && X1 + 2 == X2 && casilla[Y1-1][X1+1].darTipo() == Casillas.FICHA_BLANCA && casilla[Y1-1][X1+1].existe())
					{
						if(ultimaFichaBlanca())
						{
							throw new Exception("Fin del juego.\nGanaron las rojas.");
						}
						else
						{
							casilla[Y1][X1].cambiarTipo(Casillas.CASILLA_NEGRA);
							casilla[Y1-1][X1+1].cambiarTipo(Casillas.CASILLA_NEGRA);
							casilla[Y2][X2].cambiarTipo(Casillas.FICHA_ROJA);
							turno = TURNO_BLANCAS;
						}
					}	
					else
					{
						throw new Exception("El movimiento es inválido.");
					}
				}
				else if((Y1 - 2 == Y2) && X1 - 2 == X2 && casilla[Y2][X2].estaLibre())
				{
					if(!casilla[Y1-1][X1-1].estaLibre()&& (X1 - 2 == X2)  && casilla[Y1-1][X1-1].darTipo() == Casillas.FICHA_BLANCA &&  casilla[Y2][X2].estaLibre())
					{
						if(ultimaFichaBlanca())
						{
							throw new Exception("Fin del juego.\nGanaron las rojas.");
						}
						else
						{
							casilla[Y1][X1].cambiarTipo(Casillas.CASILLA_NEGRA);
							casilla[Y1-1][X1-1].cambiarTipo(Casillas.CASILLA_NEGRA);
							casilla[Y2][X2].cambiarTipo(Casillas.FICHA_ROJA);
							turno = TURNO_BLANCAS;
						}
					}
					else
					{
						throw new Exception("El movimiento es inválido.");
					}
				}
				else
				{
					throw new Exception("El movimiento es inválido.");
				}
			}
			else
			{
				throw new Exception("Es el turno de las blancas.");
			}
		}
		else
		{
			throw new Exception("Seleccione una ficha.");
		}
	}


	/**
	 * Retorna si el juego está cargado o no.
	 * @return True si el juego está cargado. False si el juego no está cargado.
	 */
	public boolean cargado()
	{
		boolean rta = false;
		if(!casillaInicial.equals(casilla))
		{
			rta = true;
		}
		else
		{
			rta = false;
		}
		return rta;
	}

	/**
	 * Reinicia el juego.
	 * <b> post: <b> Se reinició el juego. Las fichas vuelen a su posición inicial. <br>
	 * @throws Exception
	 */
	public void reiniciar()throws Exception
	{
		if(cargado() == false)
		{
			throw new Exception("No hay ningún juego cargado");
		}
		else
		{
			turno = TURNO_BLANCAS;
			for (int i = 0; i < casilla.length; i++)
			{
				for (int j = 0; j < casilla[i].length; j++) 
				{
					casilla[i][j].cambiarTipo(casillaInicial[i][j].darTipo());
				}

			}
		}
	}

	//---------------------------------------------------------------
	// Puntos de extensión
	//---------------------------------------------------------------

	/**
	 * Método para la extensión 1.
	 * @return respuesta1.
	 */
	public String metodo1() 
	{
		return "Respuesta 1.";
	}

	/**
	 * Método para la extensión 2.
	 * @return respuesta2.
	 */
	public String metodo2()
	{
		return "Respuesta 2.";
	}
}