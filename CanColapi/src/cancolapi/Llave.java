package cancolapi;

import java.awt.Image;
import javax.swing.ImageIcon;


public class Llave extends Objeto {
    
    private Image imgArmarioSinLlave;
    
    public Llave() {
        super("Llave", 130, 160);
        imgObjeto = new ImageIcon(getClass().getResource("./images/armario02_llave_130x160.png")).getImage();
        imgArmarioSinLlave = new ImageIcon(getClass().getResource("./images/armario02_130x160.png")).getImage();
    }
    
    public void setDescripcion( String sDescripcion ) { this.sDescripcion = sDescripcion; }
    public void setNumHab( int iNumHab ) { this.iNumHab = iNumHab; }
    public void CambiarEstado( int iEstado ) { this.iEstado = iEstado; }
    public void Recogida() { this.imgObjeto = this.imgArmarioSinLlave; }
    public void Poseer( boolean bPoseer ) { 
        if( bPoseer ) {
            this.iEstado = Objeto.LOTENGO;
            CanColapi.getMochila().MeterLlave();
        } 
    }


}
