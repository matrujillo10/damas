package uniandes.cupi2.cupiDamas.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelExtension extends JPanel implements ActionListener
{
	// -----------------------------------------------------------------
		// Constantes
		// -----------------------------------------------------------------

		/**
		 * Comando Opci�n 1.
		 */
		public static final String OPCION_1 = "OPCION_1";

		/**
		 * Comando Opci�n 2.
		 */
		public static final String OPCION_2 = "OPCION_2";

		/**
		 * Comando reiniciar juego.
		 */
		public static final String REINICIAR = "REINICIAR";

		/**
		 * Comando cargar juego.
		 */
		public static final String CARGAR = "CARGAR_JUEGO";

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
		 * Bot�n Opci�n 1.
		 */
		private JButton btnOpcion1;

		/**
		 * Bot�n Opci�n 2.
		 */
		private JButton btnOpcion2;

		/**
		 * Bot�n cargar juego.
		 */
		private JButton btnCargar;

		/**
		 * Bot�n reiniciar juego.
		 */
		private JButton btnReiniciar;

		// -----------------------------------------------------------------
		// Constructores
		// -----------------------------------------------------------------

		/**
		 * Constructor del panel.
		 * @param pPrincipal Ventana principal. pPrincipal != null.
		 */
		public PanelExtension( InterfazDamas pPrincipal )
		{

			principal = pPrincipal;

			setBorder(new TitledBorder("Opciones"));
			setLayout( new GridLayout( 1, 4));
			
			// Bot�n reiniciar juego
			btnReiniciar = new JButton( "Reiniciar" );
			btnReiniciar.setActionCommand( REINICIAR );
			btnReiniciar.addActionListener( this );
			add( btnReiniciar );
			
			// Bot�n cargar juego
			btnCargar = new JButton( "Cargar" );
			btnCargar.setActionCommand( CARGAR );
			btnCargar.addActionListener( this );
			add( btnCargar );
			
			// Bot�n opci�n 1
			btnOpcion1 = new JButton( "Opci�n 1" );
			btnOpcion1.setActionCommand( OPCION_1 );
			btnOpcion1.addActionListener( this );
			add( btnOpcion1 );

			// Bot�n opci�n 2
			btnOpcion2 = new JButton( "Opci�n 2" );
			btnOpcion2.setActionCommand( OPCION_2 );
			btnOpcion2.addActionListener( this );
			add( btnOpcion2 );

		}

		// -----------------------------------------------------------------
		// M�todos
		// -----------------------------------------------------------------

		/**
		 * Manejo de los eventos de los botones.
		 * @param pEvento Acci�n que gener� el evento. pEvento != null.
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getActionCommand().equals(CARGAR) )
			{
				principal.cargarJuego();
			}
			if(e.getActionCommand().equals(REINICIAR))
			{
				principal.reiniciar();
			}
			if(e.getActionCommand().equals(OPCION_1))
			{
				principal.reqFuncOpcion1();
			}
			if(e.getActionCommand().equals(OPCION_2))
			{
				principal.reqFuncOpcion2();
			}
		}
}
