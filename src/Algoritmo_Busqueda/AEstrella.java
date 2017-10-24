/*
 * @author Pancho
 */

package Algoritmo_Busqueda;

import Entorno_Grafico.Tablero;


public class AEstrella {
    //-----> DECLARACIÓN DE LOS ATRIBUTOS DE LA CLASE.

    private Lista list_open;
    private Lista list_close;
    private Lista list_final;
    private Tablero dashboard;

    private int origin_x; // Creamos los nodos de origen para desarrollar el primer paso del algoritmo.
    private int origin_y;  // Creamos los nodos de origen para desarrollar el primer paso del algoritmo.
    private int destiny_x; // Creamos los nodos de destino para comprobar que hemos llegado al destino cada vez que tomamos un nodo adyacente.
    private int destiny_y; // Creamos los nodos de origen para desarrollar el primer paso del algoritmo.

    private boolean fin; // Variable boleana para establecer cuando llegamos al destino.
    private int value_g; // Variable para almacenar el valor g del nodo en el que estamos actualmente. 

//-----> DECLARACIÓN DE LOS MÉTODOS DE LA CLASE.
    public AEstrella(Tablero game_board) { // Constructor.

        // Inicialización de las variables.
        list_open = new Lista();
        list_close = new Lista();
        list_final = new Lista();
        dashboard = game_board; // Inicializamos el atributo dashboard con nuestra matriz pasada por parámetro.

        for (int i = 0; i < dashboard.GetFilas(); i++) {
            for (int j = 0; j < dashboard.GetColumnas(); j++) {
                if (dashboard.GetObjeto(i, j) != null) {
                    if (dashboard.GetObjeto(i, j).GetId() == 1) { // CONDICÍON: Si el valor de uno de los elementos de la matriz se corresponde con el spaceman.
                        origin_x = i; // Inicializamos la posición del origen_x como la fila del mismo.
                        origin_y = j; // Inicializamos la posición del origen_y como la columna del mismo.
                    }
                    if (dashboard.GetObjeto(i, j).GetId() == 2) { // CONDICÍON: Si el valor de uno de los elementos de la matriz se corresponde con el ironmonger.
                        destiny_x = i; // Inicializamos la posición del destino_x como la fila del mismo.
                        destiny_y = j; // Inicializamos la posición del destino_y como la columna del mismo.
                    }
                }
            }
        }
        value_g = 0; // Inicializamos a 0 el valor de g.
        fin = false; // Inicializamos la variable fin a false.	
    }

    public void try_node(int i, int j, boolean direction, Nodo father) { // Método para ir comprobando los nodos, recibiendo las coordenadas y si el movimiento es diagonal.
        if (fin == false) { // CONDICIÓN: Si no se ha llegado al final.
            if (dashboard.GetObjeto(i, j) != null) {
                if (dashboard.GetObjeto(i, j).GetId() == 2) { // CONDICIÓN: Si el nodo es el destino, acabamos.
                    fin = true;
                    return;
                }
                if (dashboard.GetObjeto(i, j).GetId() == 3) { // CONDICIÓN: Si el nodo es un obstacle, lo ignoramos.
                    return;
                }
            }

            Nodo node = new Nodo(i, j, false); // Si no se dan las condiciones anteriores, creamos una variable local con el nodo que vamos a tratar.

            if (list_close.find(node)) { // CONDICIÓN: Si encuentra dicho nodo en la lista cerrada (nodo por el que ya se ha pasado), lo ignoramos.
                return;
            }

            node.set_father(father); // Establecemos el nodo father (anterior) del nodo que estamos tratanto. Será el nodo pasado por parámetro inicialmente.

            //-----> CÁLCULO DE LA FUNCIÓN f = g + h.
            // 1º CALCULAR LA H DE LA FUNCIÓN. Haremos uso de la Distancia Manhattan.
            int i_manhattan = 0; // Variable local para almacenar la i manhattan. Inicializada en 0.
            int j_manhattan = 0; // Variable local para almacenar la j manhattan. Inicializada en 0.

            if (i > destiny_x) { // CONDICIÓN: Si la i pasada por parámetro es mayor que el destino.
                i_manhattan = i - destiny_x;
            } else {
                i_manhattan = destiny_x - i;
            }
            if (j > destiny_y) { // CONDICIÓN: Si la j pasada por parámetro es mayor que el destino.
                j_manhattan = j - destiny_y;
            } else {
                j_manhattan = destiny_y - j;
            }

            node.set_h((i_manhattan + j_manhattan) * 10); // Establecemos la h de la función en el nodo.

            // 2º CALCULAR LA G DEL SUCESOR.
            if (direction == false) { // CONDICIÓN: Si la dirección pasada por parámetro es false (no es diagonal).
                node.set_g(value_g + 10);
            } else {
                node.set_g(value_g + 14);
            }

            // 3º TENIENDO H Y G, PODEMOS CALCULAR F.
            node.calculate_f();

            // 4º ÚLTIMO PASO: Si el nodo está en la lista abierta, comprobamos si esa nueva g es mejor a la actual.
            // Si es asi, recalculamos factores y ponemos como father a la lista extraida. Si no esta, ignoramos.
            // Para el resto de nodos, recalculamos valores y los metemos en la lista abierta.
            if (list_open.find(node) == true) { // CONDICIÓN: Si el nodo está en la lista abierta.
                boolean found = false; // Variable local para indicar si se ha encontrado el nodo. Inicializada a false.
                Lista_nodo node_actual = list_open.first; // Almacenamos en una variable local el nodo actual en que nos encontramos.

                while (found != true) { // Mientras found no sea igual a true.
                    if (node_actual.getInfo().equal(node)) {
                        found = true;
                    } else {
                        node_actual = node_actual.getNext();
                    }
                }
                // Una vez que lo encontramos comprobamos el valor de su g.
                if (node_actual.getInfo().get_g() > node.get_g()) { // CONDICIÓN: Si es menor que la que tenemos en la cola, actualizamos y recalculamos f.
                    node_actual.getInfo().set_g(node.get_g());
                    node_actual.getInfo().set_father(father);
                    node_actual.getInfo().calculate_f();
                }
            } else { // CONDICIÓN: Si no esta en la lista abierta, simplemente lo insertamos en esta.
                list_open.insert(node);
            }
        }
    }

    public Lista beggining() { // Método que comienza a ejecutar el algoritmo.

        Nodo origin = new Nodo(origin_x, origin_y, false);
        Nodo intermediate = null;
        list_open.insert(origin);

        while (fin != true) { // Mientras no se llegue al final, realizamos un pop de la lista abierta y un push de la lista cerrada.

            if (list_open.empty() == true) { // CONDICIÓN: Si list_open no contiene ningún nodo (sin solución) devolvemos null.
                fin = true;
                return null;
            }
            intermediate = list_open.extract();
            list_close.insert(intermediate);
            value_g = intermediate.get_g();

            // Guardamos las coordenadas del nodo insertado en la lista cerrada.
            int i = intermediate.get_id_x();
            int j = intermediate.get_id_y();

            //-----> ANALISIS DE LOS NODOS VECINOS. Llevamos a cabo un control de los nodos vecino
            if ((i != 0) && (j != 0) && (i != dashboard.GetFilas() - 1) && (j != dashboard.GetColumnas() - 1)) { // CONDICIÓN: Si los nodos no pertenecen a los bordes.
                try_node(i, j + 1, false, intermediate); // Tratamos el nodo de la derecha.
                try_node(i + 1, j, false, intermediate); // Tratamos el nodo inferior.
                try_node(i, j - 1, false, intermediate); // Tratamos el nodo de la izquierda.
                try_node(i - 1, j, false, intermediate); // Tratamos el nodo superior.
                try_node(i + 1, j + 1, true, intermediate); // Tratamos el nodo de la diagonal inferior derecha.
                try_node(i + 1, j - 1, true, intermediate); // Tratamos el nodo de la diagonal inferior izquierda.
                try_node(i - 1, j - 1, true, intermediate); // Tratamos el nodo de la diagonal superior izquierda.
                try_node(i - 1, j + 1, true, intermediate); // Tratamos el nodo de la diagonal superior derecha.
            } else if (i == 0) { // CONDICIÓN: Si el nodo se encuentra en el borde superior.
                if (j == 0) { // CONDICIÓN: Y está en la esquina superior izquierda del tablero.
                    try_node(i + 1, j, false, intermediate); // Tratamos el nodo inferior.
                    try_node(i, j + 1, false, intermediate); // Tratamos el nodo a la derecha.
                    try_node(i + 1, j + 1, true, intermediate); // Tratamos el nodo de la diagonal inferior derecha.
                } else if (j == dashboard.GetColumnas() - 1) { // CONDICIÓN: Y está en la esquina superior derecha.
                    try_node(i, j - 1, false, intermediate); // Tratamos el nodo de la izquierda.
                    try_node(i + 1, j, false, intermediate); // Tratamos el nodo inferior.
                    try_node(i + 1, j - 1, true, intermediate); // Tratamos el nodo de la diagonal inferior izquierda.
                } else { // CONDICIÓN: Y está en el centro superior.
                    try_node(i + 1, j, false, intermediate); // Tratamos el nodo inferior.
                    try_node(i, j - 1, false, intermediate); // Tratamos el nodo de la izquierda.
                    try_node(i, j + 1, false, intermediate); // Tratamos el nodo de la derecha.
                    try_node(i + 1, j - 1, true, intermediate); // Tratamos el nodo de la diagonal inferior izquierda.
                    try_node(i + 1, j + 1, true, intermediate); // Tratamos el nodo de la diagonal inferior derecha.
                }
            } else if (i == dashboard.GetFilas() - 1) { // CONDICIÓN: Si el nodo se encuentra en el borde inferior.
                if (j == 0) { // CONDICIÓN: Y está en la esquina inferior izquierda.
                    try_node(i, j + 1, false, intermediate); // Tratamos el nodo de la derecha.
                    try_node(i - 1, j, false, intermediate); // Tratamos el nodo superior.
                    try_node(i - 1, j + 1, true, intermediate); // Tratamos el nodo de la diagonal superior derecha.
                } else if (j == dashboard.GetColumnas() - 1) { // CONDICIÓN: Y está en la esquina inferior derecha.
                    try_node(i, j - 1, false, intermediate); // Tratamos el nodo de la izquierda.
                    try_node(i - 1, j, true, intermediate); // Tratamos el nodo superior.
                    try_node(i - 1, j - 1, true, intermediate); // Tratamos el nodo de la diagonal superior izquierda.
                } else { // CONDICIÓN: Y está en el centro inferior.
                    try_node(i, j + 1, false, intermediate); // Tratamos el nodo de la derecha.
                    try_node(i, j - 1, false, intermediate); // Tratamos el nodo de la izquierda.
                    try_node(i - 1, j, false, intermediate); // Tratamos el nodo superior.
                    try_node(i - 1, j + 1, true, intermediate); // Tratamos el nodo de la diagonal superior derecha.
                    try_node(i - 1, j - 1, true, intermediate); // Tratamos el nodo de la diagonal superior izquierda.
                }
            } else if (j == 0) { // CONDICIÓN: Si el nodo se encuentra en el borde derecho.
                try_node(i - 1, j, false, intermediate); // Tratamos el nodo superior.
                try_node(i + 1, j, false, intermediate); // Tratamos el nodo inferior.
                try_node(i, j + 1, false, intermediate); // Tratamos el nodo de la derecha.
                try_node(i - 1, j + 1, true, intermediate); // Tratamos el nodo de la diagonal superior derecha.
                try_node(i + 1, j + 1, true, intermediate); // Tratamos el nodo de la diagonal inferior derecha.
            } else if (j == dashboard.GetFilas() - 1) { // CONDICIÓN: Si el nodo se encuentra en el borde izquierdo.
                try_node(i, j - 1, false, intermediate); // Tratamos el nodo de la izquierda.
                try_node(i + 1, j, false, intermediate); // Tratamos el nodo inferior.
                try_node(i - 1, j, false, intermediate); // Tratamos el nodo superior.
                try_node(i - 1, j - 1, true, intermediate); // Tratamos el nodo de la diagonal superior izquierda.
                try_node(i + 1, j - 1, true, intermediate); // Tratamos el nodo de la diagonal inferior izquierda.
            }

            list_open.order(); // Ordenamos la lista abierta en función de f y hacemos de nuevo pop.
        }

        while (intermediate.get_father() != null) { // En intermediate esta el nodo previo a llegar al objetivo. El nodo origen no tiene father, asi que el bucle se detendrá al llegar a el.
            list_final.insert(intermediate);
            intermediate = intermediate.get_father();
        }
        return list_final;
    }

    //-----> MÉTODOS SETTERS AND GETTERS.
    public int getOrigin_x() {
        return origin_x;
    }

    public void setOrigin_x(int origin_x) {
        this.origin_x = origin_x;
    }

    public int getOrigin_y() {
        return origin_y;
    }

    public void setOrigin_y(int origin_y) {
        this.origin_y = origin_y;
    }

    public int getDestiny_x() {
        return destiny_x;
    }

    public void setDestiny_x(int destiny_x) {
        this.destiny_x = destiny_x;
    }

    public int getDestiny_y() {
        return destiny_y;
    }

    public void setDestiny_y(int destiny_y) {
        this.destiny_y = destiny_y;
    }
}
