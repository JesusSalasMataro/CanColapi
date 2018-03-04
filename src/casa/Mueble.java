package casa;

import java.awt.Image;


public class Mueble {
    
    private Image imgMueble;
    
    // iPosX, iPosY nos indica la posición del mueble en la habitación
    // Señana el pixel inferior izquierdo de la imagen del mueble
    // El mueble se dibujará desde esta posición hacia arriba y hacia la derecha
    private int iPosX, iPosY, iAncho, iAlto;
 
    public Mueble() {}
    public Mueble( Image imgImagen, int iPx, int iPy, int iAncho, int iAlto ) {
        this.imgMueble = imgImagen;
        this.iPosX = iPx;
        this.iPosY = iPy;
        this.iAncho = iAncho;
        this.iAlto = iAlto;
    }
    
    public Image getImagen() { return imgMueble; }
    public int getPosX() { return iPosX; }
    public int getPosY() { return iPosY; }
    public int getAncho() { return iAncho; }
    public int getAlto() { return iAlto; }
    
}
