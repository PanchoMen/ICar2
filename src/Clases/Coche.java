/*
 * @author Pancho
 */

package Clases;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Coche extends Objeto {
    
    //Atributos de la clase
    
    //Constructor
    public Coche(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
        SetImagen(GetDefault());
    }
    
    public Image GetDefault(){
        ImageIcon obj = new ImageIcon(this.getClass().getResource("imagenes/coche.gif"));
        SetImagen(obj.getImage());
        return GetImagen();
    }
}
