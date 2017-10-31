/**
 * @author Pancho
 */

package Clases;

import java.awt.Image;
import javax.swing.ImageIcon;


public class Camino extends Objeto{
    int color;
    public Camino(int x, int y, int escala, int color) {
        super(x, y, escala);
        this.color = color;
        this.SetImagen(GetDefault());
    }
    
    public Image GetDefault(){
        ImageIcon obj= new ImageIcon(this.getClass().getResource("imagenes/puntorojo.png"));
        if(color == 1){
             obj = new ImageIcon(this.getClass().getResource("imagenes/puntorojo.png"));
        }else if(color == 2){
            obj = new ImageIcon(this.getClass().getResource("imagenes/puntoazul.png"));
        }else if(color == 3){
            obj = new ImageIcon(this.getClass().getResource("imagenes/puntoamarillo.png"));
        }
        SetImagen(obj.getImage());
        return GetImagen();
    }
}
