/*
 * @author Pancho
 */

package Clases;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Llegada  extends Objeto {
    
    //Constructor
    public Llegada(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
        SetImagen(GetDefault());
    }
    
    public Image GetDefault(){
        ImageIcon obj = new ImageIcon(this.getClass().getResource("imagenes/parking.gif"));
        SetImagen(obj.getImage());
        return GetImagen();
    }
    
}
