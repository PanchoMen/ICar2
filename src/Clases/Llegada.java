/*
 * @author Pancho
 */

package Clases;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Llegada  extends Objeto {
    
    private boolean insertado;
    
    //Constructor
    public Llegada(int x, int y, int escala) {
        super(x, y, escala);
        SetId(2);
        SetImagen(GetDefault());
        insertado = false;
    }
    
    public Image GetDefault(){
        ImageIcon obj = new ImageIcon(this.getClass().getResource("imagenes/parking.gif"));
        SetImagen(obj.getImage());
        return GetImagen();
    }
    
    public boolean GetInsert(){
        return insertado;
    }
    
    public void SetInsert(boolean ins){
        insertado = ins;
    }
}
