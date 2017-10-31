/*
 * @author Pancho
 */

package Clases;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Obstaculo extends Objeto {
    
    //Atributos de la clase
    
    //Constructor
    public Obstaculo(int x, int y, int escala) {
        super(x, y, escala);
        SetId(3);
        SetImagen(GetDefault());
    }
    
    public Image GetDefault(){
        ImageIcon obj = new ImageIcon(this.getClass().getResource("imagenes/prohibido.png"));
        SetImagen(obj.getImage());
        return GetImagen();
    }
}
