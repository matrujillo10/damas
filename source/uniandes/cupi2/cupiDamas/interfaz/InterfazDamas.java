package uniandes.cupi2.cupiDamas.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.cupiDamas.mundo.Casillas;
import uniandes.cupi2.cupiDamas.mundo.Tablero;

public class InterfazDamas extends JFrame
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Ventana principal de la aplicaci�n.
	 */
	private static InterfazDamas principal;

	/**
	 * Clase principal del mundo.
	 */
	private Tablero mundo;

	// -----------------------------------------------------------------
	// Atributos de la interfaz
	// -----------------------------------------------------------------

	/**
	 * Panel con la imagen de encabezado.
	 */
	private PanelImagen panelImagen;

	/**
	 * Panel con las extensiones.
	 */
	private PanelExtension panelExtension;

	/**
	 * Panel con el tablero.
	 */
	private PanelTablero panelTablero;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Constructor de la ventana principal.
	 */
	public InterfazDamas()
	{
		mundo = new Tablero();

		setLayout(new BorderLayout());
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setTitle("Damas");
		setSize(700, 700);
		setResizable(false);

		panelImagen = new PanelImagen();
		panelExtension = new PanelExtension(this);
		panelTablero = new PanelTablero();

		add(panelImagen, BorderLayout.NORTH);
		add(panelTablero, BorderLayout.CENTER);
		add(panelExtension, BorderLayout.SOUTH);
	}

	//-----------------------------------------------------------------
	// M�todos
	//-----------------------------------------------------------------

	/**
	 * Retorna la matriz de casillas.
	 * @return La matriz de casillas.
	 */
	public Casillas[][] casilla()
	{
		return mundo.casilla();
	}

	/**
	 * Retorna la matriz de casillas iniciales.
	 * @return La matriz de casillas iniciales.
	 */
	public Casillas[][] casillaInicial()
	{
		return mundo.casillaInicial();
	}

	/**
	 * Realizar una jugada.
	 * <b> post: <b> Se realiz� una jugada. <br>
	 */
	public void realizarJugada(int X1, int Y1, int X2, int Y2)
	{
		try
		{
			mundo.realizarJugada(X1, Y1, X2, Y2);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Jugada", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Reiniciar el juego.
	 * <b> post: <b> Se reinici� el juego. <br>
	 */
	public void reiniciar()
	{
		try
		{
			mundo.reiniciar();
			panelTablero.actualizarPanel(this);
			panelTablero.actualizarFichas();
			panelTablero.validate();
			JOptionPane.showMessageDialog(this, "El juego ha sido reiniciado.", "Reiniciar", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "No hay ning�n juego cargado.", "Reiniciar juego", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Cargar el tablero de juego
	 * <b> post: <b> Carg� el tablero de juego. <br>
	 */
	public void cargarJuego()
	{
		JFileChooser fc = new JFileChooser("./data");
		fc.setDialogTitle("Elija el tama�o del tablero con el que desea jugar.");
		int resultado = fc.showOpenDialog(this);
		if(resultado == JFileChooser.APPROVE_OPTION)
		{
			File archivo = fc.getSelectedFile();
			try
			{
				mundo = new Tablero(Tablero.TURNO_BLANCAS, archivo);
				panelTablero.actualizarPanel(this);
				panelTablero.validate();
				int y = casilla().length;
				if(y == 8)
				{
					setSize(615, 650);
				}
				else
				{
					setSize(700, 700);
				}
				JOptionPane.showMessageDialog(this, "Las fichas blancas empiezan el juego.", "Turno", JOptionPane.INFORMATION_MESSAGE);
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Error de la lectura del archivo: formato inv�lido.", "Cargar juego", JOptionPane.ERROR_MESSAGE);
		}
	}

	// -----------------------------------------------------------------
	// Puntos de Extensi�n
	// -----------------------------------------------------------------

	/**
	 * M�todo para la extensi�n 1.
	 */
	public void reqFuncOpcion1( )
	{
		String resultado = mundo.metodo1();
		JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
	}

	/**
	 * M�todo para la extensi�n 2.
	 */
	public void reqFuncOpcion2( )
	{
		String resultado = mundo.metodo2();
		JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
	}

	// -----------------------------------------------------------------
	// Main
	// -----------------------------------------------------------------

	/**
	 * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz.
	 * @param args No son necesarios.
	 */
	public static void main(String[] args) 
	{
		principal = new InterfazDamas();
		principal.setVisible( true );
	}
}
