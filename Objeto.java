package cancolapi;

import java.awt.Image;


public abstract class Objeto {
    
    public static final int NOLOTENGO = 0;
    public static final int LOTENGO = 1;
    public static final int USADO = 2;
       
    protected String sDescripcion;
    protected int iEstado;
    protected int iNumHab;
    protected int iAncho, iAlto; 
    protected Image imgObjeto;
    
    
    public Objeto() { 
        Poseer(false);
    }
    
    public Objeto( String sDescripcion, int iAncho, int iAlto ) { 
        this.sDescripcion = sDescripcion;
        this.iAncho = iAncho;
        this.iAlto = iAlto;
        iEstado = NOLOTENGO;
        Poseer(false);
    }
    
    abstract void setDescripcion( String sDescripcion ); 
    abstract void setNumHab( int iNumHab );
    abstract void CambiarEstado( int iEstado );
    abstract void Poseer( boolean bPoseer );
    
    public String getDescripcion() { return sDescripcion; }
    public int getEstado() { return iEstado; }
    public int getNumHab() { return iNumHab; }
    public Image getImagen() { return imgObjeto; }
    public int getAlto() { return iAlto; }
    public int getAncho() { return iAncho; }
    
}
