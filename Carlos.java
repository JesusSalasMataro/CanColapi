package cancolapi;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Carlos extends Thread {
    
    // Tamaño del sprite de Carlos
    public static int CARLOS_ANCHO = 60;
    public static int CARLOS_ALTO = 130;

    public final static int MOVER_DERECHA = 1;
    public final static int MOVER_IZQUIERDA = 2;
    public final static int MOVER_ARRIBA = 3;
    public final static int MOVER_ABAJO = 4;
    
    public final static int QUIETO_DERECHA = 5;
    public final static int QUIETO_IZQUIERDA = 6;
    public final static int QUIETO_ARRIBA = 7;
    public final static int QUIETO_ABAJO = 8;

    private final int ARRIBA = 1;
    private final int ABAJO = 2;
    private final int DERECHA = 3;
    private final int IZQUIERDA = 4;
    
    public final static boolean PASO_DERECHO = true;
    public final static boolean PASO_IZQUIERDO = false;

    public static Image[] imgCarlos_Abajo, imgCarlos_Arriba, 
                          imgCarlos_Derecha, imgCarlos_Izquierda;
    
    private Habitacion habHabitacion;
    private int iDireccion, iPosX, iPosY, iPaso;
    private boolean bPie;
    private boolean bRunOk;
       
    public Carlos() {
        
        iDireccion = QUIETO_ABAJO;
        bRunOk = true;
        
        imgCarlos_Abajo = new Image[3];
        imgCarlos_Abajo[0] = new ImageIcon(
            getClass().getResource("./images/carlos_abajo_quieto_60x130.png")).getImage();
        imgCarlos_Abajo[1] = new ImageIcon(
            getClass().getResource("./images/carlos_abajo_1_60x130.png")).getImage();
        imgCarlos_Abajo[2] = new ImageIcon(
            getClass().getResource("./images/carlos_abajo_2_60x130.png")).getImage();
        
        imgCarlos_Arriba = new Image[3];
        imgCarlos_Arriba[0] = new ImageIcon(
            getClass().getResource("./images/carlos_arriba_quieto_60x130.png")).getImage();
        imgCarlos_Arriba[1] = new ImageIcon(
            getClass().getResource("./images/carlos_arriba_1_60x130.png")).getImage();
        imgCarlos_Arriba[2] = new ImageIcon(
            getClass().getResource("./images/carlos_arriba_2_60x130.png")).getImage();
        
        imgCarlos_Derecha = new Image[3];
        imgCarlos_Derecha[0] = new ImageIcon(
            getClass().getResource("./images/carlos_der_quieto_60x130.png")).getImage();
        imgCarlos_Derecha[1] = new ImageIcon(
            getClass().getResource("./images/carlos_der_1_60x130.png")).getImage();
        imgCarlos_Derecha[2] = new ImageIcon(
            getClass().getResource("./images/carlos_der_2_60x130.png")).getImage();
        
        imgCarlos_Izquierda = new Image[3];
        imgCarlos_Izquierda[0] = new ImageIcon(
            getClass().getResource("./images/carlos_izq_quieto_60x130.png")).getImage();
        imgCarlos_Izquierda[1] = new ImageIcon(
            getClass().getResource("./images/carlos_izq_1_60x130.png")).getImage();
        imgCarlos_Izquierda[2] = new ImageIcon(
            getClass().getResource("./images/carlos_izq_2_60x130.png")).getImage();
        
        iPosX = 100; iPosY = 100; iPaso = 0; bPie = PASO_DERECHO;
        
    }
    
    public void run() {        
        while( bRunOk ) {
            switch( iDireccion ) {
                case MOVER_ABAJO:
                    if( (iPosY+10) < habHabitacion.getLimiteY() ) {
                        if( !HayObstaculo(iPosX, iPosY+10, ABAJO) )
                            iPosY = iPosY + 10;
                    }
                    else {
                        if( habHabitacion.HayPuerta(
                                habHabitacion.getCasillaX(iPosX), Puerta.ABAJO) ) {
                            CambiarHabitacion(habHabitacion.getNumHab(), Puerta.ABAJO);
                        }
                    }
                    break;
                case MOVER_ARRIBA:
                    if( (iPosY-10) > 0 ) {
                        if( !HayObstaculo(iPosX, iPosY-10, ARRIBA) )
                            iPosY = iPosY - 10;
                    }
                    else {
                        if( habHabitacion.HayPuerta(
                                habHabitacion.getCasillaX(iPosX), Puerta.ARRIBA) ) {
                            CambiarHabitacion(habHabitacion.getNumHab(), Puerta.ARRIBA);
                        }
                    }                    
                    break;
                case MOVER_DERECHA:
                    if( (iPosX+10) < habHabitacion.getLimiteX() ) {
                        if( !HayObstaculo(iPosX+10, iPosY, DERECHA) )
                        iPosX = iPosX + 10;
                    }
                    else {
                        if( habHabitacion.HayPuerta(
                                habHabitacion.getCasillaX(iPosY), Puerta.DERECHA) ) {
                            CambiarHabitacion(habHabitacion.getNumHab(), Puerta.DERECHA);
                        }
                    }                    
                    break;
                case MOVER_IZQUIERDA:
                    if( (iPosX-10) > 0 ) {
                        if( !HayObstaculo(iPosX-10, iPosY, IZQUIERDA) )
                            iPosX = iPosX - 10;
                    }
                    else {
                        if( habHabitacion.HayPuerta(
                                habHabitacion.getCasillaX(iPosY), Puerta.IZQUIERDA) ) {
                            CambiarHabitacion(habHabitacion.getNumHab(), Puerta.IZQUIERDA);
                        }
                    }                       
                    break;               
            }
            CambiarPaso();                               
            try {
                Thread.sleep(80); }
            catch( InterruptedException e ) {}        
        }
    }
    
    
    public void VolverInicio() {       
            try {
                Thread.sleep(5000); }
            catch( InterruptedException e ) {}
            setRunOk(false);
            CanColapi.getCasa().setHabActiva(0);
            CanColapi.getPanelMensajes().LimpiarTexto();
            CanColapi.getCasa().InicializarCarlos();
            CanColapi.getCasa().getCarlos().start();
    }
    
    
    public void CambiarHabitacion( int iNumHabActual, int iDireccion ) {
        int iPosicPuerta;
        
        switch( iNumHabActual ) {
            // Habitación superior izquierda
            case 0:
                CanColapi.getCasa().setHabActiva(3);
                break;
            // Habitación superior centro
            case 1:
                CanColapi.getCasa().setHabActiva(4);
                break;
            // Habitación superior derecha
            case 2:
                CanColapi.getCasa().setHabActiva(5);
                break;
            // Habitación centro izquierda
            case 3:
                if( iDireccion == Puerta.ARRIBA ) CanColapi.getCasa().setHabActiva(0);
                else if( iDireccion == Puerta.DERECHA) CanColapi.getCasa().setHabActiva(4);
                else CanColapi.getCasa().setHabActiva(6);
                break;
            // Habitación centro centro
            case 4:
                if( iDireccion == Puerta.ARRIBA ) CanColapi.getCasa().setHabActiva(1);
                else if( iDireccion == Puerta.IZQUIERDA ) CanColapi.getCasa().setHabActiva(3);
                else if( iDireccion == Puerta.DERECHA ) CanColapi.getCasa().setHabActiva(5);
                else CanColapi.getCasa().setHabActiva(7);
                break;
            // Habitación centro derecha
            case 5:
                if( iDireccion == Puerta.ARRIBA ) CanColapi.getCasa().setHabActiva(2);
                else if( iDireccion == Puerta.ABAJO ) {
                    CanColapi.getCasa().setHabActiva(8);
                    if( (CanColapi.getCasa().getPerro().getEstado() == Perro.TRANQUILO) &&
                        (CanColapi.getCasa().getEscalera().getEstado() == Objeto.NOLOTENGO) ) 
                            CanColapi.getPanelBotones().setBotonCogerActivo(true);
                }
                else CanColapi.getCasa().setHabActiva(4);
                break;
            // Habitación inferior izquierda
            case 6:
                CanColapi.getCasa().setHabActiva(3);
                break;
            // Habitación inferior centro
            case 7:
                if( iDireccion == Puerta.ARRIBA ) CanColapi.getCasa().setHabActiva(4);
                else {
                    if( CanColapi.getCasa().getLlave().getEstado() == Objeto.LOTENGO ) {
                        CanColapi.getPanelMensajes().LimpiarTexto();
                        CanColapi.getPanelMensajes().AnadirTexto("¡Por fin! ¡Lo conseguí! Pensé que nunca saldría.");
                        CanColapi.getPanelMensajes().AnadirTexto("¡Podré ir al baile con Joana! ¡Voy a contarle todo ahora mismo!");
                        CanColapi.getJuego().FinalizarJuego();
                    }
                    else {
                        CanColapi.getPanelMensajes().LimpiarTexto();
                        CanColapi.getPanelMensajes().AnadirTexto("¡Maldición! La puerta está cerrada con llave.");
                        CanColapi.getPanelMensajes().AnadirTexto("Se trata de una puerta antigua pero muy sólida.");
                        CanColapi.getPanelMensajes().AnadirTexto("No hay manera de abrirla.");    
                    }
                }
                break;
            // Habitación inferior derecha
            case 8:
                if( CanColapi.getPanelBotones().isCogerActivo() ) 
                    CanColapi.getPanelBotones().setBotonCogerActivo(false);
                if( CanColapi.getPanelBotones().isUsarActivo() )
                    CanColapi.getPanelBotones().setBotonUsarActivo(false); 
                CanColapi.getCasa().setHabActiva(5);
                break; 
        }
        
        // Cambiamos de habitación excepto en el caso de que sea la habitación 7 y la puerta de salida.
        if( !((habHabitacion.getNumHab() == 7) && (iDireccion == Puerta.ABAJO)) ) {
            iPosicPuerta = habHabitacion.PosicPuerta(iDireccion);
            habHabitacion = CanColapi.getCasa().getHabActiva();

            if( iDireccion == Puerta.ABAJO ) {
                iPosX = Habitacion.CASILLA_SUELO * iPosicPuerta + 65;
                iPosY = 0;
            }
            else if( iDireccion == Puerta.ARRIBA ) {
                iPosX = Habitacion.CASILLA_SUELO * iPosicPuerta + 65;
                iPosY = habHabitacion.getLimiteY();
            }
            else if( iDireccion == Puerta.DERECHA ) {
                iPosY = Habitacion.CASILLA_SUELO * iPosicPuerta + 75;
                iPosX = 0;
            }
            else {
                iPosY = Habitacion.CASILLA_SUELO * iPosicPuerta + 75;
                iPosX = habHabitacion.getLimiteX();            
            }
            iPaso = 0; bPie = PASO_DERECHO;
        }
    }
    
    
    public void CambiarPaso() {       
            if( bPie == PASO_DERECHO ) {
                if( iPaso == 0 ) {
                    iPaso = 1;
                    bPie = PASO_IZQUIERDO;
                }
                else iPaso = 0;                      
            }
            else {
                if( iPaso == 0 ) {
                    iPaso = 2;
                    bPie = PASO_DERECHO;
                }
                else iPaso = 0;                        
            }
    }
    
    
    private boolean HayObstaculo( int iPosX, int iPosY, int iDireccion ) {
        boolean bHayObstaculo = false;
        
        if( habHabitacion.getNumHab() == 8 ) {
            if( (iPosX < 250) && (iPosY > 80) && (CanColapi.getCasa().getPerro().getEstado() == Perro.RABIOSO) ) {
                CanColapi.getPanelMensajes().AnadirTexto("¡¡¡Aaaaaarrrrrggggg!!! Debí pensármelo antes de acercarme");
                CanColapi.getPanelMensajes().AnadirTexto("tanto a tan terrible bestia...");
                if( CanColapi.getPanelBotones().isCogerActivo() )
                    CanColapi.getPanelBotones().setBotonCogerActivo(false);
                if( CanColapi.getPanelBotones().isUsarActivo() )
                    CanColapi.getPanelBotones().setBotonUsarActivo(false);                
                VolverInicio();
            }
            else {
                if( (iPosX < 250) && (iPosY > 100) ) bHayObstaculo = true;
            }
        }
        else {       
            switch( iDireccion ) {
                case ARRIBA:
                    if( HayMueble(iPosX, iPosY, ARRIBA) || HayObjeto(iPosX, iPosY, ARRIBA) ) 
                        bHayObstaculo = true;
                    break;
                case ABAJO:
                    if( HayMueble(iPosX, iPosY, ABAJO) || HayObjeto(iPosX, iPosY, ABAJO) ) 
                        bHayObstaculo = true;
                    break;
                case IZQUIERDA:
                    if( HayMueble(iPosX, iPosY, IZQUIERDA) || HayObjeto(iPosX, iPosY, IZQUIERDA) ) 
                        bHayObstaculo = true;
                    break;
                case DERECHA:
                    if( HayMueble(iPosX, iPosY, DERECHA) || HayObjeto(iPosX, iPosY, DERECHA) ) 
                        bHayObstaculo = true;
                    break;
            }
        }
        return bHayObstaculo;
    }
    
    
    private boolean HayMueble( int iPosX, int iPosY, int iDireccion ) {
        boolean bHayMueble = false;
        int i;  
        
        switch( habHabitacion.getNumHab() ) {
            case 0:
                if( ((iPosY<60) && (iPosX>(habHabitacion.getLimiteX()-100))) ||
                    ((iPosY>130) && (iPosX<90)) ) bHayMueble = true; 
                    break;

            case 1:
                if( (iPosY<90) && ((iPosX<120) || (iPosX>290)) ) bHayMueble = true;
                break;
                
            case 2:
                if( (iPosY<90) && (iPosX>(habHabitacion.getLimiteX()-150)) ) bHayMueble = true;
                break;                

            case 3:
                if( (iPosY<280) && (iPosY>80) && (iPosX<110) ) bHayMueble = true;
                break;
                
            case 4:
                if( (iPosX>300) && (iPosX<500) && (iPosY>110) && (iPosY<300) ) bHayMueble = true;
                break;
            
            case 6:
                if( ((iPosX>440) && (iPosY<30)) || ((iPosX>250) && (iPosX<390) && (iPosY>250)) ) bHayMueble = true;
                break;
        }
        return bHayMueble;
    }
    
    
    private boolean HayObjeto( int iPosX, int iPosY, int iDireccion ) {
        boolean bHayObjeto = false;
        
        // Compruebo si ya tenemos el hueso en la mochila, en caso contrario miro si está
        // en la habitación actual
        if( CanColapi.getCasa().getHueso().getEstado() == Objeto.NOLOTENGO ) {
            // Compruebo que la habitación donde está el hueso sea la actual
            if( habHabitacion.getNumHab() == CanColapi.getCasa().getHueso().getNumHab() )
                switch( habHabitacion.getNumHab() ) {
                    case 1:
                        if( (iPosX>370) && (iPosY>250) ) CanColapi.getPanelBotones().setBotonCogerActivo(true);
                        else CanColapi.getPanelBotones().setBotonCogerActivo(false);
                        break;
                    case 2:
                        if( (iPosX<90) && (iPosY<90) ) CanColapi.getPanelBotones().setBotonCogerActivo(true);
                        else CanColapi.getPanelBotones().setBotonCogerActivo(false);
                        break;
                    case 3:
                        if( (iPosX>250) && (iPosY<90) ) CanColapi.getPanelBotones().setBotonCogerActivo(true);
                        else CanColapi.getPanelBotones().setBotonCogerActivo(false);
                        break;
                    case 4:
                        if( (iPosX>500) && (iPosY>240) ) CanColapi.getPanelBotones().setBotonCogerActivo(true);
                        else CanColapi.getPanelBotones().setBotonCogerActivo(false);
                        break;
                    case 5:
                        if( (iPosX>370) && (iPosY<90) ) CanColapi.getPanelBotones().setBotonCogerActivo(true);
                        else CanColapi.getPanelBotones().setBotonCogerActivo(false);
                        break;
                    case 6:
                        if( (iPosX<90) && (iPosY>230) ) CanColapi.getPanelBotones().setBotonCogerActivo(true);
                        else CanColapi.getPanelBotones().setBotonCogerActivo(false);
                        break;
                    case 7:
                        if( (iPosX<90) && (iPosY<90) ) CanColapi.getPanelBotones().setBotonCogerActivo(true);
                        else CanColapi.getPanelBotones().setBotonCogerActivo(false);
                        break;
            }
        }
        
        
        // Compruebo que la habitación donde está la llave sea la actual
        if( habHabitacion.getNumHab() == CanColapi.getCasa().getLlave().getNumHab() ) {
            switch( habHabitacion.getNumHab() ) {
                case 1:
                    if( (iPosX>370) && (iPosY>160) ) {
                        if( CanColapi.getCasa().getLlave().getEstado() == Objeto.NOLOTENGO ) {
                            if( CanColapi.getCasa().getEscalera().getEstado() == Objeto.LOTENGO )
                                CanColapi.getPanelBotones().setBotonCogerActivo(true);
                        }
                        bHayObjeto = true;
                    }
                    else CanColapi.getPanelBotones().setBotonCogerActivo(false);
                    break;
                case 2:
                    if( (iPosX<140) && (iPosY<70) ) {
                        if( CanColapi.getCasa().getLlave().getEstado() == Objeto.NOLOTENGO ) {
                            if( CanColapi.getCasa().getEscalera().getEstado() == Objeto.LOTENGO )
                                CanColapi.getPanelBotones().setBotonCogerActivo(true);
                        }
                        bHayObjeto = true;
                    }
                    else CanColapi.getPanelBotones().setBotonCogerActivo(false);
                    break;
                case 3:
                    if( (iPosX>250) && (iPosY<90) ) {
                        if( CanColapi.getCasa().getLlave().getEstado() == Objeto.NOLOTENGO ) {
                            if( CanColapi.getCasa().getEscalera().getEstado() == Objeto.LOTENGO )
                                CanColapi.getPanelBotones().setBotonCogerActivo(true);
                        }
                        bHayObjeto = true;
                    }
                    else CanColapi.getPanelBotones().setBotonCogerActivo(false);
                    break;
                case 4:
                    if( (iPosX>500) && (iPosY>160) ) {
                        if( CanColapi.getCasa().getLlave().getEstado() == Objeto.NOLOTENGO ) {
                            if( CanColapi.getCasa().getEscalera().getEstado() == Objeto.LOTENGO )
                                CanColapi.getPanelBotones().setBotonCogerActivo(true);
                        }
                        bHayObjeto = true;
                    }
                    else CanColapi.getPanelBotones().setBotonCogerActivo(false);
                    break;
                case 5:
                    if( (iPosX>370) && (iPosY<90) ) {
                        if( CanColapi.getCasa().getLlave().getEstado() == Objeto.NOLOTENGO ) {
                            if( CanColapi.getCasa().getEscalera().getEstado() == Objeto.LOTENGO )
                                CanColapi.getPanelBotones().setBotonCogerActivo(true);
                        }
                        bHayObjeto = true;
                    }
                    else CanColapi.getPanelBotones().setBotonCogerActivo(false);
                    break;
                case 6:
                    if( (iPosX<140) && (iPosY>160) ) {
                        if( CanColapi.getCasa().getLlave().getEstado() == Objeto.NOLOTENGO ) {
                            if( CanColapi.getCasa().getEscalera().getEstado() == Objeto.LOTENGO )
                                CanColapi.getPanelBotones().setBotonCogerActivo(true);
                        }
                        bHayObjeto = true;
                    }
                    else CanColapi.getPanelBotones().setBotonCogerActivo(false);
                    break;
                case 7:
                    if( (iPosX<140) && (iPosY<70) ) {
                        if( CanColapi.getCasa().getLlave().getEstado() == Objeto.NOLOTENGO ) {
                            if( CanColapi.getCasa().getEscalera().getEstado() == Objeto.LOTENGO )
                                CanColapi.getPanelBotones().setBotonCogerActivo(true);
                        }
                        bHayObjeto = true;
                    }
                    else CanColapi.getPanelBotones().setBotonCogerActivo(false);
                    break;
            }
        }
        
               
        return bHayObjeto;
    }
    
    public void setPosicion( int x, int y ) { iPosX = x; iPosY = y; }
    public void setDireccion( int d ) { iDireccion = d; }
    public void setHabActiva( Habitacion habHabitacion ) { this.habHabitacion = habHabitacion; }
    public void setRunOk( boolean bRunOk ) { this.bRunOk = bRunOk; }
    
    public int getPosX() { return iPosX; }
    public int getPosY() { return iPosY; }
    public int getDireccion() { return iDireccion; }
    public int getPaso() { return iPaso; }

}
