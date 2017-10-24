/*
 * @author Pancho
 */

package Clases;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Coche extends Objeto {
    
    //Atributos de la clase
    private boolean insertado;
    
    //Constructor
    public Coche(int x, int y, int escala) {
        super(x, y, escala);
        SetId(1);
        SetImagen(GetDefault());
        insertado = false;
    }
    
    public Image GetDefault(){
        ImageIcon obj = new ImageIcon(this.getClass().getResource("imagenes/coche.gif"));
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
