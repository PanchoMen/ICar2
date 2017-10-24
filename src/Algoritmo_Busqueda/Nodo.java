/*
 * @author Pancho
 */

package Algoritmo_Busqueda;


public class Nodo {// Clase para la creación de los nodos que usaremos en la lista.

//-----> DECLARACIÓN DE LOS ATRIBUTOS DE LA CLASE.
	
	// Variables identificadores que indican la posición del nodo en la matriz.
	private int id_x;
	private int id_y;
	
	// Variables de la funcion f = g + h.
	private int f;
	private int g;
	private int h;
	
	private Nodo father;  // Variable father que nos indica el nodo previo para llegar al actual.
	private boolean obstacle; // Variable para detectar un obstacle.
	  
//-----> DECLARACIÓN DE LOS MÉTODOS DE LA CLASE.
	
	public Nodo() { // Constructor por defecto.
		
		// Inicialización de los atributos.
	    id_x = 0;
	    id_y = 0;
	    f = 0;
	    g = 0;
	    h = 0;
	}
	
	public Nodo(int x, int y, boolean s) { // Constructor.
		
		// Inicialización de los atributos.	
		set_id_x(x); // Llamada al método set_id_x() para establecer el valor de id_x.
	    set_id_y(y); // Llamada al método set_id_y() para establecer el valor de id_y.
	    obstacle = s; // Iniciamos la variable obstacle al valor pasado por parámetro.
	    f = 0;
	    g = 0;
	    h = 0;
	}

	//-----> MÉTODOS GETTERS AND SETTERS.
	
	public void set_id_x(int x) {
		id_x = x;
	}
	
	public void set_id_y(int y) {
		id_y = y;
	}
	
	public void set_f(int f_) {
	    f = f_;
	}
	
	public void set_g(int g_) {
	    g = g_;
	}
	
	public void set_h(int h_) {
	    h = h_;
	}
	
	public void set_father(Nodo pad) {
	    father = pad;
	}
	
	public void set_obstacle(boolean obs) {
	    obstacle = obs;
	}

	public int get_id_x() {
		return id_x;
	}

	public int get_id_y() {
	    return id_y;
	}

	public int get_f() {
	    return f;
	}

	public int get_g() {
	    return g;
	}

	public int get_h() {
	    return h;
	}

	public Nodo get_father() {
	    return father;
	}

    public boolean get_obstacle() {
	    return obstacle;
	}

	public void calculate_f() { // Método para calcular la f de la función teniendo la g y la h.
	    f = g + h;
	}

	//----> SOBRECARGA DE OPERADORES.

	public boolean equal(Nodo a) { // Método para la sobrecarga del operador ==.

		if(a.get_id_x() == this.get_id_x() && a.get_id_y() == this.get_id_y()) {
			return true;
		}
		else {
			return false;
		}
	}
}
