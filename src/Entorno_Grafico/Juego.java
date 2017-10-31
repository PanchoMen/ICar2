/*
 * @author Pancho
 */

package Entorno_Grafico;

import Algoritmo_Busqueda.AEstrella;
import Algoritmo_Busqueda.Lista;
import Algoritmo_Busqueda.Nodo;
import Clases.Camino;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

public class Juego extends JFrame implements ActionListener, MouseListener{
    
    private Configuracion conf;
    private Tablero tablero;
    private Menu botton_menu;
    private AEstrella camino_min;
    
    private ArrayList<Camino> Manhattan;
    private ArrayList<Camino> Euclideo;
    private ArrayList<Camino> Mahalanobis;
    private ArrayList<Camino> Camino_Final;
    
    private boolean boton_coche_selec;
    private boolean boton_llegada_selec;
    private boolean boton_obstaculo_selec;
    
    private Dimension screenSize;
    
    private int filas;
    private int columnas;
    private int ancho;
    private int alto;
    private int escala;
    private boolean manual;
    private int n_obstaculos;
    
    private int[] estadisticas_movs;
    private long[] estadisticas_time;
    
    
    public Juego(int filas, int columnas, boolean manual, int n_obstaculos){
        this.filas = filas;
        this.columnas = columnas;
        this.manual = manual;
        this.n_obstaculos = n_obstaculos;
        this.boton_coche_selec = false;
        this.boton_llegada_selec = false;
        this.boton_obstaculo_selec = false;
        
        Manhattan = new ArrayList<Camino>();
        Euclideo = new ArrayList<Camino>();
        Mahalanobis = new ArrayList<Camino>();
        Camino_Final =  new ArrayList<Camino>();
        
        estadisticas_movs = new int[3];
        estadisticas_time = new long[3];
                
        Toolkit t = Toolkit.getDefaultToolkit();
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.escala = ((screenSize.height - 150) / (filas));
        
        getContentPane().setLayout(null);
        setTitle("ICar");
        setFocusable(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        tablero = new Tablero(filas, columnas, escala, manual, n_obstaculos);
        tablero.addMouseListener(this);
        botton_menu = new Menu(filas*escala, columnas*escala);
        this.add(tablero);
        this.add(botton_menu);
        changeSize();
        setVisible(true);
        addActionListener();
    }
    
    public void changeSize(){
        this.alto = (tablero.getHeight() + botton_menu.getHeight() + 20);
        this.ancho = (tablero.getHeight());
        this.setSize(ancho, alto);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botton_menu.Get_CocheBTN()){
            //insertar coche;
            ////System.out.println("insertar coche;");
            if(botton_menu.Get_CocheBTN().isSelected()){
                boton_coche_selec = true;
                boton_llegada_selec = false;
                boton_obstaculo_selec = false;
                
            }
        }else if(e.getSource() == botton_menu.Get_LlegadaBTN()){
            //insertar llegada;
            ////System.out.println("insertar llegada;");
            if(botton_menu.Get_LlegadaBTN().isSelected()){
                boton_coche_selec = false;
                boton_llegada_selec = true;
                boton_obstaculo_selec = false;
                
            }
        }else if(e.getSource() == botton_menu.Get_ObstaculoBTN()){
            //insertar obstaculo;
            ////System.out.println("insertar obstaculo;");
            if(botton_menu.Get_ObstaculoBTN().isSelected()){
                boton_coche_selec = false;
                boton_llegada_selec = false;
                boton_obstaculo_selec = true;
                
            }
        }else if(e.getSource() == botton_menu.Get_IniciarBTN()){
            //Iniciar simulación;
            ////System.out.println("INICIAR SIMULACION");
            Generar_Caminos();
            Mostrar_Camino(1);
            JOptionPane.showMessageDialog(null, "La Heuristica H(1) ha tardado " + estadisticas_time[0] + " millisegundos y ha analizado " + estadisticas_movs[0] + " nodos\n" + "La Heuristica H(2) ha tardado " + (estadisticas_time[1]) + " millisegundos y ha analizado " + estadisticas_movs[1] + " nodos\n" + "La Heuristica H(3) ha tardado " + estadisticas_time[2] + " millisegundos y ha analizado " + estadisticas_movs[2] + " nodos", "ESTADISTICAS", JOptionPane.INFORMATION_MESSAGE);
        }else if(e.getSource() == botton_menu.Get_VolverBTN()){
            //Volver al menú de configuración;
            //System.out.println("Volver al menú de configuración");
            conf = new Configuracion();
            conf.setVisible(true);
            dispose();
        }else if(e.getSource() == botton_menu.Get_ManhattanBTN()){
            //Mostrar el camino Manhattan;
            //System.out.println("Mostrando Manhantan");
            Mostrar_Camino(1);
        }else if(e.getSource() == botton_menu.Get_EuclideoBTN()){
            //Mostrar el camino Euclideo;
            //System.out.println("Mostrando Euclidea");
            Mostrar_Camino(2);
        }else if(e.getSource() == botton_menu.Get_MahalanobisBTN()){
            //Mostrar el camino Mahalanobis;
            //System.out.println("Mostrando Mahalanobis");
            Mostrar_Camino(3);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        int coordenada_x = e.getX(); // Obtenemos la coordenada X del ratón y la almacenamos en una variable.
	int coordenada_y = e.getY(); // Obtenemos la coordenada Y del ratón y la almacenamos en una variable.
	int position_x = (int)(coordenada_x / tablero.GetEscala());
	int position_y = (int)(coordenada_y / tablero.GetEscala());
        //System.out.println("Mouse pressed en: (" + coordenada_x + " ," + coordenada_y + ") Colocando objeto en: (" + position_x + " ," + position_y);
        if(boton_obstaculo_selec){
            if(tablero.GetObjeto(position_x,position_y) != null){
                if(tablero.GetObjeto(position_x,position_y).GetId() == 3){
                    tablero.SetObjeto(position_x,position_y, null);
                }
            }else{
                tablero.InsertarObstaculo(position_x, position_y);
            }
        }
        if(boton_coche_selec){
            if(tablero.GetObjeto(position_x,position_y) != null){
                if(tablero.GetObjeto(position_x,position_y).GetId() == 1){
                    tablero.SetObjeto(position_x,position_y, null);
                }
            }else{
                if (tablero.GetCoche().GetInsert()) {
                    //System.out.println("Ya hay un coche");
                    tablero.Eliminar(tablero.GetCoche());
                    tablero.InsertarCoche(position_x, position_y);
                }else{
                    tablero.InsertarCoche(position_x, position_y);
                }
            }
        }
        if(boton_llegada_selec){
            if(tablero.GetObjeto(position_x,position_y) != null){
                if(tablero.GetObjeto(position_x,position_y).GetId() == 2){
                    tablero.SetObjeto(position_x,position_y, null);
                }
            }else{
                if (tablero.GetLlegada().GetInsert()) {
                    //System.out.println("Ya hay una parada");
                    tablero.Eliminar(tablero.GetLlegada());
                    tablero.InsertarLlegada(position_x, position_y);
                }else{
                    tablero.InsertarLlegada(position_x, position_y);
                }
            }
        }
        repaint();
    }
    
    public void Generar_Caminos(){
        for(int heuristic = 1; heuristic <= 3; heuristic++){
            camino_min = new AEstrella(tablero, heuristic);

            Lista list_solution = null; // Variable de tipo list para mostrar los nodos de la solución. Inicializada en null.
            list_solution = camino_min.beggining(); // Llamada al método beggining() que nos devuelve la lista con los nodos del camino mínimo.

            if (list_solution == null) { // CONDICIÓN: Si list_solution devuelve null (no hay solución).
                JOptionPane.showMessageDialog(null, "NO EXISTE SOLUCIÓN"); // Mostramos una alerta por pantalla indicándolo.
                break;
            } else { // CONDICIÓN: Si list_solution nos devuelve un camino mínimo.
                while(list_solution.empty() == false) {
                    Nodo auxiliary_node = list_solution.extract(); // Llamada al método extract() para extraer el primer nodo de la lista.
                    int x = auxiliary_node.get_id_x(); // Llamada al método get_id_x() para almacenar en una variable local la x del nodo.
                    int y = auxiliary_node.get_id_y(); // Llamada al método get_id_y() para almacenar en una variable local la y del nodo.
                    Camino camino = new Camino(x, y, escala, heuristic);
                    switch (heuristic){
                        case 1:
                            this.Manhattan.add(camino);
                        break;

                        case 2:
                            this.Euclideo.add(camino);
                        break;

                        case 3:
                            this.Mahalanobis.add(camino);
                        break;
                    }
                }
            }
            estadisticas_movs[heuristic - 1] = camino_min.getMovs();
            estadisticas_time[heuristic - 1] = camino_min.getTime();
        }
    }
    
    public void Mostrar_Camino(int heuristica) { // Método run (propio de la clase Thread) para iniciar el algoritmo A*.
        switch (heuristica){
            case 1:
                this.Camino_Final = this.Manhattan;
                break;

            case 2:
                this.Camino_Final = this.Euclideo;
                break;

            case 3:
                this.Camino_Final = this.Mahalanobis;
                break;
        }
        repaint(); 
    }
    
    @Override
    public void paint(Graphics _g){
        super.paint(_g);
        Graphics2D g = (Graphics2D) _g;
        for(Camino cam : this.Camino_Final){
            g.drawImage(cam.GetImagen(), tablero.Escalar(cam.GetX()), tablero.Escalar(cam.GetY())+ 23, cam.GetAncho(), cam.GetAlto(), this);
        }
    }

    public void addActionListener() { // Método para añadir los Action Listener a los componentes relacionados con nuestra ventana.
        botton_menu.Get_CocheBTN().addActionListener(this);
        botton_menu.Get_LlegadaBTN().addActionListener(this);
        botton_menu.Get_ObstaculoBTN().addActionListener(this);
        botton_menu.Get_IniciarBTN().addActionListener(this);
        botton_menu.Get_VolverBTN().addActionListener(this);
        botton_menu.Get_ManhattanBTN().addActionListener(this);
        botton_menu.Get_EuclideoBTN().addActionListener(this);
        botton_menu.Get_MahalanobisBTN().addActionListener(this);
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
