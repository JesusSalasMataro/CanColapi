package cancolapi;


public class Puerta {
    
    public static final int ARRIBA = 1;
    public static final int ABAJO = 2;
    public static final int DERECHA = 3;
    public static final int IZQUIERDA = 4;
    
    private int iPosicion, iLado;
    
    public Puerta( int iPosicion, int iLado ) {
        this.iPosicion = iPosicion;
        this.iLado = iLado;
    }
    
    public int getPosicion() { return iPosicion; }
    public int getLado() { return iLado; }
    
}
