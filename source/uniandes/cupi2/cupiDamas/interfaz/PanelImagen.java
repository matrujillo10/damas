package uniandes.cupi2.cupiDamas.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.media.j3d.Background;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelImagen extends JPanel
{
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor sin par�metros del panel.
     */
	public PanelImagen()
	{
		// Carga la imagen
		ImageIcon iconoCentro = new ImageIcon( "./data/imagenes/Encabezado.png" );
		JLabel imagenCentro = new JLabel( iconoCentro );
		imagenCentro.setIcon(iconoCentro);
		setBackground(Color.WHITE);
		add( imagenCentro, BorderLayout.CENTER );
	}
}

