package casa;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Vector;
import javax.swing.JPanel;

public class PanelMensajes extends JPanel {
    
    public static int ANCHO_PANEL_MENSAJES = 600;
    public static int ALTO_PANEL_MENSAJES = 100;
    
    private Vector<String> Texto;
    private Font fntFuente;
    
    public PanelMensajes() {
        super();
        fntFuente = new Font("", Font.BOLD, 16);
        Texto = new Vector<String>();
    }
    
    
    public void AnadirTexto( String sTexto ) {
        Texto.add(sTexto);
    }
    
    
    public void LimpiarTexto() {
        Texto = new Vector<String>();
    }
    
    
    protected void paintComponent(Graphics g) {
        int i;
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, ANCHO_PANEL_MENSAJES, ALTO_PANEL_MENSAJES);
        g2d.setColor(Color.WHITE);
        g2d.setFont(fntFuente);
        for( i=0; i<Texto.size(); i++ ) {
            g2d.drawString(Texto.get(i), 10, 20*(i+1));
        }
        
    }
    
}
