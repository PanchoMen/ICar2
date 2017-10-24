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
    
    private Llegada llegada;
    private Coche coche;
    
    private Objeto [][] matriz;
    private int escala;
    private int filas;
    private int columnas;
    private boolean manual;
    private int n_obstaculos;
    
    public Tablero(int x, int y, int escala, boolean manual, int n_obstaculos){
        matriz = new Objeto[x][y];
        this.escala = escala;
        this.filas = x;
        this.columnas = y;
        this.manual = manual;
        this.n_obstaculos = n_obstaculos;
        this.coche = new Coche(0, 0, escala);
        this.llegada = new Llegada(0, 0, escala);
        
        //Si el modo seleccionado es aleatorio, se rellena el tablero con el número de obstaculos indicados
        if(!manual){
            this.GenerarObstaculos();
        }
    }
    
    public Objeto GetObjeto(int x, int y){
        return matriz[x][y];
    }
    
    public void SetObjeto(int x, int y, Objeto obj){
        matriz[x][y] = obj;
    }
    
    public int GetColumnas(){
        return columnas;
    }
    
    public int GetFilas(){
        return filas;
    }
    
    public Coche GetCoche(){
        return coche;
    }
    
    public Llegada GetLlegada(){
        return llegada;
    }
    
    public void Eliminar(Objeto obj){
        matriz[obj.GetX()][obj.GetY()] = null;
    }
    
    public int GetEscala(){
        return escala;
    }
    
    public void SetEscala(int escala){
        this.escala = escala;
    } 
    
    public int Escalar(int num){
        return num * escala;
    }
    private void InsertarObjeto(Objeto obj, int x, int y){
        matriz[x][y] = obj;
    }
    
    public void InsertarCoche(int x, int y){
        coche = new Coche(x, y, escala);
        InsertarObjeto(coche,x,y);
        coche.SetInsert(true);
    }
    
    public void InsertarObstaculo(int x, int y){
        Obstaculo obstaculo = new Obstaculo(x, y, escala);
        InsertarObjeto(obstaculo,x,y);
    }
    
    public void InsertarLlegada(int x, int y){
        llegada = new Llegada(x, y, escala);
        InsertarObjeto(llegada,x,y);
        llegada.SetInsert(true);
    }
    
    public void GenerarObstaculos(){
        Random  rnd = new Random();
        for(int i = 0; i < n_obstaculos; i++){
            int x = rnd.nextInt(this.filas);
            int y = rnd.nextInt(this.columnas);
            if(matriz[x][y] != null){
                i--;
            }
            this.InsertarObstaculo(x, y);
        }
        
    }
    
    @Override
    public void paint (Graphics _g){
        Graphics2D g = (Graphics2D) _g;
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j< matriz[0].length; j++){
                if(matriz[i][j] != null){
                    g.drawImage(matriz[i][j].GetImagen(), Escalar(matriz[i][j].GetX()), Escalar(matriz[i][j].GetY()), matriz[i][j].GetAncho(), matriz[i][j].GetAlto(), this);
                }
            }
        }
    }
}
