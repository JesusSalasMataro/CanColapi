package casa;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Mochila extends JPanel {
    
    public static int ANCHO_PANEL_MOCHILA = 100;
    public static int ALTO_PANEL_MOCHILA = 185;
    
    public static int ANCHO_ICONO_MOCHILA = 45;
    public static int ALTO_ICONO_MOCHILA = 45;
    
    private Image imgObjetos, imgIconoMochila, imgLlave, imgEscalera, imgHueso;
    private boolean bLlave, bEscalera, bHueso;
    
    public Mochila() {
        super();
        bLlave = false; bEscalera = false; bHueso = false;
        imgObjetos = new ImageIcon(getClass().getResource("./images/cuadricula_objetos3x2.png")).getImage();
        imgIconoMochila = new ImageIcon(getClass().getResource("./images/mochila45x45.png")).getImage();
        
        imgLlave = new ImageIcon(getClass().getResource("./images/llave32x32_icono.png")).getImage();
        imgEscalera = new ImageIcon(getClass().getResource("./images/escalera32x32_icono.png")).getImage();
        imgHueso = new ImageIcon(getClass().getResource("./images/hueso32x32.png")).getImage();
    }
    
    
    public void MeterLlave() { bLlave = true; }
    public void MeterEscalera() { bEscalera = true; }
    public void MeterHueso() { bHueso = true; }
    
    public void SacarLlave() { bLlave = false; }
    public void SacarEscalera() { bEscalera = false; }
    public void SacarHueso() { bHueso = false; }

    protected void paintComponent( Graphics g ) {
        g.drawImage(imgIconoMochila, 0, 0, ANCHO_ICONO_MOCHILA, ALTO_ICONO_MOCHILA, null);
        g.drawImage(imgObjetos, 0, ALTO_ICONO_MOCHILA, 
                ANCHO_PANEL_MOCHILA, ALTO_PANEL_MOCHILA - ALTO_ICONO_MOCHILA, null); 
        
        if( bLlave ) {
            g.drawImage(imgLlave, 10, ALTO_ICONO_MOCHILA + 10, 32, 32, null);
        }
        if( bEscalera ) {
            g.drawImage(imgEscalera, 10, ALTO_ICONO_MOCHILA + 55, 32, 32, null);
        }
        if( bHueso ) {
            g.drawImage(imgHueso, 60, ALTO_ICONO_MOCHILA + 10, 32, 32, null);
        }
    }
    
}