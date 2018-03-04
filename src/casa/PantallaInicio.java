package casa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;


public class PantallaInicio extends JPanel implements ActionListener {
    
    private final int ANCHO_BOTON = 300;
    private final int ALTO_BOTON = 50;
    
    private Image imgFondo;
    private Image imgTitulo;
    private int iAnchoTitulo, iAltoTitulo;
    private Timer tim;
    private JButton btnEmpezar;
    
    private boolean bEmpezarJuego;
    private boolean bAnimacionFinalizada;
    
    
    public PantallaInicio( ) {
        imgFondo = new ImageIcon(getClass().getResource("./images/portada_1000x700.png")).getImage();
        imgTitulo = new ImageIcon(getClass().getResource("./images/titulo_juego.png")).getImage();
        bEmpezarJuego = false;
        bAnimacionFinalizada = false;
        
        setPreferredSize(new Dimension(CanColapi.ANCHO, CanColapi.ALTO));
        iAnchoTitulo = 50;
        iAltoTitulo = 50;
        btnEmpezar = new MiBoton(ANCHO_BOTON, ALTO_BOTON, Color.BLACK, 
                new Font("SANS_SERIF", Font.BOLD, 22), MiBoton.BOTON_INICIO);
        btnEmpezar.setText("Empezar");
        btnEmpezar.setBounds(CanColapi.ANCHO/2 - ANCHO_BOTON/2, CanColapi.ALTO - 100, ANCHO_BOTON, ALTO_BOTON);
        btnEmpezar.addActionListener(this);
        this.add(btnEmpezar);
    }
    
    @Override
    public void paintComponent( Graphics g ) {
        g.drawImage(imgFondo, 0, 0, CanColapi.ANCHO, CanColapi.ALTO, null);
        g.drawImage(imgTitulo, CanColapi.ANCHO/2 - iAnchoTitulo/2, CanColapi.ALTO/2 - iAltoTitulo/2,
            iAnchoTitulo, iAltoTitulo, null);
        btnEmpezar.repaint();
    }
    
    public void IniciarAnimacion() {
        tim = new Timer(5, this);
        tim.start();
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == tim ) {
            if( (iAnchoTitulo < 900) && (iAltoTitulo < 630) ) {
                iAnchoTitulo = iAnchoTitulo + 10;
                iAltoTitulo = iAltoTitulo + 7;
                repaint();
            }
            else {
                tim.stop();
                btnEmpezar.setVisible(true);
                bAnimacionFinalizada = true;
            }
        }
        else if( e.getSource() == btnEmpezar ) {
            bEmpezarJuego = true;
        }
    }
    
    public boolean JuegoIniciado() { return bEmpezarJuego; }
    public boolean AnimacionFinalizada() { return bAnimacionFinalizada; }
    
    public void EmpezarJuego() { bEmpezarJuego = true; }

}
