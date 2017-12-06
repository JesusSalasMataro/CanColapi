package cancolapi;

import javax.swing.JDialog;

public class Juego extends Thread {
    
    private CanColapi canColapi;
    private LienzoJuego lnzLienzoJuego;
    private PantallaInicio pntInicio;
    private PantallaFinal pntFinal;
    
    public Juego() { }
    
    public void run() {
        pntInicio = new PantallaInicio();
        pntInicio.setLayout(null);
        
        pntFinal = new PantallaFinal();
        pntFinal.setLayout(null);
        
        MostrarPantallaInicial();
        while( !pntInicio.JuegoIniciado() ) { 
            try {
                Thread.sleep(80); }
            catch( InterruptedException e ) {} 
        }
        canColapi.setContentPane(lnzLienzoJuego);
        canColapi.setVisible(true);
        canColapi.transferFocus();
        CanColapi.getCasa().getCarlos().start();
    }
    
    public void FinalizarJuego() {
        CanColapi.getCasa().getCarlos().setRunOk(false);
        try {
            Thread.sleep(5000); }
        catch( InterruptedException e ) {}        
        MostrarPantallaFinal();
    }
    
    public void setCanColapi( CanColapi c, LienzoJuego l ) {
        this.canColapi = c;
        this.lnzLienzoJuego = l;
    }
    
    private void MostrarPantallaInicial() {              
        canColapi.setContentPane(pntInicio);
        canColapi.setVisible(true);
        pntInicio.IniciarAnimacion();        
    }
    
    private void MostrarPantallaFinal() {
        canColapi.setContentPane(pntFinal);
        canColapi.setVisible(true);
        pntFinal.IniciarAnimacion();
    }
    
    public PantallaInicio getPantallaInicio() { return pntInicio; }

}

