//	i_,j_ -> pos actual
//	dist -> distancia respecto al P final
	bool is_obstaculo(int i, int j);//-> funcion que devuelve si hay obtaculo o no
	int dist(int i,int j);//-> funcion que devuelve la distancia de dicho punto

//tiene que ir almacenando el camino

void camino(int i_, int j_, int dist){

	if (is_obstaculo(i_,j_)==true)//si hay un obstaculo
	{
		return;//camino descartado
	}
	
	int distancia = dist(i_,j_);
	
	if (distancia == 0)//ya ha llegado a la meta
	{
		return;//fin del algoritmo
	}
	
	if (distancia > dist)//si la distancia es mayor
	{
		return;//camino descartado
	}
	
	else//si la distancia es menor
	{//es un posible camino
		camino(i_+1,j_,dist);//camino por arriba
		camino(i_-1,j_,dist);//camino por abajo
		camino(i_,j_+1,dist);//camino por la derecha
		camino(i_,j_-1,dist);//camino por la izquierda
	}
}