/*
 * @author Pancho
 */

package Entorno_Grafico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JToggleButton;

public class Juego extends JFrame implements ActionListener, MouseListener{
    
    private Tablero tablero;
    
    private JMenuBar menu;
    private ButtonGroup grupo_botones;
    private JToggleButton boton_coche;
    private JToggleButton boton_llegada;
    private JToggleButton boton_obstaculo;
    
    private boolean boton_coche_selec;
    private boolean boton_llegada_selec;
    private boolean boton_obstaculo_selec;
    
    
    private int filas;
    private int columnas;
    private int ancho;
    private int alto;
    private int escalax;
    private int escalay;
    private boolean manual;
    private int n_obstaculos;
    
    
    public Juego(int filas, int columnas, boolean manual, int n_obstaculos){
        this.filas = filas;
        this.columnas = columnas;
        this.escalax = 40;
        this.escalay = 40;
        this.manual = manual;
        this.n_obstaculos = n_obstaculos;
        this.boton_coche_selec = false;
        this.boton_llegada_selec = false;
        this.boton_obstaculo_selec = false;
        
        setTitle("ICar");
        setVisible(true);
        setFocusable(true);
        setResizable(false);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseListener(this);
        
        //scroll = new JScrollPane(this.tablero, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        menu = new JMenuBar();
        setJMenuBar(menu);
        grupo_botones = new ButtonGroup();
        
        boton_coche = new JToggleButton("Coche");
        //boton_coche.setIcon(new ImageIcon())
        grupo_botones.add(boton_coche);
        menu.add(boton_coche);
        boton_coche.addActionListener(this);
        
        boton_llegada = new JToggleButton("Parada");
        //boton_parada.setIcon(new ImageIcon())
        grupo_botones.add(boton_llegada);
        menu.add(boton_llegada);
        boton_llegada.addActionListener(this);
        
        boton_obstaculo = new JToggleButton("Obstaculo");
        //boton_coche.setIcon(new ImageIcon())
        grupo_botones.add(boton_obstaculo);
        menu.add(boton_obstaculo);
        boton_obstaculo.addActionListener(this);
        
        tablero = new Tablero(filas, columnas, escalax, manual, n_obstaculos);
        add(tablero);
    }
    
    public void changeSize(){
        this.ancho = this.columnas * tablero.GetEscala();
        this.alto = ((this.filas + 1) * tablero.GetEscala()) + 10;
        setBounds(100, 100, ancho, alto);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == boton_coche){
            //insertar coche;
            System.out.println("insertar coche;");
            if(boton_coche.isSelected()){
                boton_coche_selec = true;
                boton_llegada_selec = false;
                boton_obstaculo_selec = false;
                
            }
        }else if(e.getSource() == boton_llegada){
            //insertar llegada;
            System.out.println("insertar llegada;");
            if(boton_llegada.isSelected()){
                boton_coche_selec = false;
                boton_llegada_selec = true;
                boton_obstaculo_selec = false;
                
            }
        }else if(e.getSource() == boton_obstaculo){
            //insertar obstaculo;
            System.out.println("insertar obstaculo;");
            if(boton_obstaculo.isSelected()){
                boton_coche_selec = false;
                boton_llegada_selec = false;
                boton_obstaculo_selec = true;
                
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse pressed en: (" + e.getX() + " ," + (e.getY() - menu.getHeight() - 23) + ")");
        int coordenada_x = e.getX(); // Obtenemos la coordenada X del ratón y la almacenamos en una variable.
	int coordenada_y = e.getY() - menu.getHeight() - 23; // Obtenemos la coordenada Y del ratón y la almacenamos en una variable.
	int position_x = (int)(coordenada_x/tablero.GetEscala());
	int position_y = (int)(coordenada_y/tablero.GetEscala());
        if(boton_obstaculo_selec){
            if(tablero.GetMatriz(position_x,position_y) != null){
                if(tablero.GetMatriz(position_x,position_y).GetId() == 3){
                    tablero.SetMatriz(position_x,position_y, null);
                }
            }else{
                tablero.InsertarObstaculo(position_x, position_y);
            }
        }
        if(boton_coche_selec){
            if(tablero.GetMatriz(position_x,position_y) != null){
                if(tablero.GetMatriz(position_x,position_y).GetId() == 1){
                    tablero.SetMatriz(position_x,position_y, null);
                }
            }else{
                if (tablero.GetCoche().GetInsert()) {
                    System.out.println("Ya hay un coche");
                    tablero.Eliminar(tablero.GetCoche());
                    tablero.InsertarCoche(position_x, position_y);
                }else{
                    tablero.InsertarCoche(position_x, position_y);
                }
            }
        }
        if(boton_llegada_selec){
            if(tablero.GetMatriz(position_x,position_y) != null){
                if(tablero.GetMatriz(position_x,position_y).GetId() == 2){
                    tablero.SetMatriz(position_x,position_y, null);
                }
            }else{
                if (tablero.GetLlegada().GetInsert()) {
                    System.out.println("Ya hay una parada");
                    tablero.Eliminar(tablero.GetLlegada());
                    tablero.InsertarLlegada(position_x, position_y);
                }else{
                    tablero.InsertarLlegada(position_x, position_y);
                }
            }
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
