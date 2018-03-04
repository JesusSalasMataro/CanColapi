package casa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class PantallaFinal extends JPanel implements ActionListener {

    private Image imgFondo;
    private int iAnchoImagen, iAltoImagen;
    private Timer tim;   
    
    boolean bAnimacionActiva;
    
    public PantallaFinal( ) {
        bAnimacionActiva = true;
        setFont(new Font("SANS_SERIF", Font.BOLD, 34));
        imgFondo = new ImageIcon(getClass().getResource("./images/foto_final.jpg")).getImage();
        
        setPreferredSize(new Dimension(CanColapi.ANCHO, CanColapi.ALTO));
        iAnchoImagen = 75;
        iAltoImagen = 50;
    }
    
    @Override
    public void paintComponent( Graphics g ) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, CanColapi.ANCHO, CanColapi.ALTO);
        g.drawImage(imgFondo, CanColapi.ANCHO/2 - iAnchoImagen/2, CanColapi.ALTO/2 - iAltoImagen/2,
            iAnchoImagen, iAltoImagen, null);
        if( !bAnimacionActiva ) {            
            g.setColor(Color.WHITE);
            g.drawString("FIN", 470, 70);
        }
    }
    
    public void IniciarAnimacion() {
        tim = new Timer(5, this);
        tim.start();
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == tim ) {
            if( iAnchoImagen < 700 ) {
                iAnchoImagen = iAnchoImagen + 3;
                iAltoImagen = iAltoImagen + 2;
                repaint();
            }
            else {
                tim.stop();
                bAnimacionActiva = false;
                repaint();
            }
        }
    }

}
