package casa;

import javax.swing.ImageIcon;


public class Escalera extends Objeto {
    
    public Escalera() {
        super("Escalera", 128, 128);
        imgObjeto = new ImageIcon(getClass().getResource("./images/escalera_128x128.png")).getImage();
    }
    
    public void setDescripcion( String sDescripcion ) { this.sDescripcion = sDescripcion; }
    public void setNumHab( int iNumHab ) { this.iNumHab = iNumHab; }
    public void CambiarEstado( int iEstado ) { this.iEstado = iEstado; }
    public void Poseer( boolean bPoseer ) { 
        if( bPoseer ) {
            this.iEstado = LOTENGO;
            CanColapi.getMochila().MeterEscalera();
        } 
    }

}
