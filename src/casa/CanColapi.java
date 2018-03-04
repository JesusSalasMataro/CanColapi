package casa;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;


public class CanColapi extends JFrame implements KeyListener {
    
    public final static int ANCHO = 1000;
    public final static int ALTO = 700;
    
    private static CanColapi clpCanColapi;
    private static LienzoJuego lnzLienzoJuego;
    private static final Casa casCasa = new Casa();
    private static final PanelMensajes pnlMensajes = new PanelMensajes();
    private static final Mochila pnlMochila = new Mochila();
    private static final PanelBotones pnlBotones = new PanelBotones();
    private PantallaInicio pntInicio;
    private static final Juego jgoJuego = new Juego();
    

    public CanColapi() {
        initComponents();
        Dimension d = new Dimension(ANCHO, ALTO);
        setPreferredSize(d);
        setMaximumSize(d);
        setMinimumSize(d);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pnlMensajes.setMinimumSize(
                new Dimension(PanelMensajes.ANCHO_PANEL_MENSAJES, PanelMensajes.ALTO_PANEL_MENSAJES));
        pnlMensajes.setBounds(50, ALTO - PanelMensajes.ALTO_PANEL_MENSAJES - 50,
                PanelMensajes.ANCHO_PANEL_MENSAJES, PanelMensajes.ALTO_PANEL_MENSAJES);
        pnlMensajes.AnadirTexto("Vaya, este lugar es realmente tétrico.");
        pnlMensajes.AnadirTexto("En fin, voy a buscar la salida, por fin podré ir al baile de fin de curso con");
        pnlMensajes.AnadirTexto("Joana. Voy a ser la envidia de todo el instituto jajaja.");
        pnlMensajes.setVisible(true);        
        pnlMensajes.setMinimumSize(
                new Dimension(Mochila.ANCHO_PANEL_MOCHILA, Mochila.ALTO_PANEL_MOCHILA));
        
        pnlMochila.setBounds(ANCHO - Mochila.ANCHO_PANEL_MOCHILA - 50, 50,
                Mochila.ANCHO_PANEL_MOCHILA, Mochila.ALTO_PANEL_MOCHILA);
        pnlMochila.setVisible(true);
        
        pnlBotones.setLayout(new FlowLayout());
        pnlBotones.setMinimumSize(
            new Dimension(PanelBotones.ANCHO_PANEL_BOTONES, PanelBotones.ALTO_PANEL_BOTONES));
        pnlBotones.setVisible(true);
        pnlBotones.setBounds(ANCHO - Mochila.ANCHO_PANEL_MOCHILA - 50, 
                Mochila.ALTO_PANEL_MOCHILA + 100, 
                PanelBotones.ANCHO_PANEL_BOTONES, PanelBotones.ALTO_PANEL_BOTONES);

        lnzLienzoJuego = new LienzoJuego(600, 400, casCasa);       
        lnzLienzoJuego.setLayout(null);
        lnzLienzoJuego.add(pnlMensajes);
        lnzLienzoJuego.add(pnlMochila);
        lnzLienzoJuego.add(pnlBotones);
        
        lnzLienzoJuego.setFocusable(true);
        lnzLienzoJuego.addKeyListener(lnzLienzoJuego);               
    }

    public static Casa getCasa() { return casCasa; }
    public static PanelMensajes getPanelMensajes() { return pnlMensajes; }
    public static Mochila getMochila() { return pnlMochila; }
    public static PanelBotones getPanelBotones() { return pnlBotones; }
    public LienzoJuego getLienzoJuego() { return lnzLienzoJuego; }
    public static CanColapi getCanColapi() { return clpCanColapi; }
    public static Juego getJuego() { return jgoJuego; }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 400));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 939, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 626, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                clpCanColapi = new CanColapi();
                clpCanColapi.addKeyListener(clpCanColapi);
                jgoJuego.setCanColapi(clpCanColapi, lnzLienzoJuego);
                jgoJuego.start();            
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables


    @Override
    public void keyPressed( KeyEvent ke ) {
        if( ke.getKeyCode() == KeyEvent.VK_ENTER ) {
            if( jgoJuego.getPantallaInicio().AnimacionFinalizada() ) {
                jgoJuego.getPantallaInicio().EmpezarJuego();
                clpCanColapi.removeKeyListener(clpCanColapi);
                transferFocus();
            }
        }
    }

    @Override
    public void keyTyped( KeyEvent ke ) { }
    
    @Override
    public void keyReleased( KeyEvent ke ) { }

}
