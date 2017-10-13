/*
 * @author Pancho
 */

package Entorno_Grafico;

import Clases.Coche;
import Clases.Objeto;
import Clases.Obstaculo;
import Clases.Llegada;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.util.Random;

public class Tablero extends JPanel{
    
    private Objeto [][] matriz;
    private int escalax;
    private int escalay;
    private int x;
    private int y;
    
    public Tablero(int x, int y, int escalax, int escalay){
        matriz = new Objeto[x][y];
        this.escalax = escalax;
        this.escalay = escalay;
        this.x = x;
        this.y = y;
        this.GenerarObstaculos();
        this.InsertarCoche(0, 0);
        this.InsertarLlegada(5, 5); 
    }
    
    public Objeto [][] GetMatriz(){
        return matriz;
    }
    
    public int GetEscalaX(){
        return escalax;
    }
    
    public void SetEscalaX(int escalax){
        this.escalax = escalax;
    }
    
    public int GetEscalaY(){
        return escalay;
    }
    
    public void SetEscalaY(int escalay){
        this.escalay = escalay;
    }
    
    public int EscalarX(int num){
        return num * escalax;
    }
    
    public int EscalarY(int num){
        return num * escalay;
    }
    
    private void InsertarObjeto(Objeto obj, int x, int y){
        matriz[x][y] = obj;
    }
    
    public void InsertarCoche(int x, int y){
        Objeto coche = new Coche(EscalarX(x),EscalarY(y),GetEscalaX(),GetEscalaY());
        InsertarObjeto(coche,x,y);
    }
    
    public void InsertarObstaculo(int x, int y){
        Objeto coche = new Obstaculo(EscalarX(x),EscalarY(y),GetEscalaX(),GetEscalaY());
        InsertarObjeto(coche,x,y);
    }
    
    public void InsertarLlegada(int x, int y){
        Objeto coche = new Llegada(EscalarX(x),EscalarY(y),GetEscalaX(),GetEscalaY());
        InsertarObjeto(coche,x,y);
    }
    
    public void GenerarObstaculos(){
        Random  rnd = new Random();
        int numeroObstaculos = rnd.nextInt(this.x * this.y);
        for(int i = 0; i < numeroObstaculos; i++){
            int x = rnd.nextInt(this.x);
        int y = rnd.nextInt(this.y);
            this.InsertarObstaculo(x, y);
        }
        
    }
    
    @Override
    public void paint (Graphics _g){
        Graphics2D g = (Graphics2D) _g;
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz[0].length; j++){
                if(matriz[i][j] != null){
                    g.drawImage(matriz[i][j].GetImagen(), matriz[i][j].GetX(), matriz[i][j].GetY(), matriz[i][j].GetAncho(), matriz[i][j].GetAlto(), this);
                }
            }
        }
    }
}
