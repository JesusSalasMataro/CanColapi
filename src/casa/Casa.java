package casa;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;


public class Casa {
    
    private final int NUM_HABITACIONES = 9;
    
    private Carlos carCarlos;
    private Habitacion[] habHabitacion;
    private int iHabActiva;
    private Hueso objHueso;
    private Llave objLlave;
    private Escalera objEscalera;
    private Perro objPerro;
    
    
    public Casa() {       
        InicializarHabitaciones();
        InicializarPosicObjHabitaciones();
        InicializarCarlos();
        InicializarObjetos();
        SituarObjetos();
        SituarMuebles();
    }
    
    
    private void InicializarObjetos() {       
        objHueso = new Hueso();
        objLlave = new Llave();
        objEscalera = new Escalera();
        objPerro = new Perro();
    }
    
    
    private void SituarObjetos() {
        int iNumHabLlave, iNumHabHueso;
        
        objEscalera.setNumHab(8);
        objPerro.setNumHab(8);
        
        // Número aleatorio en el rango 1-6
        // La habitación 0 es la inicial, la 7 es la de salida y la 8 es la del perro
        Random random = new Random();
        iNumHabLlave = random.nextInt(6) + 1;
        
        iNumHabHueso = iNumHabLlave;
        while( iNumHabHueso == iNumHabLlave ) {
            iNumHabHueso = random.nextInt(6) + 1;
        }

        objLlave.setNumHab(iNumHabLlave);
        objHueso.setNumHab(iNumHabHueso);    
    }
    
    
    private void SituarMuebles() {
        Image imgMueble = new ImageIcon(getClass().getResource("./images/comoda_80x70.png")).getImage();   
        Mueble m = new Mueble(imgMueble, 30, 260, 80, 70);
        habHabitacion[0].AnadirMueble(m);
        
        imgMueble = new ImageIcon(getClass().getResource("./images/vidriera_70x160.png")).getImage();
        m = new Mueble(imgMueble, habHabitacion[0].getLimiteX() - 40, 30, 70, 160);
        habHabitacion[0].AnadirMueble(m);
        
        imgMueble = new ImageIcon(getClass().getResource("./images/sofa_100x180.png")).getImage();
        m = new Mueble(imgMueble, 20, 200, 100, 180);
        habHabitacion[3].AnadirMueble(m);
        
        imgMueble = new ImageIcon(getClass().getResource("./images/bañera04_150x200.png")).getImage();
        m = new Mueble(imgMueble, 300, habHabitacion[6].getLimiteY() - 20, 100, 130);
        habHabitacion[6].AnadirMueble(m);
        
        imgMueble = new ImageIcon(getClass().getResource("./images/lavabo_50x110.png")).getImage();
        m = new Mueble(imgMueble, habHabitacion[6].getLimiteX() - 20, 30, 50, 110);
        habHabitacion[6].AnadirMueble(m);
        
        imgMueble = new ImageIcon(getClass().getResource("./images/mesa_sillas_200x190.png")).getImage();
        m = new Mueble(imgMueble, 330, 230, 200, 190);
        habHabitacion[4].AnadirMueble(m);
        
        imgMueble = new ImageIcon(getClass().getResource("./images/cama01_200x175.png")).getImage();
        m = new Mueble(imgMueble, habHabitacion[1].getLimiteX() - 180, 0, 200, 175);
        habHabitacion[1].AnadirMueble(m);    
    
        imgMueble = new ImageIcon(getClass().getResource("./images/armario_115x200.png")).getImage();
        m = new Mueble(imgMueble, 30, 0, 115, 200);
        habHabitacion[1].AnadirMueble(m);   
        
        imgMueble = new ImageIcon(getClass().getResource("./images/mesa02_160x100.png")).getImage();
        m = new Mueble(imgMueble, 520, 90, 160, 100);
        habHabitacion[2].AnadirMueble(m);  

    }
    
    
    public void InicializarCarlos() {       
        carCarlos = new Carlos();
        carCarlos.setDireccion(Carlos.QUIETO_ABAJO);
        carCarlos.setHabActiva(getHabActiva());        
    }
    
    
    private void InicializarHabitaciones() {       
        iHabActiva = 0;
        habHabitacion = new Habitacion[NUM_HABITACIONES];
      
        // Habitación superior izquierda
        habHabitacion[0] = new Habitacion(0, 2, 2, 50, 50);
        habHabitacion[0].AnadirPuerta(1, Puerta.ABAJO);

        // Habitación superior centro
        habHabitacion[1] = new Habitacion(1, 4, 3, 50, 50);
        habHabitacion[1].AnadirPuerta(1, Puerta.ABAJO);  
        
        // Habitación superior derecha
        habHabitacion[2] = new Habitacion(2, 5, 2, 50, 50);
        habHabitacion[2].AnadirPuerta(1, Puerta.ABAJO); 
        
        // Habitación centro izquierda
        habHabitacion[3] = new Habitacion(3, 3, 3, 50, 50);
        habHabitacion[3].AnadirPuerta(0, Puerta.ABAJO); 
        habHabitacion[3].AnadirPuerta(1, Puerta.ARRIBA); 
        habHabitacion[3].AnadirPuerta(1, Puerta.DERECHA);
        
        // Habitación centro centro
        habHabitacion[4] = new Habitacion(4, 5, 3, 50, 50);
        habHabitacion[4].AnadirPuerta(2, Puerta.ABAJO); 
        habHabitacion[4].AnadirPuerta(1, Puerta.ARRIBA); 
        habHabitacion[4].AnadirPuerta(1, Puerta.IZQUIERDA); 
        habHabitacion[4].AnadirPuerta(0, Puerta.DERECHA); 
        
        // Habitación centro derecha
        habHabitacion[5] = new Habitacion(5, 4, 3, 50, 50);
        habHabitacion[5].AnadirPuerta(2, Puerta.ABAJO);
        habHabitacion[5].AnadirPuerta(1, Puerta.ARRIBA);
        habHabitacion[5].AnadirPuerta(0, Puerta.IZQUIERDA);
        
        // Habitación abajo izquierda
        habHabitacion[6] = new Habitacion(6, 4, 3, 50, 50);
        habHabitacion[6].AnadirPuerta(0, Puerta.ARRIBA); 
        
        // Habitación centro abajo
        habHabitacion[7] = new Habitacion(7, 4, 3, 50, 50);
        habHabitacion[7].AnadirPuerta(2, Puerta.ABAJO); 
        habHabitacion[7].AnadirPuerta(2, Puerta.ARRIBA);
        
        // Habitación centro derecha
        habHabitacion[8] = new Habitacion(8, 3, 2, 50, 50);
        habHabitacion[8].AnadirPuerta(2, Puerta.ARRIBA);               
    }
    
    
    private void InicializarPosicObjHabitaciones() {
        habHabitacion[0].setPosicObjetos(0, 0, 0, 0);
        habHabitacion[1].setPosicObjetos(3, 2, 3, 0);
        habHabitacion[2].setPosicObjetos(0, 0, 4, 1);
        habHabitacion[3].setPosicObjetos(2, 0, 2, 2);
        habHabitacion[4].setPosicObjetos(4, 2, 4, 2);
        habHabitacion[5].setPosicObjetos(3, 0, 3, 1);
        habHabitacion[6].setPosicObjetos(0, 2, 2, 2);
        habHabitacion[7].setPosicObjetos(0, 0, 0, 1);
        habHabitacion[8].setPosicObjetos(1, 1, 0, 1);
    }
    
    
    public Habitacion getHabActiva() { return habHabitacion[iHabActiva]; }
    public Carlos getCarlos() { return carCarlos; }
    public Hueso getHueso() { return objHueso; }
    public Llave getLlave() { return objLlave; }
    public Escalera getEscalera() { return objEscalera; }
    public Perro getPerro() { return objPerro; }
    
    public void setHabActiva( int iHabActiva ) { 
        this.iHabActiva = iHabActiva; 
        CanColapi.getPanelMensajes().LimpiarTexto();

        if( (objHueso.getEstado() == Objeto.NOLOTENGO) && (objHueso.getNumHab() == iHabActiva) ) {
            CanColapi.getPanelMensajes().AnadirTexto(
                "¡Vaya hueso! Espero que no sea humano...");
            CanColapi.getPanelMensajes().AnadirTexto(
                "Qué extraño, tiene una especie de brillo natural...");           
            CanColapi.getPanelMensajes().AnadirTexto(
                "Nunca había visto nada parecido, podría cogerlo, tal vez me sirva para algo.");           
        }
        else if( (objLlave.getEstado() == Objeto.NOLOTENGO) && (objLlave.getNumHab() == iHabActiva) ) {
            if( objEscalera.getEstado() == Objeto.NOLOTENGO ) {
                CanColapi.getPanelMensajes().AnadirTexto(
                    "Parece haber algo brillante sobre ese viejo armario...");
                CanColapi.getPanelMensajes().AnadirTexto(
                    "Que bien me vendría tener una escalera para poder subirme.");
            }
            else {
                CanColapi.getPanelMensajes().AnadirTexto(
                    "Ahora sí que podré alcanzar el techo del armario.");
                CanColapi.getPanelMensajes().AnadirTexto(
                    "Espero encontrar algo que me sirva...");                
            }
        }
        else {
            switch( iHabActiva ) {
                case 0:
                    CanColapi.getPanelMensajes().AnadirTexto(
                        "La ventana ha quedado sellada por una fuerza misteriosa.");
                    CanColapi.getPanelMensajes().AnadirTexto(
                        "¡Vaya! ¡Tengo que encontrar otra salida!");
                    break;
                case 1:
                    CanColapi.getPanelMensajes().AnadirTexto(
                        "Vaya dormitorio más tenebroso, se percibe un olor extrañísimo aquí.");
                    CanColapi.getPanelMensajes().AnadirTexto(
                        "Hay un cuadro que representa una salvaje cacería. Vaya gusto tenían los");
                    CanColapi.getPanelMensajes().AnadirTexto(
                        "dueños... Mejor salgo de aquí.");
                    break;
                case 2:
                    CanColapi.getPanelMensajes().AnadirTexto(
                        "Otra habitación sin salida. ¿Para qué demonios usarían este cuarto?");
                    CanColapi.getPanelMensajes().AnadirTexto(
                        "No tiene ni ventanas ni ventilación de ningún tipo. ¡Esto más que una casa");
                    CanColapi.getPanelMensajes().AnadirTexto(
                        "parece un laberinto!");
                    break;
                case 3:
                    CanColapi.getPanelMensajes().AnadirTexto(
                        "Se oyen muchos ruídos extraños, este lugar es realmente espeluznante.");
                    CanColapi.getPanelMensajes().AnadirTexto(
                        "¿Estará la casa realmente encantada? ¡Bah! ¡Bobadas! Vamos Carlos no te");
                    CanColapi.getPanelMensajes().AnadirTexto(
                        "dejes impresionar por una simple casa, al fin y al cabo no es más que eso.");
                    break;
                case 4:
                    CanColapi.getPanelMensajes().AnadirTexto(
                        "Esto debe de ser la zona del comedor, aunque está todo destrozado.");
                    CanColapi.getPanelMensajes().AnadirTexto(
                        "Espero que la salida esté cerca de aquí...");
                    break;
                case 5:
                    if( CanColapi.getCasa().getPerro().getEstado() == Perro.TRANQUILO ) {
                        CanColapi.getPanelMensajes().AnadirTexto(
                            "Me encuentro agotado, espero poder salir de aquí pronto.");
                        CanColapi.getPanelMensajes().AnadirTexto(
                            "¡Ánimo Carlos! No puedo venirme abajo ahora.");                        
                    }
                    else {
                        CanColapi.getPanelMensajes().AnadirTexto(
                            "Que serán esos ruídos tan extraños, parecen aullidos.");
                        CanColapi.getPanelMensajes().AnadirTexto(
                            "Empiezo a estar realmente asustado...");
                    }
                    break;
                case 6:
                    CanColapi.getPanelMensajes().AnadirTexto(
                        "Más que un baño esto parece un cuarto de torturas.");
                    CanColapi.getPanelMensajes().AnadirTexto(
                        "¡Hay restos de sangre por toda la pared!");
                    CanColapi.getPanelMensajes().AnadirTexto(
                        "Estoy empezando a arrepentirme de haber entrado en este lugar...");
                    break;
                case 7:
                    if( objLlave.getEstado() == Objeto.LOTENGO ) {
                        CanColapi.getPanelMensajes().AnadirTexto(
                            "¡Por fin la salida!");
                        CanColapi.getPanelMensajes().AnadirTexto(
                            "Espero que me sirva la llave que encontré...");
                    }
                    else {
                        CanColapi.getPanelMensajes().AnadirTexto(
                            "¡Esta debe de ser la puerta de salida!");
                        CanColapi.getPanelMensajes().AnadirTexto(
                            "¡Me largo de aquí ahora mismo!");
                    }
                    break;
                case 8:
                    if( objPerro.getEstado() == objPerro.RABIOSO ) {
                        CanColapi.getPanelMensajes().AnadirTexto(
                            "¡Aaarrrggghhh! ¿Qué es ese horrible monstruo?");
                        CanColapi.getPanelMensajes().AnadirTexto(
                            "Mejor me voy de aquí."); 
                        if( CanColapi.getCasa().getHueso().getEstado() == Objeto.LOTENGO ) {
                            CanColapi.getPanelBotones().setBotonUsarActivo(true);
                        }
                    }
                    else {
                        if( objEscalera.getEstado() == Objeto.LOTENGO ) {
                            CanColapi.getPanelMensajes().AnadirTexto(
                                "Parece mentira que este perrito fuera una horrible bestia hace solo un momento.");
                            CanColapi.getPanelMensajes().AnadirTexto(
                                "Mejor sigo buscando la salida.");                                                                           
                        }
                        else {
                            CanColapi.getPanelMensajes().AnadirTexto(
                                "Ahora puedo coger la escalera.");
                            CanColapi.getPanelMensajes().AnadirTexto(
                                "Investigaré con ella el techo de aquel viejo armario...");                                                                           
                        }
                    }                        
                    break;                   
            }
        }
    }
    
}
