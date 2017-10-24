/*
 * @author Pancho
 */

package Entorno_Grafico;

import Algoritmo_Busqueda.AEstrella;
import Algoritmo_Busqueda.Lista;
import Algoritmo_Busqueda.Nodo;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

public class Juego extends JFrame implements ActionListener, MouseListener{
    
    private Configuracion conf;
    private Tablero tablero;
    private AEstrella camino_min;
    
    private JMenuBar menu;
    private ButtonGroup grupo_botones;
    private ButtonGroup grupo_botones2;
    private JToggleButton boton_coche;
    private JToggleButton boton_llegada;
    private JToggleButton boton_obstaculo;
    private JButton iniciar;
    private JButton volver;
    
    private boolean boton_coche_selec;
    private boolean boton_llegada_selec;
    private boolean boton_obstaculo_selec;
    
    private Dimension screenSize;
    private int tam_menu;
    
    private int filas;
    private int columnas;
    private int ancho;
    private int alto;
    private int escala;
    private boolean manual;
    private int n_obstaculos;
    
    
    public Juego(int filas, int columnas, boolean manual, int n_obstaculos){
        this.filas = filas;
        this.columnas = columnas;
        this.manual = manual;
        this.n_obstaculos = n_obstaculos;
        this.boton_coche_selec = false;
        this.boton_llegada_selec = false;
        this.boton_obstaculo_selec = false;
        this.tam_menu = 30;
        
        Toolkit t = Toolkit.getDefaultToolkit();
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.escala = ((screenSize.height - 150) / (filas));
        
        setTitle("ICar");
        setFocusable(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addMouseListener(this);
        
        menu = new JMenuBar();
        setJMenuBar(menu);
        grupo_botones = new ButtonGroup();
        menu.setLayout(new FlowLayout());
        
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
        
        iniciar = new JButton("Iniciar Simulación");
        menu.add(iniciar);
        iniciar.addActionListener(this);
        volver= new JButton("Volver al menú");
        menu.add(volver);
        volver.addActionListener(this);
        
        tablero = new Tablero(filas, columnas, escala, manual, n_obstaculos);
        add(tablero);
        changeSize();
        setVisible(true);
    }
    
    public void changeSize(){
        this.alto = ((filas * escala) + tam_menu + 23);
        this.ancho = (columnas * escala);
        this.setSize(ancho, alto);
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
        }else if(e.getSource() == iniciar){
            //Iniciar simulación;
            System.out.println("INICIAR SIMULACION");
            camino_min = new AEstrella(tablero);
            Buscar_Camino();
        }else if(e.getSource() == volver){
            //Volver al menú de configuración;
            System.out.println("Volver al menú de configuración");
            conf = new Configuracion();
            conf.setVisible(true);
            dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse pressed en: (" + e.getX() + " ," + (e.getY()) + ") Tamaño menu: " + menu.getHeight());
        int coordenada_x = e.getX(); // Obtenemos la coordenada X del ratón y la almacenamos en una variable.
	int coordenada_y = e.getY() - menu.getHeight() - 23; // Obtenemos la coordenada Y del ratón y la almacenamos en una variable.
	int position_x = (int)(coordenada_x/tablero.GetEscala());
	int position_y = (int)(coordenada_y/tablero.GetEscala());
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
                    System.out.println("Ya hay un coche");
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
    
    public void Buscar_Camino() { // Método run (propio de la clase Thread) para iniciar el algoritmo A*.

        Lista list_solution = null; // Variable de tipo list para mostrar los nodos de la solución. Inicializada en null.
        list_solution = camino_min.beggining(); // Llamada al método beggining() que nos devuelve la lista con los nodos del camino mínimo.
        int value_path = 0; // Variable para almacenar el elemento que se mostrará en el camino. Inicializada a 0.

        if (list_solution == null) { // CONDICIÓN: Si list_solution devuelve null (no hay solución).
            JOptionPane.showMessageDialog(null, "NO EXISTE SOLUCIÓN"); // Mostramos una alerta por pantalla indicándolo.
        } else { // CONDICIÓN: Si list_solution nos devuelve un camino mínimo.
            JOptionPane.showMessageDialog(null, "SOLUCIÓN ENCONTRADA");
            /*while (list_solution.empty() == false) { // Mientras la list_solution no esté vacía.
                Nodo auxiliary_node = list_solution.extract(); // Llamada al método extract() para extraer el primer nodo de la lista.
                int x = auxiliary_node.get_id_x(); // Llamada al método get_id_x() para almacenar en una variable local la x del nodo.
                int y = auxiliary_node.get_id_y(); // Llamada al método get_id_y() para almacenar en una variable local la y del nodo.

                for (int i = 0; i < tablero.GetFilas(); i++) {
                    for (int j = 0; j < tablero.GetColumnas(); j++) {
                        if ((tablero.GetObjeto(i, j) >= 80) && (tablero.GetObjeto(i, j) < 90)) { // CONDICIÓN: Para cada posición de la matriz que sea igual o mayor a 80 (spaceman).
                            if ((x < i) && (y == j)) { // CONDICIÓN: Si se produce un movimiento vertical ascendente.
                                tablero.SetObjeto(i, j, value_path); // Situamos en su lugar un elemento espacio.
                                tablero.SetObjeto(x, y, 81); // Establecemos en la posición (x,y) dada por la lista solución un 81 (spaceman).
                            }
                            if ((x > i) && (y == j)) { // CONDICIÓN: Si se produce un movimiento vertical descendente.
                                tablero.SetObjeto(i, j, value_path); // Situamos en su lugar un elemento espacio.
                                tablero.SetObjeto(x, y, 82); // Establecemos en la posición (x,y) dada por la lista solución un 82 (spaceman).
                            }
                            if ((x == i) && (y > j)) { // CONDICIÓN: Si se produce un movimiento horizontal ascendente.
                                tablero.SetObjeto(i, j, value_path); // Situamos en su lugar un elemento espacio.
                                tablero.SetObjeto(x, y, 83); // Establecemos en la posición (x,y) dada por la lista solución un 83 (spaceman).
                            }
                            if ((x == i) && (y < j)) { // CONDICIÓN: Si se produce un movimiento horizontal descendente.
                                tablero.SetObjeto(i, j, value_path); // Situamos en su lugar un elemento espacio.
                                tablero.SetObjeto(x, y, 84); // Establecemos en la posición (x,y) dada por la lista solución un 84 (spaceman).
                            }
                            if ((x < i) && (y > j)) { // CONDICIÓN: Si se produce un movimiento diagonal derecho ascendente.
                                tablero.SetObjeto(i, j, value_path); // Situamos en su lugar un elemento espacio.
                                tablero.SetObjeto(x, y, 85); // Establecemos en la posición (x,y) dada por la lista solución un 85 (spaceman).
                            }
                            if ((x > i) && (y > j)) { // CONDICIÓN: Si se produce un movimiento diagonal derecho descendente.
                                tablero.SetObjeto(i, j, value_path); // Situamos en su lugar un elemento espacio.
                                tablero.SetObjeto(x, y, 86); // Establecemos en la posición (x,y) dada por la lista solución un 87 (spaceman).
                            }
                            if ((x < i) && (y < j)) {// CONDICIÓN: Si se produce un movimiento diagonal izquierdo ascendente.
                                tablero.SetObjeto(i, j, value_path); // Situamos en su lugar un elemento espacio.
                                tablero.SetObjeto(x, y, 87); // Establecemos en la posición (x,y) dada por la lista solución un 86 (spaceman).
                            }
                            if ((x > i) && (y < j)) {// CONDICIÓN: Si se produce un movimiento diagonal izquierdo descendente.
                                tablero.SetObjeto(i, j, value_path); // Situamos en su lugar un elemento espacio.
                                tablero.SetObjeto(x, y, 88); // Establecemos en la posición (x,y) dada por la lista solución un 88 (spaceman).
                            }
                        }
                    }
                }
                tablero.repaint(); // Llamada al método repaint() para hacer un nuevo paint de nuestro matrixpanel.
            }*/
        }
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
