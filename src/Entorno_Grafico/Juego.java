/*
 * @author Pancho
 */

package Entorno_Grafico;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Juego extends JFrame{
    
    Tablero tablero;
    Configuracion configuracion;
    int ancho;
    int alto;
    int escalax;
    int escalay;
    
    
    public Juego(int filas, int columnas, int escalax, int escalay){
        this.ancho = filas;
        this.alto = columnas;
        this.escalax = escalax;
        this.escalay = escalay;
        
        setTitle("ICar");
        setVisible(true);
        setFocusable(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        tablero = new Tablero(ancho,alto,escalax,escalay);
        add(tablero);
    }
    
    public void changeSize(Dimension screenSize){
        this.ancho = screenSize.width;
        this.alto = screenSize.height - 180;
        setSize(ancho,alto);
    }
}
