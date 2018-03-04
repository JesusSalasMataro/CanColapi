package casa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;


public class MiBoton extends JButton {
    
    public static final int BOTON_INICIO = 1;
    public static final int BOTON_COGER = 2;
    public static final int BOTON_USAR = 3;
    
    private Font fntFuente;
    private int iAncho, iAlto;
    Color iColorFondo;
    private int iTipoBoton;
    
    public MiBoton( int iAncho, int iAlto, Color iColorFondo, Font f, int iTipoBoton ) {
        super();
        fntFuente = f;
        setPreferredSize(new Dimension(iAncho, iAlto));
        setVisible(false);
        setFont(fntFuente);
        this.iAncho = iAncho;
        this.iAlto = iAlto;
        this.iColorFondo = iColorFondo;
        this.iTipoBoton = iTipoBoton;
    }
    
    @Override
    public void paintComponent( Graphics g ) {
        g.setColor(iColorFondo);
        g.fillRect(0, 0, iAncho, iAlto);       
        if( iTipoBoton == BOTON_INICIO ) {
            g.setColor(Color.WHITE);
            g.drawString("Pulsa INTRO para jugar", 30, 35);
        }
        else if( iTipoBoton == BOTON_COGER ) {
            g.setColor(Color.BLACK);
            g.drawString("Coger", 17, 30);
        }
        else if( iTipoBoton == BOTON_USAR ) {
            g.setColor(Color.BLACK);
            g.drawString("Usar", 23, 30);
        }
    }
    
}
