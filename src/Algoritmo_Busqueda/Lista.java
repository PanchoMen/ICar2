/*
 * @author Pancho
 */

package Algoritmo_Busqueda;


public class Lista {// Clase para la creación de una Lista de nodos.

//-----> DECLARACIÓN DE LOS ATRIBUTOS DE LA CLASE.
	
	// Inicialización de los atributos.
	public Lista_nodo first; // Atributo para crear una referencia al primer nodo de la Lista. 

	  // a Lista is created in an empty state
	  public Lista() { // Constructor.

	    first = null; // Inicializamos la Lista en un estado vacío.
	  }

	  public void insert(Nodo o) { // Método para insertar nodos en la Lista.

	  	Lista_nodo nuevo = new Lista_nodo(o, first); // Creamos un nuevo nodo que tendrá como referencia al first anterior.
	    first = nuevo; // Establecemos el nuevo first.
	  }

	  public Nodo extract() { // Método para extraer un nodo de la Lista.

	    Nodo out = null; // Creamos una variable local en la que almacenaremos el nodo que saquemos.

	    if (empty() == false) { // CONDICIÓN: Si en la Lista hay algún nodo (first != null).
	      out = first.getInfo(); // Almacenamos en out el nodo a extraer.
	      first = first.getNext(); // El first de dicho nodo, será ahora el first del next, ya que este nodo se extrae.
	    }
	    
	    return out; // Retornamos el nodo extraído. Si la Lista está vacía, se retorna null.
	  }

	  public boolean empty() { // Método boleano para comprobar si la Lista está vacía.
	    if (first == null) { // CONDICIÓN: Si el first es igual a null (no hay ningún nodo en la Lista).
	      return true; // Retornamos true.
	    }
	    else {
	      return false; // Retornamos false. 
	    }
	  }

	  public int lenght() { // Método que devuelve la cantidad de nodos de la Lista.

	    int contador = 0; // Variable local para ir contando el número de nodos.
	    Lista_nodo nodoauxiliarity = first; // Variable local para almacenar un nodo auxiliarityiliar que será el nodo de comienzo.

	    while(nodoauxiliarity != null) { // Mientras la referecencia al nodo auxiliarityiliar no sea igual a null (fin de la Lista).
	      contador++; // Aumentamos en uno la variable contador.
	      nodoauxiliarity = nodoauxiliarity.getNext(); // Establecemos el nodo auxiliarityiliar como el next nodo.
	    }

	    return contador; // Retornamos la variable contador.
	  }

	  public void order() { // Método para implementar el algoritmo de la burbuja y ordenar la Lista.
		  
	  	Lista_nodo actual; // Variable local para el primer nodo de la comparación.
	  	Lista_nodo next; // Variable local para el segundo nodo de la comparación.
	  	Nodo auxiliarity; // Variable local de un nodo auxiliarityiliar usado en la comparación.
	  	actual = first; // Inicializamos el nodo actual en el principio de la Lista.

	  	while(actual != null) { // Mientras el nodo actual no sea igual a null.
	  		next = actual.getNext(); // Almacenamos en siguente el nodo situado justo después del actual.
	  		while(next != null) { // Mientras el nodo next no sea igual a null.
	  			if(actual.getInfo().get_f() > next.getInfo().get_f()) { // CONDICIÓN: Si el valor del nodo actual es mayor que el del nodo next.
	  				auxiliarity = actual.getInfo(); // Almacenamos en el nodo auxiliarityiliar el valor del next para conservarla.
	  				actual.setInfo(next.getInfo()); // Establecemos como valor del nodo actual la información del nodo next.
	  				next.setInfo(auxiliarity); // Establecemos como valor del nodo next la información del auxiliarityiliar (anteriormente la de actual).
	  			}
	  		next = next.getNext(); // Establecemos como al nodo next que está justo después del next usado en el if.
	  		}
	  		actual = actual.getNext(); // Establecemos como nodo actual al que está justo después del actual usado.
	  	}
	  }

	  public boolean find(Nodo nodo){ // Método boleano para encontrar un nodo dentro de la Lista.

	  	Lista_nodo Nodo_actual = first; // Variable local que almacenará el nodo actual. Iniciada en el primer nodo.

	  	while (Nodo_actual != null){ // Mientras el nodo actual no sea igual a null.
	  		if(Nodo_actual.getInfo().equal(nodo)) { // Llamada al método es_igual(), que comprueba si la información de los nodos coinciden.
	  			return true; // Retornamos true para establecer que se ha encontrado el nodo en la Lista.
	  		}
	  		else {
	  			Nodo_actual= Nodo_actual.getNext(); // Establecemos como nodo actual al que esta justo después.
	  		}
	  	}
	  	return false; // Si terminamos de recorrer la Lista y no encontramos ningún nodo coincidente, retornamos false.
	  }
}
