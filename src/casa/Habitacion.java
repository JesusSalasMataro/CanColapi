package casa;

import java.awt.Image;
import java.util.Vector;
import javax.swing.ImageIcon;


public class Habitacion {
    
    public static final int ALTO_MURO_HORIZONTAL = 105;
    public static final int GRUESO_MURO = 25;
    public static final int ANCHO_MURO_HORIZONTAL = 130;
    public static final int ANCHO_MURO_HORIZONTAL_PEQ = 105;
    public static final int ALTO_MURO_VERTICAL = 130;
    public static final int ALTO_MURO_VERTICAL_PEQ = 105;
    public static final int ANCHO_VENTANA_ROTA = 130;
    public static final int ALTO_VENTANA_ROTA = 70;
    public static final int CASILLA_SUELO = 130;
    
    private int iNumHabitacion;
    private int iAncho, iAlto, iPosX, iPosY, iLimiteX, iLimiteY;
    private int iPosXObjeto1, iPosYObjeto1, iPosXObjeto2, iPosYObjeto2;
    private Vector<Puerta> prtPuertas;
    private Vector<Mueble> mblMuebles;
    public static Image imgSuelo, imgMuroHorizontal, imgMuroVertical, imgMuroVerticalPeq,
            imgMuroEsquina, imgMuroHorizontalInferior, imgMuroHorizontalInferiorPeq, 
            imgPuertaHorizontal, imgPuertaHorizontalInferior, imgPuertaVertical,
            imgVentanaRota;
    
    public Habitacion( int iNumHabitacion, int iAncho, int iAlto, int iPosX, int iPosY ) {
        this.iNumHabitacion = iNumHabitacion;
        this.iAncho = iAncho;
        this.iAlto = iAlto;
        this.iPosX = iPosX;
        this.iPosY = iPosY;
        this.iLimiteX = CASILLA_SUELO * iAncho - 10;
        this.iLimiteY = CASILLA_SUELO * iAlto - GRUESO_MURO - 10;
        imgSuelo = new ImageIcon(getClass().getResource("./images/suelo02.png")).getImage();
        imgMuroHorizontal = new ImageIcon(getClass().getResource("./images/muro_horizontal.png")).getImage(); 
        imgMuroVertical = new ImageIcon(getClass().getResource("./images/muro_vertical.png")).getImage();
        imgMuroVerticalPeq = new ImageIcon(getClass().getResource("./images/muro_vertical_peq.png")).getImage();
        imgMuroEsquina = new ImageIcon(getClass().getResource("./images/muro_esquina.png")).getImage();
        imgMuroHorizontalInferior = new ImageIcon(getClass().getResource("./images/muro_horizontal_inferior.png")).getImage();
        imgMuroHorizontalInferiorPeq = new ImageIcon(getClass().getResource("./images/muro_horizontal_inferior_peq.png")).getImage();
        imgPuertaHorizontal = new ImageIcon(getClass().getResource("./images/puerta_horizontal.png")).getImage();
        imgPuertaHorizontalInferior = new ImageIcon(getClass().getResource("./images/puerta_horizontal_inferior.png")).getImage();
        imgPuertaVertical = new ImageIcon(getClass().getResource("./images/puerta_vertical.png")).getImage(); 
        imgVentanaRota = new ImageIcon(getClass().getResource("./images/ventana_rota2_130x70.png")).getImage();
        
        prtPuertas = new Vector<Puerta>();
        mblMuebles = new Vector<Mueble>();
    }
    
    
    public void AnadirPuerta( int iPosicion, int iLado ) {        
        Puerta p = new Puerta(iPosicion, iLado);
        prtPuertas.add(p);       
    }
    
    
    public void AnadirMueble( Mueble m ) {
        mblMuebles.add(m);
    }
    
    
    public boolean HayPuerta( int iPos, int iLado ) {       
        boolean bHayPuerta = false;
        int i;
        
        for( i=0; i<prtPuertas.size(); i++ ) {
           if( (prtPuertas.get(i).getPosicion() == iPos) && (prtPuertas.get(i).getLado() == iLado) ) {
               bHayPuerta = true;
           }
        }      
        return bHayPuerta;        
    }
    
    // Devuelve la posición de la puerta en el lado especificado
    public int PosicPuerta( int iDireccion ) {
        int iPos = -1;
        int i;
        
        for( i=0; i<prtPuertas.size(); i++ ) {
            if( prtPuertas.get(i).getLado() == iDireccion ) 
                iPos = prtPuertas.get(i).getPosicion();
        }
        return iPos;
    }
    
    public void setPosicObjetos( int iPxObj1, int iPyObj1, int iPxObj2, int iPyObj2 ) {
        iPosXObjeto1 = iPxObj1;
        iPosYObjeto1 = iPyObj1;
        iPosXObjeto2 = iPxObj2;
        iPosYObjeto2 = iPyObj2;
    }
    
    
    public int getNumHab() { return iNumHabitacion; }
    public int getAncho() { return iAncho; }
    public int getAlto() { return iAlto; }
    public int getPosX() { return iPosX; }
    public int getPosY() { return iPosY; }
    public int getLimiteX() { return iLimiteX; }
    public int getLimiteY() { return iLimiteY; }
    public Image getSuelo() { return imgSuelo; }
    public Image getMuroHorizontal() { return imgMuroHorizontal; }
    public Image getMuroVertical() { return imgMuroVertical; }
    public Image getMuroVerticalPeq() { return imgMuroVerticalPeq; }
    public Image getMuroEsquina() { return imgMuroEsquina; }
    public Image getMuroHorizontalInferior() { return imgMuroHorizontalInferior; }
    public Image getMuroHorizontalInferiorPeq() { return imgMuroHorizontalInferiorPeq; }
    public Image getPuertaHorizontal() { return imgPuertaHorizontal; }
    public Image getPuertaHorizontalInferior() { return imgPuertaHorizontalInferior; }
    public Image getPuertaVertical() { return imgPuertaVertical; }
    public Image getVentanaRota() { return imgVentanaRota; }
    
    public int getPosXObjeto1() { return iPosXObjeto1; }
    public int getPosYObjeto1() { return iPosYObjeto1; }
    public int getPosXObjeto2() { return iPosXObjeto2; }
    public int getPosYObjeto2() { return iPosYObjeto2; }
    
    public Vector<Mueble> getMuebles() { return mblMuebles; }
    
    public int getCasillaX( int iPixelX ) { return (iPixelX / CASILLA_SUELO); }
    public int getCasillaY( int iPixelY ) { return (iPixelY / CASILLA_SUELO); }
    
}
