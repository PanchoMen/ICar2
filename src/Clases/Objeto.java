/*
 * @author Pancho
 */

package Clases;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Objeto {
    //Atributos de la clase
    private int x, y;                   //Variables para determinar la psición del objeto
    private int dx, dy;                 //Variables para determinar el movimiento del objeto
    private int ancho, alto;            //Determina el ancho y alto del objeto
    private Image imagen;               //Almacena la imagen correspondiente a cada objeto
    private int id;
    
    //Constructor
    public Objeto(int x, int y){
        id = 0;
        this.x = x;
        this.y = y;
        this.ancho = 40;
        this.alto = 40;
        SetImagen(GetDefault());
    }
    
    public int GetId(){
        return id;
    }
    
    public void SetId(int id){
        this.id = id;
    }
    
    public int GetAncho(){ 
        return ancho;
    }
    
    public void SetAncho(int ancho){
        this.ancho = ancho;
    }
    
    public int GetAlto(){
        return alto;
    }
    
    public void SetAlto(int alto){
        this.alto = alto;
    }
    
    public int GetDX(){
        return dx;
    }
    
    public void SetDX(int dx){
        this.dx = dx;
    }
    
    public int GetDY(){
        return dy;
    }
    
    public void SetDY(int dy){
        this.dy = dy;
    }
    
    public int GetX(){
        return x;
    }
    
    public void SetX(int x){
        this.x = x;
    }
    
    public int GetY(){

        return y;
    }
    
    public void SetY(int y){
        this.y = y;
    }
    
    public Image GetImagen(){
        return imagen;
    }
    
    public void SetImagen(Image imagen){
        this.imagen = imagen;
    }
    
    public void mover(){
        SetX(GetX() + GetDX());
        SetY(GetY() + GetDY());   
    }
    
    public Image GetDefault(){
        ImageIcon obj = new ImageIcon(this.getClass().getResource("imagenes/"));
        SetImagen(obj.getImage());
        return GetImagen();
    }
}
