package cancolapi;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LienzoJuego extends JPanel implements ActionListener, KeyListener {
    
    private final int MARGEN_OBJETO = 100;
    
    private int iTeclaActual;
    private Image imgFondo;
    private Casa casCasa;
    private Habitacion habHabitacion;
    
    public LienzoJuego( int iAncho, int iAlto, Casa casCasa ) {
        super();
        Dimension d = new Dimension(iAncho, iAlto);
        setPreferredSize(d);
        imgFondo = new ImageIcon(getClass().getResource("./images/fondo.jpg")).getImage();
        
        this.casCasa = casCasa;
        habHabitacion = this.casCasa.getHabActiva();
        
        Timer tim = new Timer(5, this);
        tim.start();
    }
    
    @Override
    public void paintComponent( Graphics g ) {       
        DibujarHabitacion(g);
        DibujarPanelMensajes(g);
        DibujarObjetos(g);
        DibujarMuebles(g);
        DibujarCarlos(g);               
    }
    
    
    private void DibujarHabitacion( Graphics g ) {       
        int i, ii;
        
        habHabitacion = casCasa.getHabActiva();
        
        // Imagen de fondo
        g.drawImage(imgFondo, 0, 0, CanColapi.ANCHO, CanColapi.ALTO, null);      
        
        // Esquina superior izquierda
        g.drawImage(habHabitacion.getMuroEsquina(), habHabitacion.getPosX(), habHabitacion.getPosY(),
                Habitacion.GRUESO_MURO, Habitacion.GRUESO_MURO, null);
        
        // Trozo muro pequeño debajo de esquina superior izquierda
        g.drawImage(habHabitacion.getMuroVerticalPeq(), 
                habHabitacion.getPosX(),
                habHabitacion.getPosY() + Habitacion.GRUESO_MURO, 
                Habitacion.GRUESO_MURO, Habitacion.ALTO_MURO_VERTICAL_PEQ,
                null);
        
        // Muro vertical izquierdo
        for( i=0; i<(habHabitacion.getAlto() - 1); i++ ) {
            if( habHabitacion.HayPuerta(i, Puerta.IZQUIERDA) ) {
                g.drawImage(habHabitacion.getPuertaVertical(),
                    habHabitacion.getPosX(), 
                    habHabitacion.getPosY() + i*Habitacion.ALTO_MURO_VERTICAL + Habitacion.ALTO_MURO_HORIZONTAL,
                    Habitacion.GRUESO_MURO, 
                    Habitacion.ALTO_MURO_VERTICAL, null);
            }
            else {
                g.drawImage(habHabitacion.getMuroVertical(),
                    habHabitacion.getPosX(), 
                    habHabitacion.getPosY() + i*Habitacion.ALTO_MURO_VERTICAL + Habitacion.ALTO_MURO_HORIZONTAL,
                    Habitacion.GRUESO_MURO, 
                    Habitacion.ALTO_MURO_VERTICAL, null);                
            }
        }
        
        // Trozo muro pequeño sobre la esquina inferior izquierda
        g.drawImage(habHabitacion.getMuroVerticalPeq(),
                habHabitacion.getPosX(),
                habHabitacion.getPosY() + Habitacion.ALTO_MURO_HORIZONTAL + 
                        Habitacion.ALTO_MURO_VERTICAL * (habHabitacion.getAlto() - 1),
                Habitacion.GRUESO_MURO,
                Habitacion.ALTO_MURO_VERTICAL_PEQ, null);
        
        // Esquina superior derecha
        g.drawImage(habHabitacion.getMuroEsquina(), 
                habHabitacion.getPosX() + Habitacion.GRUESO_MURO + 
                        Habitacion.ANCHO_MURO_HORIZONTAL * habHabitacion.getAncho(), 
                habHabitacion.getPosY(),
                Habitacion.GRUESO_MURO, Habitacion.GRUESO_MURO, null);
        
        // Trozo muro pequeño debajo de esquina superior derecha
        g.drawImage(habHabitacion.getMuroVerticalPeq(), 
                habHabitacion.getPosX() + Habitacion.GRUESO_MURO + 
                        Habitacion.ANCHO_MURO_HORIZONTAL * habHabitacion.getAncho(),
                habHabitacion.getPosY() + Habitacion.GRUESO_MURO, 
                Habitacion.GRUESO_MURO, Habitacion.ALTO_MURO_VERTICAL_PEQ,
                null);
        
        // Muro vertical derecho
        for( i=0; i<(habHabitacion.getAlto() - 1); i++ ) {
            if( habHabitacion.HayPuerta(i, Puerta.DERECHA) ) {
                g.drawImage(habHabitacion.getPuertaVertical(),
                    habHabitacion.getPosX() + Habitacion.GRUESO_MURO + 
                            Habitacion.ANCHO_MURO_HORIZONTAL * habHabitacion.getAncho(), 
                    habHabitacion.getPosY() + i*Habitacion.ALTO_MURO_VERTICAL + Habitacion.ALTO_MURO_HORIZONTAL,
                    Habitacion.GRUESO_MURO, 
                    Habitacion.ALTO_MURO_VERTICAL, null);
            }
            else {
                g.drawImage(habHabitacion.getMuroVertical(),
                    habHabitacion.getPosX() + Habitacion.GRUESO_MURO + 
                            Habitacion.ANCHO_MURO_HORIZONTAL * habHabitacion.getAncho(), 
                    habHabitacion.getPosY() + i*Habitacion.ALTO_MURO_VERTICAL + Habitacion.ALTO_MURO_HORIZONTAL,
                    Habitacion.GRUESO_MURO, 
                    Habitacion.ALTO_MURO_VERTICAL, null);                
            }
        }
        
        // Trozo muro pequeño sobre la esquina inferior derecha
        g.drawImage(habHabitacion.getMuroVerticalPeq(),
                habHabitacion.getPosX() + Habitacion.GRUESO_MURO + 
                        Habitacion.ANCHO_MURO_HORIZONTAL * habHabitacion.getAncho(),
                habHabitacion.getPosY() + Habitacion.ALTO_MURO_HORIZONTAL + 
                        Habitacion.ALTO_MURO_VERTICAL * (habHabitacion.getAlto() - 1),
                Habitacion.GRUESO_MURO,
                Habitacion.ALTO_MURO_VERTICAL_PEQ, null);        
        
        // Esquina inferior derecha
        g.drawImage(habHabitacion.getMuroEsquina(), 
                habHabitacion.getPosX() + Habitacion.GRUESO_MURO + 
                        Habitacion.ANCHO_MURO_HORIZONTAL * habHabitacion.getAncho(), 
                habHabitacion.getPosY() + 
                        (habHabitacion.getAlto()+1)*Habitacion.ALTO_MURO_VERTICAL - Habitacion.GRUESO_MURO*2,
                Habitacion.GRUESO_MURO, Habitacion.GRUESO_MURO, null); 
        
        // Esquina inferior izquierda
        g.drawImage(habHabitacion.getMuroEsquina(), 
                habHabitacion.getPosX(), 
                habHabitacion.getPosY() + 
                        (habHabitacion.getAlto()+1)*Habitacion.ALTO_MURO_VERTICAL - Habitacion.GRUESO_MURO*2,
                Habitacion.GRUESO_MURO, Habitacion.GRUESO_MURO, null);       
               
        // Muro horizontal superior
        for( i=0; i<habHabitacion.getAncho(); i++ ) {
            if( habHabitacion.HayPuerta(i, Puerta.ARRIBA) ) {
                g.drawImage(habHabitacion.getPuertaHorizontal(),
                    habHabitacion.getPosX() + Habitacion.GRUESO_MURO + i*Habitacion.ANCHO_MURO_HORIZONTAL, 
                    habHabitacion.getPosY(), 
                    Habitacion.ANCHO_MURO_HORIZONTAL, 
                    Habitacion.ALTO_MURO_HORIZONTAL, null);                
            }
            else {
                g.drawImage(habHabitacion.getMuroHorizontal(),
                    habHabitacion.getPosX() + Habitacion.GRUESO_MURO + i*Habitacion.ANCHO_MURO_HORIZONTAL, 
                    habHabitacion.getPosY(), 
                    Habitacion.ANCHO_MURO_HORIZONTAL, 
                    Habitacion.ALTO_MURO_HORIZONTAL, null);  
            }
        }

        // Suelo de la habitación
        for( i=0; i<habHabitacion.getAncho(); i++ ) {
            for( ii=0; ii<habHabitacion.getAlto(); ii++ ) {
                g.drawImage(habHabitacion.getSuelo(),
                   i*Habitacion.CASILLA_SUELO + habHabitacion.getPosX() + Habitacion.GRUESO_MURO, 
                   ii*Habitacion.CASILLA_SUELO + habHabitacion.getPosY() + Habitacion.ALTO_MURO_HORIZONTAL, 
                   Habitacion.CASILLA_SUELO, Habitacion.CASILLA_SUELO, null);
            }
        }
                
        // Muro horizontal inferior
        for( i=0; i<habHabitacion.getAncho(); i++ ) {
            if( habHabitacion.HayPuerta(i, Puerta.ABAJO) ) {
                g.drawImage(habHabitacion.getPuertaHorizontalInferior(),
                    habHabitacion.getPosX() + Habitacion.GRUESO_MURO + i*Habitacion.ANCHO_MURO_HORIZONTAL, 
                    habHabitacion.getPosY() + 
                            habHabitacion.getAlto()*Habitacion.ALTO_MURO_VERTICAL + 
                            Habitacion.ALTO_MURO_HORIZONTAL - Habitacion.GRUESO_MURO, 
                    Habitacion.ANCHO_MURO_HORIZONTAL, 
                    Habitacion.GRUESO_MURO, null); 
            }
            else {
                g.drawImage(habHabitacion.getMuroHorizontalInferior(),
                    habHabitacion.getPosX() + Habitacion.GRUESO_MURO + i*Habitacion.ANCHO_MURO_HORIZONTAL, 
                    habHabitacion.getPosY() + 
                            habHabitacion.getAlto()*Habitacion.ALTO_MURO_VERTICAL + 
                            Habitacion.ALTO_MURO_HORIZONTAL - Habitacion.GRUESO_MURO, 
                    Habitacion.ANCHO_MURO_HORIZONTAL, 
                    Habitacion.GRUESO_MURO, null);                 
            }
        } 
        
        // Ventana rota de la habitación de inicio
        if( habHabitacion.getNumHab() == 0 ) {
            g.drawImage(habHabitacion.getVentanaRota(),
                habHabitacion.getPosX() + 25, habHabitacion.getPosY() + 20,
                habHabitacion.ANCHO_VENTANA_ROTA, habHabitacion.ALTO_VENTANA_ROTA, null);
        }
    }
    
    
    private void DibujarCarlos( Graphics g ) {        
        switch( casCasa.getCarlos().getDireccion() ) {
            case Carlos.MOVER_ABAJO:
                g.drawImage(Carlos.imgCarlos_Abajo[casCasa.getCarlos().getPaso()], 
                    casCasa.getCarlos().getPosX() + habHabitacion.getPosX(), 
                    casCasa.getCarlos().getPosY() + habHabitacion.getPosY(), 
                    Carlos.CARLOS_ANCHO, Carlos.CARLOS_ALTO, null);
                break;               
            case Carlos.MOVER_ARRIBA:
                g.drawImage(Carlos.imgCarlos_Arriba[casCasa.getCarlos().getPaso()], 
                    casCasa.getCarlos().getPosX() + habHabitacion.getPosX(), 
                    casCasa.getCarlos().getPosY() + habHabitacion.getPosY(), 
                    Carlos.CARLOS_ANCHO, Carlos.CARLOS_ALTO, null);
                break;               
            case Carlos.MOVER_DERECHA:
                g.drawImage(Carlos.imgCarlos_Derecha[casCasa.getCarlos().getPaso()], 
                    casCasa.getCarlos().getPosX() + habHabitacion.getPosX(), 
                    casCasa.getCarlos().getPosY() + habHabitacion.getPosY(), 
                    Carlos.CARLOS_ANCHO, Carlos.CARLOS_ALTO, null);
                break;               
            case Carlos.MOVER_IZQUIERDA:
                g.drawImage(Carlos.imgCarlos_Izquierda[casCasa.getCarlos().getPaso()], 
                    casCasa.getCarlos().getPosX() + habHabitacion.getPosX(), 
                    casCasa.getCarlos().getPosY() + habHabitacion.getPosY(), 
                    Carlos.CARLOS_ANCHO, Carlos.CARLOS_ALTO, null);
                break;  
            case Carlos.QUIETO_ABAJO:
                g.drawImage(Carlos.imgCarlos_Abajo[0], 
                    casCasa.getCarlos().getPosX() + habHabitacion.getPosX(), 
                    casCasa.getCarlos().getPosY() + habHabitacion.getPosY(), 
                    Carlos.CARLOS_ANCHO, Carlos.CARLOS_ALTO, null);
                break;                  
            case Carlos.QUIETO_ARRIBA:
                g.drawImage(Carlos.imgCarlos_Arriba[0], 
                    casCasa.getCarlos().getPosX() + habHabitacion.getPosX(), 
                    casCasa.getCarlos().getPosY() + habHabitacion.getPosY(), 
                    Carlos.CARLOS_ANCHO, Carlos.CARLOS_ALTO, null);
                break;  
            case Carlos.QUIETO_DERECHA:
                g.drawImage(Carlos.imgCarlos_Derecha[0], 
                    casCasa.getCarlos().getPosX() + habHabitacion.getPosX(), 
                    casCasa.getCarlos().getPosY() + habHabitacion.getPosY(), 
                    Carlos.CARLOS_ANCHO, Carlos.CARLOS_ALTO, null);
                break;  
            case Carlos.QUIETO_IZQUIERDA:
                g.drawImage(Carlos.imgCarlos_Izquierda[0], 
                    casCasa.getCarlos().getPosX() + habHabitacion.getPosX(), 
                    casCasa.getCarlos().getPosY() + habHabitacion.getPosY(), 
                    Carlos.CARLOS_ANCHO, Carlos.CARLOS_ALTO, null);
                break;  
        }
    }
    
    
    private void DibujarObjetos( Graphics g ) {       
        // Compruebo si el hueso se encuentra en la habitación actual, en caso afirmativo
        // lo dibujo si no lo tengo en la mochila todavía
        if( casCasa.getHueso().getNumHab() == habHabitacion.getNumHab() ) {
            if( casCasa.getHueso().getEstado() == Objeto.NOLOTENGO ) {
                g.drawImage(casCasa.getHueso().getImagen(),
                        habHabitacion.getPosXObjeto1() * Habitacion.CASILLA_SUELO + MARGEN_OBJETO,
                        habHabitacion.getPosYObjeto1() * Habitacion.CASILLA_SUELO + MARGEN_OBJETO + 70,
                        casCasa.getHueso().getAncho(), casCasa.getHueso().getAlto(), null);
            }
        }
        
        // Compruebo que la llave se encuentra en la habitación actual, en caso afirmativo
        // la dibujo. Si ya la tengo en la mochila se dibujará el armario sin la llave.
        if( casCasa.getLlave().getNumHab() == habHabitacion.getNumHab() ) {
            g.drawImage(casCasa.getLlave().getImagen(),
                habHabitacion.getPosXObjeto1() * Habitacion.CASILLA_SUELO + MARGEN_OBJETO - 20,
                habHabitacion.getPosYObjeto1() * Habitacion.CASILLA_SUELO + MARGEN_OBJETO - 20,
                casCasa.getLlave().getAncho(), casCasa.getLlave().getAlto(), null);
        }
        
        if( habHabitacion.getNumHab() == 8 ) {
            g.drawImage(casCasa.getPerro().getImagen(),
                habHabitacion.getPosXObjeto1() * Habitacion.CASILLA_SUELO + MARGEN_OBJETO - 60,
                habHabitacion.getPosYObjeto1() * Habitacion.CASILLA_SUELO + MARGEN_OBJETO + 20,
                casCasa.getPerro().getAncho(), casCasa.getPerro().getAlto(), null);
            
            if( CanColapi.getCasa().getEscalera().getEstado() == Objeto.NOLOTENGO ) {
                g.drawImage(casCasa.getEscalera().getImagen(),
                    habHabitacion.getPosXObjeto2() * Habitacion.CASILLA_SUELO + MARGEN_OBJETO - 20,
                    habHabitacion.getPosYObjeto2() * Habitacion.CASILLA_SUELO + MARGEN_OBJETO + 20,
                    casCasa.getEscalera().getAncho(), casCasa.getEscalera().getAlto(), null);
            }
        }       
    }
    
    
    private void DibujarMuebles( Graphics g ) {
        int i;
        Mueble m;
        
        for( i=0; i<habHabitacion.getMuebles().size(); i++ ) {
            m = new Mueble();
            m = habHabitacion.getMuebles().get(i);
            g.drawImage(m.getImagen(),
                    m.getPosX() + habHabitacion.getPosX(),
                    m.getPosY() + habHabitacion.getPosY(),
                    m.getAncho(), m.getAlto(), null);
        }
    }
    
    
    public void DibujarPanelMensajes( Graphics g ) {
        CanColapi.getPanelMensajes().setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        repaint();
    }

    public void MoverCarlos( int iDireccion ) {
        casCasa.getCarlos().setDireccion(iDireccion);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if( ke.getKeyCode() == KeyEvent.VK_DOWN ) {
            MoverCarlos(Carlos.MOVER_ABAJO);
            iTeclaActual = KeyEvent.VK_DOWN;
        }
        else if( ke.getKeyCode() == KeyEvent.VK_UP ) {
            MoverCarlos(Carlos.MOVER_ARRIBA);
            iTeclaActual = KeyEvent.VK_UP;
        }
        else if( ke.getKeyCode() == KeyEvent.VK_LEFT ) {
            MoverCarlos(Carlos.MOVER_IZQUIERDA);
            iTeclaActual = KeyEvent.VK_LEFT;
        }
        else if( ke.getKeyCode() == KeyEvent.VK_RIGHT ) {
            MoverCarlos(Carlos.MOVER_DERECHA);
            iTeclaActual = KeyEvent.VK_RIGHT;
        }
        else if( ke.getKeyCode() == KeyEvent.VK_ENTER ) {
            // Comprobamos si está activo el botón de "Coger"
            if( CanColapi.getPanelBotones().isCogerActivo() ) {           
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
                CanColapi.getPanelBotones().setBotonCogerActivo(false);
            }
            
            //Comprobamos si el botón "Usar" se encuentra activo
            else if( CanColapi.getPanelBotones().isUsarActivo() ) {
                // Habitación del perro (habitación 8), se usa el hueso
                if( CanColapi.getCasa().getHabActiva().getNumHab() == 8 ) {
                    CanColapi.getCasa().getPerro().CambiarEstado(Perro.TRANQUILO);
                    CanColapi.getCasa().getHueso().CambiarEstado(Objeto.USADO);
                    CanColapi.getMochila().SacarHueso();
                    CanColapi.getCasa().getPerro().CambiarImagen();
                    CanColapi.getPanelBotones().setBotonCogerActivo(true);
                    CanColapi.getPanelMensajes().LimpiarTexto();
                    CanColapi.getPanelMensajes().AnadirTexto("'La bestia se abalanza sobre el misterioso hueso destrozándolo en");
                    CanColapi.getPanelMensajes().AnadirTexto("cuestión de segundos. De forma increíble se transforma en un dócil");
                    CanColapi.getPanelMensajes().AnadirTexto("cachorro que se queda dormido plácidamente'.");
                    CanColapi.getPanelMensajes().AnadirTexto("Pensaba que no lo contaba...");
                    CanColapi.getPanelBotones().setBotonUsarActivo(false);
                    CanColapi.getPanelBotones().setBotonCogerActivo(true);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if( (ke.getKeyCode() == KeyEvent.VK_DOWN) ) {
            if( iTeclaActual == KeyEvent.VK_DOWN) MoverCarlos(Carlos.QUIETO_ABAJO);
        }
        else if( (ke.getKeyCode() == KeyEvent.VK_UP) ) {
            if( iTeclaActual == KeyEvent.VK_UP) MoverCarlos(Carlos.QUIETO_ARRIBA);
        }
        else if( (ke.getKeyCode() == KeyEvent.VK_LEFT) ) {
            if( iTeclaActual == KeyEvent.VK_LEFT) MoverCarlos(Carlos.QUIETO_IZQUIERDA);
        }
        else if( (ke.getKeyCode() == KeyEvent.VK_RIGHT) ) {
            if( iTeclaActual == KeyEvent.VK_RIGHT) MoverCarlos(Carlos.QUIETO_DERECHA);
        }
    }
  
}
