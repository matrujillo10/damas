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
		 * Comando Opción 1.
		 */
		public static final String OPCION_1 = "OPCION_1";

		/**
		 * Comando Opción 2.
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
		 * Ventana principal de la aplicación.
		 */
		private InterfazDamas principal;

		// -----------------------------------------------------------------
		// Atributos de interfaz
		// -----------------------------------------------------------------

		/**
		 * Botón Opción 1.
		 */
		private JButton btnOpcion1;

		/**
		 * Botón Opción 2.
		 */
		private JButton btnOpcion2;

		/**
		 * Botón cargar juego.
		 */
		private JButton btnCargar;

		/**
		 * Botón reiniciar juego.
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
			
			// Botón reiniciar juego
			btnReiniciar = new JButton( "Reiniciar" );
			btnReiniciar.setActionCommand( REINICIAR );
			btnReiniciar.addActionListener( this );
			add( btnReiniciar );
			
			// Botón cargar juego
			btnCargar = new JButton( "Cargar" );
			btnCargar.setActionCommand( CARGAR );
			btnCargar.addActionListener( this );
			add( btnCargar );
			
			// Botón opción 1
			btnOpcion1 = new JButton( "Opción 1" );
			btnOpcion1.setActionCommand( OPCION_1 );
			btnOpcion1.addActionListener( this );
			add( btnOpcion1 );

			// Botón opción 2
			btnOpcion2 = new JButton( "Opción 2" );
			btnOpcion2.setActionCommand( OPCION_2 );
			btnOpcion2.addActionListener( this );
			add( btnOpcion2 );

		}

		// -----------------------------------------------------------------
		// Métodos
		// -----------------------------------------------------------------

		/**
		 * Manejo de los eventos de los botones.
		 * @param pEvento Acción que generó el evento. pEvento != null.
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
