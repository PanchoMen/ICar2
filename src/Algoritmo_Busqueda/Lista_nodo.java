/*
 * @author Pancho
 */

package Algoritmo_Busqueda;

public class Lista_nodo {// Clase para establecer el contenido de los nodos y los métodos de acceso.

//-----> DECLARACIÓN DE LOS ATRIBUTOS DE LA CLASE.
	  private Nodo info; // 
	  private Lista_nodo next;

//-----> DECLARACIÓN DE LOS MÉTODOS DE LA CLASE.
	  
	  public Lista_nodo() { // Constructor por defecto.
	    next = null;
	  }

	  public Lista_nodo(Nodo o, Lista_nodo n) { // Constructor.
	    info = o;
	    setNext(n);
	  }
	  
	  //-----> MÉTODOS SETTERS AND GETTERS.

	  public void setNext(Lista_nodo n) { // Método para establecer el nodo siguiente.
	    next = n;
	  }

	  public Lista_nodo getNext() { // Método para obtener el nodo siguiente.
	    return next;
	  }

	  public void setInfo(Nodo i) { // Método para establecer la información del nodo.
	    info = i;
	  }
	  
	  public Nodo getInfo() { // Método para obtener la información del nodo.
	    return info;
	  }
}
