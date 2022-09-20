package uniandes.cupi2.cupiDamas.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.cupiDamas.mundo.Casillas;
import uniandes.cupi2.cupiDamas.mundo.Tablero;

public class PanelTablero extends JPanel implements ActionListener
{

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Ventana principal de la aplicaci�n.
	 */
	private InterfazDamas principal;

	// -----------------------------------------------------------------
	// Atributos de interfaz
	// -----------------------------------------------------------------

	/**
	 * Panel del tablero.
	 */
	private JPanel panel;

	/**
	 * Fila uno.
	 */
	private int j1;

	/**
	 * Columna 1.
	 */
	private int i1;

	/**
	 * Matriz para las casillas.
	 */
	private Casillas[][] botones;

	/**
	 * Matriz de botones.
	 */
	private JButton[][] botones1;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructor del panel.
	 */
	public PanelTablero()
	{
		setBorder(new TitledBorder(""));
		setLayout( new BorderLayout());
		setBackground(Color.WHITE);
	}

	/**
	 *  Actualizar el panel.
	 *  <b> post: <b> Se actualiz� el panel. <br>
	 * @param pPrincipal Ventana principal. pPrincipal != null.
	 */
	public void actualizarPanel(InterfazDamas pPrincipal)
	{
		principal = pPrincipal;
		botones = principal.casilla();
		int y = principal.casilla().length;
		botones1 = new JButton[y][y];
		i1=-1;
		j1=-2;

		setBorder(new TitledBorder(""));
		setLayout( new BorderLayout());
		setBackground(Color.WHITE);

		removeAll();

		// Carga la imagen
		ImageIcon iconoCentro = new ImageIcon( "./data/imagenes/Derecha.png" );
		JLabel imagenCentro = new JLabel( iconoCentro );
		imagenCentro.setIcon(iconoCentro);
		add( imagenCentro, BorderLayout.EAST );

		// Carga la imagen
		ImageIcon iconoCentro2 = new ImageIcon( "./data/imagenes/Izquierda.png" );
		JLabel imagenCentro2 = new JLabel( iconoCentro2 );
		imagenCentro.setIcon(iconoCentro2);
		add( imagenCentro2, BorderLayout.WEST );

		// Panel del tablero
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(botones.length,botones.length));
		// Carga el tablero
		for (int i = 0; i < botones.length; i++) 
		{
			for (int j = 0; j < botones[i].length; j++) 
			{
				botones1[i][j] = new JButton("");
				botones1[i][j].setActionCommand(i + "-" + j);
				botones1[i][j].addActionListener(this);
				botones1[i][j].setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				if(principal.casilla()[i][j].darTipo() == principal.casilla()[i][j].CASILLA_NEGRA)
				{
					ImageIcon negra = new ImageIcon(botones[i][j].imagen());
					botones1[i][j].setIcon(negra);
				}
				else if(principal.casilla()[i][j].darTipo() == principal.casilla()[i][j].CASILLA_BLANCA)
				{
					ImageIcon blanca = new ImageIcon(botones[i][j].imagen());
					botones1[i][j].setIcon(blanca);
				}
				else if(principal.casilla()[i][j].darTipo() == principal.casilla()[i][j].FICHA_BLANCA)
				{
					ImageIcon fb = new ImageIcon(botones[i][j].imagen());
					botones1[i][j].setIcon(fb);
				}
				else if(principal.casilla()[i][j].darTipo() == principal.casilla()[i][j].FICHA_ROJA)
				{
					ImageIcon fr = new ImageIcon(botones[i][j].imagen());
					botones1[i][j].setIcon(fr);
				}
				panel.add(botones1[i][j]);
			}
		}
		add(panel, BorderLayout.CENTER);
	}

	/**
	 * Actualiza la imagen de las casillas.
	 * <b> post: <b> Se actualizaron las imagenes de las casillas. <br>
	 */
	public void actualizarFichas()
	{
		Casillas[][] casillasMundo = principal.casilla();

		for (int i = 0; i < casillasMundo.length; i++) 
		{
			for (int j = 0; j < casillasMundo[i].length; j++)
			{
				Casillas actual = casillasMundo[i][j];

				if(actual.darTipo() == Casillas.CASILLA_BLANCA)
				{
					ImageIcon b = new ImageIcon(botones[i][j].imagen());
					botones1[i][j].setIcon(b);
				}	
				else if(actual.darTipo() == Casillas.CASILLA_NEGRA)
				{
					ImageIcon n = new ImageIcon(botones[i][j].imagen());
					botones1[i][j].setIcon(n);
				}
				else if(actual.darTipo() == Casillas.FICHA_ROJA)
				{
					ImageIcon fr = new ImageIcon(botones[i][j].imagen());
					botones1[i][j].setIcon(fr);
				}
				else if(actual.darTipo() == Casillas.FICHA_BLANCA)
				{
					ImageIcon fb = new ImageIcon(botones[i][j].imagen());
					botones1[i][j].setIcon(fb);
				}
			}
		}	
	}

	/**
	 * Manejo de los eventos de los botones.
	 * @param pEvento Acci�n que gener� el evento. pEvento != null.
	 */
	public void actionPerformed(ActionEvent e) 
	{
		String com = e.getActionCommand();
		String [] pos = com.split("-");
		if (i1 >= 0 && j1 >= 0)
		{
			int i2 = Integer.parseInt(pos[0]);
			int j2 = Integer.parseInt(pos[1]);
			principal.realizarJugada(j1, i1, j2, i2);
			actualizarFichas();
			repaint();
			principal.casilla()[j1][i1].imagen();
			i1 = -1;
			j1 = -2;
		}
		else
		{
			i1 = Integer.parseInt(pos[0]);
			j1 = Integer.parseInt(pos[1]);
		}
	}
}
