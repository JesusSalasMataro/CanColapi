package casa;

import javax.swing.ImageIcon;


public class Hueso extends Objeto {
    
    public Hueso() {
        super("Hueso", 32, 32);
        imgObjeto = new ImageIcon(getClass().getResource("./images/hueso64x64.png")).getImage();
    }
    
    public void setDescripcion( String sDescripcion ) { this.sDescripcion = sDescripcion; }
    public void setNumHab( int iNumHab ) { this.iNumHab = iNumHab; }
    public void CambiarEstado( int iEstado ) { this.iEstado = iEstado; }
    public void Poseer( boolean bPoseer ) { 
        if( bPoseer ) {
            this.iEstado = LOTENGO;
            CanColapi.getMochila().MeterHueso();
        } 
    }

}
