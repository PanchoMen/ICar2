/**
 * @author Pancho
 */

package Entorno_Grafico;

import Clases.Coche;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

public class Menu extends JPanel {
    
    private ButtonGroup grupo_botones;
    private JToggleButton boton_coche;
    private JToggleButton boton_llegada;
    private JToggleButton boton_obstaculo;
    private JButton iniciar;
    private JButton volver;
    private ButtonGroup heuristicas;
    private JRadioButton btn_manhattan;
    private JRadioButton btn_euclideo;
    private JRadioButton btn_mahalanobis;
    
    private int y;
    private int largo;
    private int ancho;
    
    private Coche coche;
    
    public Menu(int inicio, int largo){
        super();
        this.y = inicio;
        this.largo = largo;
        this.ancho = 90;
        
        coche = new Coche(0,0,60);
        
        grupo_botones = new ButtonGroup();
        
        boton_coche = new JToggleButton("Coche");
        //boton_coche.setIcon(new ImageIcon("../Clases/imagenes/coche.png"));
        grupo_botones.add(boton_coche);
        add(boton_coche);
        
        boton_llegada = new JToggleButton("Llegada");
        //boton_llegada.setIcon(new ImageIcon("images/prohibido.png"));
        grupo_botones.add(boton_llegada);
        add(boton_llegada);
        
        boton_obstaculo = new JToggleButton("Obstaculo");
        //boton_obstaculo.setIcon(new ImageIcon(this.getClass().getResource("coche.png")));
        grupo_botones.add(boton_obstaculo);
        add(boton_obstaculo);
        
        iniciar = new JButton("Iniciar");
        add(iniciar);
        volver= new JButton("Volver");
        add(volver);
        
        heuristicas = new ButtonGroup();
        btn_manhattan = new JRadioButton("Manhattan");
        heuristicas.add(btn_manhattan);
        btn_manhattan.setSelected(true);
        add(btn_manhattan);
        btn_euclideo = new JRadioButton("Euclidea");
        heuristicas.add(btn_euclideo);
        add(btn_euclideo);
        btn_mahalanobis = new JRadioButton("Mahalanobis");
        heuristicas.add(btn_mahalanobis);
        add(btn_mahalanobis);
        
        this.setLayout(null); // Desactivamos el gestor de Layout para hacerlo manualmente.
        Generar_Menu();
        this.setBounds(0,this.y,this.largo,this.ancho); // Establecemos unas coordenadas y un tamaño a nuestro buttonpanel (Layout desactivado en el mainframe).
        this.setBackground(new Color(182,180,180)); // Añadimos un color gris oscuro (puntos RGB) a nuestro panel.
        this.setVisible(true);
    }
    
    public JToggleButton Get_CocheBTN(){
        return this.boton_coche;
    }
    
    public JToggleButton Get_LlegadaBTN(){
        return this.boton_llegada;
    }
    
    public JToggleButton Get_ObstaculoBTN(){
        return this.boton_obstaculo;
    }
    
    public JButton Get_IniciarBTN(){
        return this.iniciar;
    }
    
    public JButton Get_VolverBTN(){
        return this.volver;
    }
    
    public JRadioButton Get_ManhattanBTN(){
        return this.btn_manhattan;
    }
    
    public JRadioButton Get_EuclideoBTN(){
        return this.btn_euclideo;
    }
    
    public JRadioButton Get_MahalanobisBTN(){
        return this.btn_mahalanobis;
    }
    
    public void Generar_Menu(){
        boton_coche.setBounds(10,8,80,60);
        boton_llegada.setBounds(92,8,80,60);
        boton_obstaculo.setBounds(174,8,80,60);
        iniciar.setBounds(290,12,100,50);
        btn_manhattan.setBounds(402,5,120,20);
        btn_euclideo.setBounds(402,27,120,20);
        btn_mahalanobis.setBounds(402,49,120,20);
        volver.setBounds(530,12,100,50);
        boton_llegada.setIcon(new ImageIcon("prohibido.png"));
    }
    
}
