package cancolapi;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class PanelBotones extends JPanel implements ActionListener {
    
    public static int ANCHO_PANEL_BOTONES = 100;
    public static int ALTO_PANEL_BOTONES = 100;
    
    private MiBoton btnCoger, btnUsar;
    private Font fntFuente;
    
    private boolean bCogerActivo, bUsarActivo;
    
    public PanelBotones() {
        super();
        fntFuente = new Font("SANS_SERIF", Font.BOLD, 14);
        
        btnCoger = new MiBoton(80, 50, Color.YELLOW, fntFuente, MiBoton.BOTON_COGER);
        btnCoger.setText("Coger");
        btnCoger.setVisible(false);
        btnCoger.addActionListener(this);
        
        btnUsar = new MiBoton(80, 50, Color.YELLOW, fntFuente, MiBoton.BOTON_USAR);
        btnUsar.setText("Usar");
        btnUsar.setVisible(false);
        btnUsar.addActionListener(this);

        setBotonCogerActivo(false);
        setBotonUsarActivo(false);
        
        this.add(btnCoger);
        this.add(btnUsar);
    }
    
    public MiBoton getBotonCoger() { return btnCoger; }
    public MiBoton getBotonUsar() { return btnUsar; }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.black);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        g2d.setColor(Color.black);
        g2d.setColor(Color.WHITE);
    }

    @Override
    public void actionPerformed( ActionEvent e ) {
        if( e.getSource() == btnCoger ) {
            if( CanColapi.getCasa().getHabActiva().getNumHab() == CanColapi.getCasa().getHueso().getNumHab() ) {
                CanColapi.getCasa().getHueso().Poseer(true);
                CanColapi.getMochila().MeterHueso();
            } 
            
            else if( CanColapi.getCasa().getHabActiva().getNumHab() == CanColapi.getCasa().getLlave().getNumHab() ) {
                CanColapi.getCasa().getLlave().Poseer(true);
                CanColapi.getMochila().MeterLlave();
                CanColapi.getCasa().getLlave().Recogida();
                CanColapi.getCasa().getEscalera().CambiarEstado(Objeto.USADO);
                CanColapi.getCasa().getEscalera().Poseer(false);
                CanColapi.getMochila().SacarEscalera();
                CanColapi.getPanelMensajes().LimpiarTexto();
                CanColapi.getPanelMensajes().AnadirTexto("¡Una llave! Espero que sea de la puerta principal...");  
            }                
            else if( CanColapi.getCasa().getHabActiva().getNumHab() == CanColapi.getCasa().getEscalera().getNumHab() ) {
                CanColapi.getCasa().getEscalera().Poseer(true);
                CanColapi.getMochila().MeterEscalera();
            }
            setBotonCogerActivo(false);
        }
        else if( e.getSource() == btnUsar ) {
            // Habitación del perro (habitación 8), se usa el hueso
            if( CanColapi.getCasa().getHabActiva().getNumHab() == 8 ) {
                CanColapi.getCasa().getPerro().CambiarEstado(Perro.TRANQUILO);
                CanColapi.getCasa().getHueso().CambiarEstado(Objeto.USADO);
                CanColapi.getMochila().SacarHueso();
                CanColapi.getCasa().getPerro().CambiarImagen();
                btnCoger.setVisible(true);
                CanColapi.getPanelMensajes().LimpiarTexto();
                CanColapi.getPanelMensajes().AnadirTexto("'La bestia se abalanza sobre el misterioso hueso destrozándolo en");
                CanColapi.getPanelMensajes().AnadirTexto("cuestión de segundos. De forma increíble se transforma en un dócil");
                CanColapi.getPanelMensajes().AnadirTexto("cachorro que se queda dormido plácidamente'.");
                CanColapi.getPanelMensajes().AnadirTexto("Pensaba que no lo contaba...");
            }
            setBotonUsarActivo(false);
            setBotonCogerActivo(true);
        }
        
        CanColapi.getCanColapi().getLienzoJuego().requestFocus();
    }
    
    public void setBotonCogerActivo( boolean bEstado ) { 
        bCogerActivo = bEstado;
        if( bEstado ) btnCoger.setVisible(true);
        else btnCoger.setVisible(false);
    }
    
    public void setBotonUsarActivo( boolean bEstado ) { 
        bUsarActivo = bEstado;
        if( bEstado ) btnUsar.setVisible(true);
        else btnUsar.setVisible(false);
    }
    
    public boolean isCogerActivo() { return bCogerActivo; }
    public boolean isUsarActivo() { return bUsarActivo; }
    
}
