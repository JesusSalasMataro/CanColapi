package casa;

import javax.swing.ImageIcon;


public class Perro extends Objeto {
    
    public static final int RABIOSO = 3;
    public static final int TRANQUILO = 4;
    
    public Perro() {
        super("Perro", 150, 130);
        CambiarEstado(RABIOSO);
        imgObjeto = new ImageIcon(getClass().getResource("./images/perro_150x130.png")).getImage();
    }
    
    public void setDescripcion( String sDescripcion ) { this.sDescripcion = sDescripcion; }
    public void setNumHab( int iNumHab ) { this.iNumHab = iNumHab; }
    public void CambiarEstado( int iEstado ) { this.iEstado = iEstado; }
    public void Poseer( boolean bPoseer ) { this.iEstado = Objeto.LOTENGO; }
    public void CambiarImagen() {
        imgObjeto = new ImageIcon(getClass().getResource("./images/perro_150x130_dormido.png")).getImage();
    }

}
